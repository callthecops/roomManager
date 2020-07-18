package com.example.roomManager.repository;

import com.example.roomManager.model.Bed;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BedRepository extends CrudRepository<Bed, Long> {
    @Override
    List<Bed> findAll();
    List<Bed> findAllByOccupiedFalse();
}
