package com.itakeunconf.codecraft.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @RequestMapping("/")
    String index() {
        return "index";
    }

    @RequestMapping("/about")
    String about() {return "about";}

}
