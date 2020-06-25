package com.example.roomManager.model;

import javax.persistence.*;

@Entity
public class Bed {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int number;
    private boolean occupied;
    @ManyToOne
    @JoinColumn(name = "room_id", nullable = false)
    private Room room;

    public Bed(Long id, int number, boolean occupied, Room room) {
        this.id = id;
        this.number = number;
        this.occupied = occupied;
        this.room = room;
    }

    public Bed() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public boolean isOccupied() {
        return occupied;
    }

    public void setOccupied(boolean occupied) {
        this.occupied = occupied;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }
}
