package com.itakeunconf.codecraft.service;

import com.itakeunconf.codecraft.model.PairingSession;
import com.itakeunconf.codecraft.model.User;

import java.text.ParseException;
import java.util.List;

public interface PairingSessionService {

    public PairingSession save(PairingSession pairingSession) throws ParseException;
    public List<PairingSession> getAllPublicSessions();

    void joinSession(Long sessionId, User participant);
}
