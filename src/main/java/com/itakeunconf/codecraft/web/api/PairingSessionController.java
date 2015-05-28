package com.itakeunconf.codecraft.web.api;

import com.itakeunconf.codecraft.model.PairingSession;
import com.itakeunconf.codecraft.repository.PairingSessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class PairingSessionController {

    private final PairingSessionRepository pairingSessionRepository;

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
    public String savePairingSession(@RequestBody PairingSession pairingSession) throws ParseException {
        SimpleDateFormat sessionDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date atDate = sessionDateFormat.parse(pairingSession.getDateAsString());
        pairingSession.setAtTime(atDate);
        pairingSessionRepository.save(pairingSession);

        return "sessions";
    }

}
