package com.example.roomManager.service;

import com.example.roomManager.model.Bed;
import com.example.roomManager.model.Room;
import com.example.roomManager.repository.BedRepository;
import com.example.roomManager.repository.RoomRepository;
import com.example.roomManager.utils.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BedService {
    @Autowired
    BedRepository bedRepository;
    @Autowired
    RoomRepository roomRepository;

    public Long saveBed(String id) {
        Room room = roomRepository.findById(Long.parseLong(id)).orElseThrow(ResourceNotFoundException::new);
        List<Bed> bedList = room.getBeds();
        if(bedList.size() == 16)
            return -1L;
        int number = 0;
        if(bedList.size() == 0){
            number = 1;
        }else{
            number = bedList.size()+1;
        }
        Bed bed = new Bed(number);
        bed.setRoom(room);
        bed = bedRepository.save(bed);
        return bed.getId();

    }

    public void deleteBed(String id) {
        Room room = roomRepository.findById(Long.parseLong(id)).orElseThrow(ResourceNotFoundException::new);
        List<Bed> bedList = room.getBeds();
        int number = 0;
        if(bedList.size() == 0) {
            return;
        }
        bedRepository.delete(bedList.get(bedList.size()-1));
    }
}
