package com.example.roomManager.controller;

import com.example.roomManager.model.Bed;
import com.example.roomManager.model.Room;
import com.example.roomManager.repository.BedRepository;
import com.example.roomManager.repository.RoomRepository;
import com.example.roomManager.service.BedService;
import com.example.roomManager.service.RoomService;
import com.example.roomManager.utils.Criteria;
import com.example.roomManager.utils.ResourceNotFoundException;
import com.example.roomManager.utils.ResponseMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class BedController {
    @Autowired
    BedService bedService;

    @Autowired
    BedRepository bedRepository;

    @PostMapping("/addBed")
    public ResponseEntity<?> addBed(@RequestBody Criteria criteria) {
        System.out.println("In addBed Function of BedController");
        System.out.println(criteria.getCriteriaId());
        Long id = bedService.saveBed(criteria.getCriteriaId());
        ResponseMsg result = new ResponseMsg();
        result.setResponse("Success");
        result.setId(id+"");
        return ResponseEntity.ok(result);

        //return "redirect:/home";
    }

   @PostMapping("/delBed")
    public ResponseEntity<?> deleteBed(@RequestBody Criteria criteria) {
       System.out.println("In deleteBed Function of BedController");
       System.out.println(criteria.getCriteriaId());
       bedService.deleteBed(criteria.getCriteriaId());
       ResponseMsg result = new ResponseMsg();
       result.setResponse("Success");
       return ResponseEntity.ok(result);

       //return "redirect:/home";
    }

    @PostMapping("/occupiedBed")
    public ResponseEntity<?> occupiedBed(@RequestBody Criteria criteria) {
        System.out.println("In occupiedBed Function of BedController");
        Bed bed = bedRepository.findById(Long.parseLong(criteria.getCriteriaId())).orElseThrow(ResourceNotFoundException::new);
        bed.setOccupied(!bed.isOccupied());
        bedRepository.save(bed);
        ResponseMsg result = new ResponseMsg();
        result.setResponse("Success");
        return ResponseEntity.ok(result);
        //return "redirect:/home";
    }
}
