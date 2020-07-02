package com.example.roomManager.repository;

import com.example.roomManager.model.Room;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomRepository extends CrudRepository<Room,Long> {
    List<Room> findByFloorId(long parseLong);
}
