package com.example.roomManager.model;

import javax.persistence.*;
import java.util.List;

@Entity

public class Floor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //@Column(name = "floor_id")
    private Long id;
    private int level;
    @OneToMany(mappedBy = "floor")
    private List<Room> rooms;

    public Floor() {
    }

    public Floor(int level) {
        this.level = level;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }
}
