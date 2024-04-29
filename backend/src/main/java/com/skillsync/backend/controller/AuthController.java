package com.skillsync.backend.controller;

import com.skillsync.backend.security.TokenProvider;
import com.skillsync.backend.exception.BadRequestException;
import com.skillsync.backend.model.User;
import com.skillsync.backend.payload.AuthResponse;
import com.skillsync.backend.payload.LoginRequest;
import com.skillsync.backend.payload.SignupRequest;
import com.skillsync.backend.service.UserService;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final TokenProvider tokenProvider;

    private String authenticateAndGetToken(String username, String password) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        return tokenProvider.generate(authentication);
    }

    // this is example usage; you can define much more than "consumes" (even though you don't need to explicitly set that)
    @PostMapping(value = "/signup", consumes = "application/json")
    // this is an example for Swagger UI/OpenAPI docs
    @ApiResponse(responseCode = "201", description = "User registered successfully", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = AuthResponse.class))
    })
    // maybe define error responses, etc.?
    public ResponseEntity<AuthResponse> signup(@Valid @RequestBody SignupRequest signupRequest) {
        log.info("Request to register as {}", signupRequest.getUsername());

        if (userService.existsUserWithUsername(signupRequest.getUsername())) {
            throw new BadRequestException(String.format("Username %s already exists.", signupRequest.getUsername()));
        }
        if (userService.existsUserWithEmail(signupRequest.getEmail())) {
            throw new BadRequestException(String.format("Email %s already exists.", signupRequest.getEmail()));
        }

        userService.saveUser(new User(
                null,
                signupRequest.getUsername(),
                signupRequest.getEmail(),
                passwordEncoder.encode(signupRequest.getPassword())
        ));

        return new ResponseEntity<>(
                new AuthResponse(authenticateAndGetToken(signupRequest.getUsername(), signupRequest.getPassword())),
                HttpStatus.CREATED
        );
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody LoginRequest loginRequest) {
        String username = loginRequest.getUsername();
        if (username == null) {
            username = userService.getUserByEmail(loginRequest.getEmail()).getUsername();
        }
        log.info("Request to log in as {}", username);
        return ResponseEntity.ok(new AuthResponse(authenticateAndGetToken(username, loginRequest.getPassword())));
    }

}
