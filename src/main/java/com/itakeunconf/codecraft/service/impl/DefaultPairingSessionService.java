package com.itakeunconf.codecraft.service.impl;

import com.itakeunconf.codecraft.model.PairingSession;
import com.itakeunconf.codecraft.repository.PairingSessionRepository;
import com.itakeunconf.codecraft.repository.UserRepository;
import com.itakeunconf.codecraft.service.PairingSessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DefaultPairingSessionService implements PairingSessionService
{
    private final PairingSessionRepository pairingSessions;
    private final UserRepository users;

    @Autowired
    public DefaultPairingSessionService(PairingSessionRepository repository, UserRepository users) {
        this.pairingSessions = repository;
        this.users = users;
    }

    @Override
    public void joinSession(Long sessionId, Long userId) {
        PairingSession session = pairingSessions.findOne(sessionId);

        session.setParticipant(users.getOne(userId));
    }
}
