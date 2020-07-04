package com.example.roomManager.controller;

import com.example.roomManager.model.Room;
import com.example.roomManager.repository.RoomRepository;
import com.example.roomManager.service.RoomService;
import com.example.roomManager.utils.Criteria;
import com.example.roomManager.utils.ResponseMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class RoomController {

    @Autowired
    RoomService roomService;

    @Autowired
    RoomRepository roomRepository;

    @PostMapping("/addRoom")
    public ResponseEntity<?> addRoom(@RequestBody Criteria criteria) {

        Room room = roomService.saveRoom(criteria.getCriteriaId());
        ResponseMsg result = new ResponseMsg();
        result.setResponse(room.getName());
        result.setId(room.getId()+"");
        return ResponseEntity.ok(result);

        //return "redirect:/home";
    }


    @PostMapping("/deleteRoom")
    public ResponseEntity<?> deleteRoom(@RequestBody Criteria criteria) {

        roomService.deleteRoom(criteria.getCriteriaId());
        ResponseMsg result = new ResponseMsg();
        result.setResponse("Success");
        return ResponseEntity.ok(result);

        //return "redirect:/home";
    }
}
