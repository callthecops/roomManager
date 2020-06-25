package com.example.roomManager.service;

import com.example.roomManager.model.Floor;
import com.example.roomManager.repository.FloorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FloorService {
    @Autowired
    FloorRepository floorRepository;


    public Floor saveFloor() {
        int level;
        List<Floor> floorList = (List<Floor>) floorRepository.findAll();
        if (floorList.isEmpty()) {
            level = 0;
        } else {
            level = floorList.size();
        }
        Floor floor = new Floor(level);
        floorRepository.save(floor);
        return floor;
    }


}
