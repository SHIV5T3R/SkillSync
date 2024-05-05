package com.skillsync.backend.service;

import com.skillsync.backend.exception.ResourceNotFoundException;
import com.skillsync.backend.model.User;
import com.skillsync.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.IntStream;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String from;

    private static final String CHAR_LOWER = "abcdefghijklmnopqrstuvwxyz";
    private static final String CHAR_UPPER = CHAR_LOWER.toUpperCase();
    private static final String NUMBER = "0123456789";
    private static final String OTHER_CHAR = "!@#$%&*()_+-=[]?";
    private static final String PASSWORD_ALLOW_BASE = CHAR_LOWER + CHAR_UPPER + NUMBER + OTHER_CHAR;
    private static final int PASSWORD_LENGTH = 8;

    private static final SecureRandom random = new SecureRandom();

    public String generateTempPassword() {
        return IntStream.range(0, PASSWORD_LENGTH)
                .map(i -> PASSWORD_ALLOW_BASE.charAt(random.nextInt(PASSWORD_ALLOW_BASE.length())))
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }

    @Override
    public Collection<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("user", "userId", userId));
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("user", "email", email));
    }

    @Override
    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("user", "userId", username));
    }

    @Override
    public boolean existsUserWithEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public boolean existsUserWithUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public void deleteUserById(Long userId) {
        if (!userRepository.existsById(userId)) {
            throw new ResourceNotFoundException("user", "userId", userId);
        }

        userRepository.deleteById(userId);
    }

    public boolean sendPasswordResetEmail(String email, PasswordEncoder encoder) {
        Optional<User> userData = userRepository.findByEmail(email);

        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(from);
            message.setSubject("SkillSync Password Reset");
            message.setTo(email);

            log.info("sendPasswordResetEmail Drafting response email...");
            if(userData.isPresent()) {
                String tempPass = generateTempPassword();
                message.setText("To access your account please login using this new password: " + tempPass + "\n" +
                        "Be sure to update your password once logged in.");

                mailSender.send(message);

                // only change the user's password to the generated one if the email was sent successfully
                userData.get().setPassword(encoder.encode(tempPass));
                userRepository.save(userData.get());

                log.info("sendPasswordResetEmail Successfully sent email to {}", email);
                return true;
            }
        } catch (Exception e) {
            log.error("sendPasswordResetEmail sending password reset request email failed to {}", email);
        }

        return false;
    }

}
