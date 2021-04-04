package edu.samir.springsecuritydemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DemoWebAppController {

    @GetMapping(path = "/")
    public String  goHome(){
        return "home";
    }

    @GetMapping("/loginForm")
    public String showMyLoginPage(){
        return "login-form";
    }

}
