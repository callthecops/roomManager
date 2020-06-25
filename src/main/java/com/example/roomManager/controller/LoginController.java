package com.example.roomManager.controller;

import com.example.roomManager.model.Floor;
import com.example.roomManager.model.Room;
import com.example.roomManager.repository.FloorRepository;
import com.example.roomManager.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class LoginController {
    @Autowired
    FloorRepository floorRepository;
    @Autowired
    RoomRepository roomRepository;

    @GetMapping("/login")
    public String showLogin() {

        return "login4";
    }

    @GetMapping("/home")
    public String showHome(Model model) {
        List<Floor> allFloors = (List<Floor>) floorRepository.findAll();

        model.addAttribute("allFloors", allFloors);
        return "home7";
    }


}
