package com.itakeunconf.codecraft.service.impl;

import com.itakeunconf.codecraft.model.AuthenticatedUser;
import com.itakeunconf.codecraft.model.User;
import com.itakeunconf.codecraft.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class DefaultUserDetailService implements UserDetailsService {
    private final UserService userService;

    @Autowired
    public DefaultUserDetailService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public AuthenticatedUser loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userService.getUserByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("User with email=%s was not found", email)));
        return new AuthenticatedUser(user);
    }
}

