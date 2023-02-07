package com.tedspsecuritydemo.spsecurity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("")
    public String home(){
        return "<h1>Home page!</h2>";
    }

    @GetMapping("/user")
    public String user(){
        return "<h1>User page!</h2>";
    }

    @GetMapping("/admin")
    public String admin(){
        return "<h1>admin page!</h2>";
    }

}
