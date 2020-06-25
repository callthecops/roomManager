package com.example.roomManager.controller;

import com.example.roomManager.model.Floor;
import com.example.roomManager.repository.FloorRepository;
import com.example.roomManager.service.FloorService;
import com.example.roomManager.utils.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class FloorController {

    @Autowired
    FloorRepository floorRepository;
    @Autowired
    FloorService floorService;

    @PostMapping("/addFloor")
    public String addFloor() {
        floorService.saveFloor();
        return "redirect:/home/#";
    }

    @PostMapping("/deleteFloor")
    public String deleteFloor(@RequestParam String deleteFloorId) {
        Floor floor = floorRepository.findById(Long.parseLong(deleteFloorId)).orElseThrow(ResourceNotFoundException::new);
        floorRepository.delete(floor);
        return "redirect:/home";
    }
}
