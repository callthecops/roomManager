package com.example.roomManager.service;

import com.example.roomManager.model.Bed;
import com.example.roomManager.model.Floor;
import com.example.roomManager.model.Room;
import com.example.roomManager.repository.BedRepository;
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

    @Autowired
    BedRepository bedRepository;

    public Room saveRoom(String id) {
        Floor floor = floorRepository.findById(Long.parseLong(id)).orElseThrow(ResourceNotFoundException::new);
        List<Room> roomList = floor.getRooms();
        int number;
        if (roomList.isEmpty()) {
            number = 0;
        } else {
            number = 0;
            for(Room room: roomList){
                String[] tokens = room.getName().split(" ");
                if(Integer.parseInt(tokens[1]) > number){
                    number = Integer.parseInt(tokens[1]);
                }
            }
            number = number+1;
        }


        String name = "Room: " + number;
        Room room = new Room(name);
        room.setFloor(floor);
        room = roomRepository.save(room);
        return room;
    }

    public void deleteRoom(String id) {
        System.out.println("In deleteRoom Function in RoomService");
        Room room = roomRepository.findById(Long.parseLong(id)).orElseThrow(ResourceNotFoundException::new);
        List<Bed> bedList = room.getBeds();
        int number = 0;
        if(bedList.size() != 0) {
            for(Bed bed: bedList){
                bedRepository.delete(bed);
            }
        }

     /*   List<Room> roomList = roomRepository.findByFloorId(room.getFloor().getId());
        boolean flag = false;
        for(Room rm: roomList){
            if(rm.getName().equals(room.getName())) {
                flag = true;
            }else if(flag){
                String[] tokens = rm.getName().split(" ");
                String name = tokens[0] + (Integer.parseInt(tokens[1])-1);
                rm.setName(name);
                roomRepository.save(rm);
            }
        }*/

        roomRepository.delete(room);
    }
}
