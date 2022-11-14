package com.emse.spring.faircorp.model;

import javax.persistence.*;

@Entity
@Table(name="HEATER")
public class Heater {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column
    private Long power;


    @ManyToOne(optional = false)
    private Room room;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private HeaterStatus heaterStatus;

    public Heater(){ }

    public Heater(String name, Room room, HeaterStatus heaterStatus){
        this.name = name;
        this.room = room;
        this.heaterStatus = heaterStatus;
    }
}
