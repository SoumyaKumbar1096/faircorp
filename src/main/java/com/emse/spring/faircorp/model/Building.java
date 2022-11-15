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
    private String name;

    @Column
    private Double outsideTemperature;

    @OneToMany(mappedBy = "building")
    private List<Room> rooms;


    public Building(){}

    public Building( String name, Double outsideTemperature){
        this.name = name;
        this.outsideTemperature = outsideTemperature;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getOutsideTemperature() {
        return outsideTemperature;
    }

    public void setOutsideTemperature(Double outsideTemperature) {
        this.outsideTemperature = outsideTemperature;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
