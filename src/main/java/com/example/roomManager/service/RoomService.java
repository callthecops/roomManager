package com.example.roomManager.service;

import com.example.roomManager.model.Floor;
import com.example.roomManager.model.Room;
import com.example.roomManager.repository.FloorRepository;
import com.example.roomManager.repository.RoomRepository;
import com.example.roomManager.utils.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomService {
    @Autowired
    RoomRepository roomRepository;
    @Autowired
    FloorRepository floorRepository;

    public void saveRoom(String id) {
        Floor floor = floorRepository.findById(Long.parseLong(id)).orElseThrow(ResourceNotFoundException::new);
        List<Room> roomList = floor.getRooms();
        String name = "Room: " + roomList.size();
        Room room = new Room(name);
        room.setFloor(floor);
        roomRepository.save(room);
    }
}
