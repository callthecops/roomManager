package com.example.roomManager.controller;

import com.example.roomManager.model.Bed;
import com.example.roomManager.model.Floor;
import com.example.roomManager.model.Room;
import com.example.roomManager.repository.BedRepository;
import com.example.roomManager.repository.FloorRepository;
import com.example.roomManager.repository.RoomRepository;
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
    @Autowired
    RoomRepository roomRepository;
    @Autowired
    BedRepository bedRepository;

    @PostMapping("/addFloor")
    public String addFloor() {
        System.out.println("In addFloor Controller Function");
        floorService.saveFloor();
        return "redirect:/home/#";
    }

    @PostMapping("/deleteFloor")
    public String deleteFloor(@RequestParam String deleteFloorId) {
        Floor floor = floorRepository.findById(Long.parseLong(deleteFloorId)).orElseThrow(ResourceNotFoundException::new);
        if(floor.getRooms() != null){
            for(Room room: floor.getRooms()){
                if(room.getBeds()!= null) {
                    for (Bed bed : room.getBeds()) {
                        bedRepository.delete(bed);
                    }
                }
                roomRepository.delete(room);
            }
        }
        /*List<Floor> floorList = (List<Floor>) floorRepository.findAll();
        for(Floor flr: floorList){
            if(flr.getLevel() > floor.getLevel()){
                flr.setLevel(flr.getLevel()-1);
                floorRepository.save(flr);
            }
        }*/
        floorRepository.delete(floor);
        return "redirect:/home";
    }
}
