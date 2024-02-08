package com.vikas.ConnectSocial.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping(path = "/home")
    public String homeControllerHandler(){
        return "This is home controller";
    }
}
