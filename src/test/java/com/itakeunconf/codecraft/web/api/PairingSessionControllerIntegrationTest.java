package com.itakeunconf.codecraft.web.api;

import com.itakeunconf.codecraft.model.PairingSession;
import com.itakeunconf.codecraft.repository.PairingSessionRepository;
import com.itakeunconf.codecraft.service.PairingSessionService;
import org.eclipse.jetty.webapp.WebAppContext;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.Arrays;

import static com.jayway.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.hamcrest.Matchers.is;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {WebAppContext.class})
@WebAppConfiguration
public class PairingSessionControllerIntegrationTest {

    private PairingSessionRepository pairingSessionRepositoryMock;
    private PairingSessionService pairingSessionServiceMock;

    @Before
    public void setup() {
        this.pairingSessionRepositoryMock = Mockito.mock(PairingSessionRepository.class);
        this.pairingSessionServiceMock = Mockito.mock(PairingSessionService.class);

        Mockito
                .when(pairingSessionRepositoryMock.findAll())
                .thenReturn(Arrays.asList(buildSession("session one"), buildSession("session two")));
    }

    @Test
    public void getPublicSessions_shouldReturn_allPairingSessions() throws Exception {

        given()
            .standaloneSetup(new PairingSessionController(pairingSessionRepositoryMock,pairingSessionServiceMock))
        .when()
            .get("/api/public/sessions")
        .then()
            .statusCode(HttpStatus.OK.value())
            .body("size()", is(2));

        Mockito.verify(pairingSessionRepositoryMock, Mockito.times(1)).findAll();
        Mockito.verifyNoMoreInteractions(pairingSessionRepositoryMock);
    }

    private static PairingSession buildSession(String name) {
        PairingSession session = new PairingSession();
        session.setSessionName(name);
        return session;
    }
}
