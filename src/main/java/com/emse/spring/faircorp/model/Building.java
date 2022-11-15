package com.emse.spring.faircorp.model;

import org.springframework.boot.autoconfigure.info.ProjectInfoProperties;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "BUILDING")
public class Building {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private Double outside_temperature;

    @OneToMany(mappedBy = "building")
    private List<Room> rooms;


    public Building(){}

    public Building(Long id){
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getOutside_temperature() {
        return outside_temperature;
    }

    public void setOutside_temperature(Double outside_temperature) {
        this.outside_temperature = outside_temperature;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }
}
