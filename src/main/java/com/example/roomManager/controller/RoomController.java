package com.example.roomManager.controller;

import com.example.roomManager.model.Room;
import com.example.roomManager.repository.RoomRepository;
import com.example.roomManager.service.RoomService;
import com.example.roomManager.utils.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RoomController {

    @Autowired
    RoomService roomService;

    @Autowired
    RoomRepository roomRepository;

    @PostMapping("/addRoom")
    public String addRoom(@RequestParam String floorId) {
        roomService.saveRoom(floorId);
        return "redirect:/home";
    }

    @PostMapping("/deleteRoom")
    public String deleteRoom(@RequestParam String deleteRoomId) {
        Room room = roomRepository.findById(Long.parseLong(deleteRoomId)).orElseThrow(ResourceNotFoundException::new);
        roomRepository.delete(room);

        return "redirect:/home";
    }
}
