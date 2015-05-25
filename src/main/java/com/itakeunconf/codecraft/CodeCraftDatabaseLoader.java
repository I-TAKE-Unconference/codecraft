package com.itakeunconf.codecraft;

import com.itakeunconf.codecraft.model.Role;
import com.itakeunconf.codecraft.model.User;
import com.itakeunconf.codecraft.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
@Profile("development")
public class CodeCraftDatabaseLoader {

    private final UserRepository userRepository;

    @Autowired
    public CodeCraftDatabaseLoader(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostConstruct
    private void initDatabase() {
        User user = new User();
        user.setUserName("user01");
        user.setEmail("user@user.com");
        user.setPasswordHash(new BCryptPasswordEncoder().encode("user01"));
        user.setRole(Role.USER);

        userRepository.save(user);
    }
}
