package com.emse.spring.faircorp.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="ROOM")
public class Room {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private Integer floor;

    @Column(nullable = false)
    private String name;

    @Column
    private Double current_temperature;

    @Column
    private Double target_temperature;

    @OneToMany(mappedBy = "room")
    private List<Heater> all_heaters;

    @OneToMany(mappedBy = "room")
    private List<Window> all_windows;


    @ManyToOne
    private Building building;

    public Room(){}

    public Room(String name, Integer floor){
        this.name = name;
        this.floor = floor;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getFloor() {
        return floor;
    }

    public void setFloor(Integer floor) {
        this.floor = floor;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getCurrent_temperature() {
        return current_temperature;
    }

    public void setCurrent_temperature(Double current_temperature) {
        this.current_temperature = current_temperature;
    }

    public Double getTarget_temperature() {
        return target_temperature;
    }

    public void setTarget_temperature(Double target_temperature) {
        this.target_temperature = target_temperature;
    }

    public List<Heater> getAll_heaters() {
        return all_heaters;
    }

    public void setAll_heaters(List<Heater> all_heaters) {
        this.all_heaters = all_heaters;
    }

    public List<Window> getAll_windows() {
        return all_windows;
    }

    public void setAll_windows(List<Window> all_windows) {
        this.all_windows = all_windows;
    }
}
