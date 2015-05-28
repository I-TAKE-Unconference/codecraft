package com.itakeunconf.codecraft.service;

import com.itakeunconf.codecraft.model.User;

import java.util.Optional;

public interface UserService {
    Optional<User> getUserById(long id);
    Optional<User> getUserByEmail(String email);
    Boolean saveUser (String username, String password);
}
