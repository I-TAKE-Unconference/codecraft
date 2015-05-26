package com.itakeunconf.codecraft;

import com.itakeunconf.codecraft.model.PairingSession;
import com.itakeunconf.codecraft.model.Role;
import com.itakeunconf.codecraft.model.User;
import com.itakeunconf.codecraft.repository.PairingSessionRepository;
import com.itakeunconf.codecraft.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Arrays;

@Service
@Profile("development")
public class CodeCraftDatabaseLoader {

    private final UserRepository userRepository;
    private final PairingSessionRepository pairingSessionRepository;

    @Autowired
    public CodeCraftDatabaseLoader(UserRepository userRepository, PairingSessionRepository pairingSessionRepository) {
        this.userRepository = userRepository;
        this.pairingSessionRepository = pairingSessionRepository;
    }

    @PostConstruct
    private void initDatabase() {
        User user = new User();
        user.setUserName("user01");
        user.setEmail("user@user.com");
        user.setPasswordHash(new BCryptPasswordEncoder().encode("user01"));
        user.setRole(Role.USER);

        userRepository.save(user);

        PairingSession pairingSessionOne = new PairingSession();
        pairingSessionOne.setSessionName("Let's code together");

        PairingSession pairingSessionTwo = new PairingSession();
        pairingSessionTwo.setSessionName("Ruby ninja pairing");

        pairingSessionRepository.save(Arrays.asList(pairingSessionOne,pairingSessionTwo) );

    }
}
