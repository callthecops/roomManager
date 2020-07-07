package com.example.roomManager.controller;

import com.example.roomManager.model.Floor;
import com.example.roomManager.repository.FloorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class LoginController {

    @Autowired
    private FloorRepository floorRepository;


	/*
	private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;


    @Autowired
    public LoginController(FloorRepository floorRepository, UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.floorRepository = floorRepository;
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }
    */


    @GetMapping("/login")
    public String showLogin() {
//        System.out.println("IN LOGIN");
//        User user = userRepository.findByName("user1");
//        System.out.println(user.getName());
//        System.out.println(user.getPassword());
//        System.out.println(user.getRole());
        return "login4";
    }


    @GetMapping("/home")
    public String showHome(Model model) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipal = authentication.getName();
        model.addAttribute("logedInUser", currentPrincipal);

        List<Floor> allFloors = (List<Floor>) floorRepository.findAll();
        model.addAttribute("allFloors", allFloors);

        return "home9";
    }


}
