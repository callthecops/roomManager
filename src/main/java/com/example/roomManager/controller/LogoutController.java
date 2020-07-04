package com.example.roomManager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LogoutController {

    @GetMapping("/signout")
    public String showLogout(){

        return "login4";
    }
}
