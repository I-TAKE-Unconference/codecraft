package com.itakeunconf.codecraft.service.impl;

import com.itakeunconf.codecraft.model.Role;
import com.itakeunconf.codecraft.model.User;
import com.itakeunconf.codecraft.repository.UserRepository;
import com.itakeunconf.codecraft.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolationException;
import java.util.Optional;

@Service
public class DatabaseUserService implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public DatabaseUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Optional<User> getUserById(long id) {
        return Optional.ofNullable(userRepository.findOne(id));
    }

    @Override
    public Optional<User> getUserByEmail(String email) {
        return userRepository.findOneByEmail(email);
    }

    @Override
    public Boolean saveUser(String username, String password) {
        User user = new User();
        user.setUserName(username);
        user.setEmail(username);
        user.setPasswordHash(new BCryptPasswordEncoder().encode(password));
        user.setRole(Role.USER);
        try {
            userRepository.save(user);
            return true;
        }
        catch (Exception ex){
            //log
            ex.printStackTrace();
            return false;
        }
    }

}
