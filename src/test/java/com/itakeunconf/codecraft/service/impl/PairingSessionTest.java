package com.itakeunconf.codecraft.service.impl;

import com.itakeunconf.codecraft.model.PairingSession;
import com.itakeunconf.codecraft.model.User;
import com.itakeunconf.codecraft.repository.PairingSessionRepository;
import com.itakeunconf.codecraft.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;


public class PairingSessionTest {
    private UserRepository userRepositoryMock;
    private PairingSessionRepository pairingSessionRepositoryMock;
    private PairingSession session;

    @Before
    public void setup() {
        this.pairingSessionRepositoryMock = Mockito.mock(PairingSessionRepository.class);
        this.userRepositoryMock = Mockito.mock(UserRepository.class);

        session = Mockito.mock(PairingSession.class);
        Mockito.when(pairingSessionRepositoryMock.findOne(Mockito.anyLong())).thenReturn(session);
    }

    @Test
    public void joining_a_session_saves_user_as_participant() {
        this.userRepositoryMock = Mockito.mock(UserRepository.class);

        DefaultPairingSessionService service = new DefaultPairingSessionService(pairingSessionRepositoryMock);
        User user = Mockito.mock(User.class);

        Mockito
                .when(userRepositoryMock.getOne(Mockito.anyLong()))
                .thenReturn(user);

        service.joinSession(1L, user);
        Mockito.verify(session).setParticipant(user);
    }
}
