package com.example.roomManager.controller;

import com.example.roomManager.model.Floor;
import com.example.roomManager.model.User;
import com.example.roomManager.repository.FloorRepository;
import com.example.roomManager.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class LoginController {
    private final FloorRepository floorRepository;
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;


    @Autowired
    public LoginController(FloorRepository floorRepository, UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.floorRepository = floorRepository;
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }


    @GetMapping("/login")
    public String showLogin() {

        return "login4";
    }

    @PostMapping("/loginver")
    public String loginProcessForm(@RequestParam String username, @RequestParam String password) {
        System.out.println(username);
        String pass = bCryptPasswordEncoder.encode(password);
        User user = userRepository.findByName(username);
        System.out.println(user.getName());
        System.out.println(user.getPassword());
        System.out.println(user.getRole());

        return "succes";
    }


    @GetMapping("/home")
    public String showHome(Model model) {

        List<Floor> allFloors = (List<Floor>) floorRepository.findAll();
        model.addAttribute("allFloors", allFloors);

        return "home9";
    }


}
