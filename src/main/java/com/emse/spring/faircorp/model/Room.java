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

    public Room(){}

    public Room(String name, Integer floor){
        this.name = name;
        this.floor = floor;
    }

}
