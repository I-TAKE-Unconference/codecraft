package com.itakeunconf.codecraft.web.api;

import com.itakeunconf.codecraft.model.AuthenticatedUser;
import com.itakeunconf.codecraft.model.PairingSession;
import com.itakeunconf.codecraft.model.User;
import com.itakeunconf.codecraft.repository.PairingSessionRepository;
import com.itakeunconf.codecraft.service.PairingSessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;
import java.util.List;

@Controller
public class PairingSessionController {

    private final PairingSessionRepository pairingSessionRepository;

    private final PairingSessionService pairingSessionService;

    @Autowired
    public PairingSessionController(PairingSessionRepository pairingSessionRepository, PairingSessionService pairingSessionService) {
        this.pairingSessionRepository = pairingSessionRepository;
        this.pairingSessionService = pairingSessionService;
    }

    @RequestMapping(value = "/api/public/sessions", method= RequestMethod.GET)
    public @ResponseBody List<PairingSession> getPublicSessions(){
        return pairingSessionRepository.findAll();
    }

    @RequestMapping(value = "/api/public/session/(sessionId)/join",method = RequestMethod.POST)
    public @ResponseBody PairingSession joinSession(@PathVariable Long sessionId, Principal principal) {
        User currentUser = ((AuthenticatedUser)((UsernamePasswordAuthenticationToken) principal).getPrincipal()).getUser();
        pairingSessionService.joinSession(currentUser.getId(), sessionId);

        return pairingSessionRepository.getOne(sessionId);
    }
}
