package edu.samir.springsecuritydemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SpringSecurityDemoWebAppController {

    @GetMapping(path = "/")
    public String  goHome(){
        return "home";
    }
}
