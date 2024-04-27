package com.skillsync.backend.service;

import com.skillsync.backend.model.User;

import java.util.Collection;

public interface UserService {

    Collection<User> getUsers();
    User getUserById(Long userId);
    User getUserByEmail(String email);
    User getUserByUsername(String username);
    boolean existsUserWithEmail(String email);
    boolean existsUserWithUsername(String username);
    User saveUser(User user);
    void deleteUserById(Long userId);

}
