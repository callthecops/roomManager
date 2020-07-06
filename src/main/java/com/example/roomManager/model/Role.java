package com.example.roomManager.model;

import javax.persistence.*;

@Entity
@Table(name = "roles")
public class Role {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    public long getId() {
        return id;
    }

    public String getRole() {
        return name;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setRole(String role) {
        this.name = role;
    }

    @Column(name = "name")
    private String name;

}
