package com.example.roomManager.model;

import javax.persistence.*;
import java.util.List;

@Entity

public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private String name;
    @OneToMany(mappedBy = "room")
    private List<Bed> beds;
    @ManyToOne
    @JoinColumn(name = "floor_id", nullable = false)
    private Floor floor;


    public Room() {
    }

    public Room(String name) {
        this.name = name;
    }

    public Room(Long id, String name, List<Bed> beds) {
        this.id = id;
        this.name = name;
        this.beds = beds;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Bed> getBeds() {
        return beds;
    }

    public void setBeds(List<Bed> beds) {
        this.beds = beds;
    }

    public Floor getFloor() {
        return floor;
    }

    public void setFloor(Floor floor) {
        this.floor = floor;
    }
}
