package com.example.roomManager.repository;

import com.example.roomManager.model.Floor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FloorRepository extends CrudRepository<Floor, Long> {
}
