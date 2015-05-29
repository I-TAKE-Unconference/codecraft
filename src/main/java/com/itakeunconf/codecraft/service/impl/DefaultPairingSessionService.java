package com.itakeunconf.codecraft.service.impl;

import com.itakeunconf.codecraft.model.PairingSession;
import com.itakeunconf.codecraft.model.User;
import com.itakeunconf.codecraft.repository.PairingSessionRepository;
import com.itakeunconf.codecraft.service.PairingSessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class DefaultPairingSessionService implements PairingSessionService {

    private final PairingSessionRepository pairingSessionRepository;

    @Autowired
    public DefaultPairingSessionService(PairingSessionRepository pairingSessionRepository) {
        this.pairingSessionRepository = pairingSessionRepository;
    }

    @Override
    public PairingSession save(PairingSession pairingSession) throws ParseException {
        return pairingSessionRepository.save(translateSessionTime(pairingSession));
    }

    @Override
    public List<PairingSession> getAllPublicSessions() {
        return pairingSessionRepository.findAll();
    }

    @Override
    public void joinSession(Long sessionId, User participant) {
        PairingSession session = pairingSessionRepository.findOne(sessionId);
        session.setParticipant(participant);
        pairingSessionRepository.save(session);
    }

    private PairingSession translateSessionTime(PairingSession session) throws ParseException {
        SimpleDateFormat sessionDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date atDate = null;

        try {
            atDate = sessionDateFormat.parse(session.getDateAsString());
        } catch (Exception e) {
            if (session.getAtTime() != null) {
                return session;
            } else {
                throw new IllegalArgumentException("Date format is incorrect, try 'yyyy-MM-dd HH:mm'");
            }
        }

        session.setAtTime(atDate);
        return session;
    }
}
