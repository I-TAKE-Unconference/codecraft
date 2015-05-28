package com.itakeunconf.codecraft.web.api;

import com.itakeunconf.codecraft.model.AuthenticatedUser;
import com.itakeunconf.codecraft.model.PairingSession;
import com.itakeunconf.codecraft.model.User;
import com.itakeunconf.codecraft.repository.PairingSessionRepository;
import com.itakeunconf.codecraft.service.impl.DefaultUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class PairingSessionController {

    private final PairingSessionRepository pairingSessionRepository;

    private DefaultUserDetailService defaultUserDetailService;

    public void setDefaultUserDetailService(DefaultUserDetailService defaultUserDetailService) {
        this.defaultUserDetailService = defaultUserDetailService;
    }

    @Autowired
    public PairingSessionController(PairingSessionRepository pairingSessionRepository) {
        this.pairingSessionRepository = pairingSessionRepository;
    }

    @RequestMapping(value = "/api/public/sessions", method= RequestMethod.GET)
    public @ResponseBody List<PairingSession> getPublicSessions(){
        return pairingSessionRepository.findAll();
    }

    @RequestMapping(value = "/sessions", method = RequestMethod.GET)
    public String sessions(){
        return "sessions";
    }

    @RequestMapping(value = "/api/session/add", method= RequestMethod.POST)
    public String savePairingSession(@RequestBody PairingSession pairingSession, Principal principal) throws ParseException {
        SimpleDateFormat sessionDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date atDate = sessionDateFormat.parse(pairingSession.getDateAsString());
        pairingSession.setAtTime(atDate);
        User currentUser = ((AuthenticatedUser)((UsernamePasswordAuthenticationToken) principal).getPrincipal()).getUser();
        pairingSession.setCreator(currentUser);
        pairingSessionRepository.save(pairingSession);

        return "index";
    }

}
