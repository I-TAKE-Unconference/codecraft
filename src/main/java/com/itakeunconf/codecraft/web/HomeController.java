package com.itakeunconf.codecraft.web;

import com.itakeunconf.codecraft.model.AuthenticatedUser;
import com.itakeunconf.codecraft.model.User;
import com.itakeunconf.codecraft.service.PairingSessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;

@Controller
public class HomeController {

    private final PairingSessionService pairingSessionService;

    @Autowired
    public HomeController(PairingSessionService pairingSessionService) {
        this.pairingSessionService = pairingSessionService;
    }

    @RequestMapping({"/", "/index", "/index.html"})
    String index() { return "index"; }

    @RequestMapping("/about")
    String about() { return "about"; }


    @RequestMapping(value = "/join/{sessionId}",method = RequestMethod.GET)
    public String joinSession(@PathVariable Long sessionId, Principal principal) {
        User currentUser = ((AuthenticatedUser)((UsernamePasswordAuthenticationToken) principal).getPrincipal()).getUser();
        pairingSessionService.joinSession(sessionId, currentUser);

        return "index";
    }
}
