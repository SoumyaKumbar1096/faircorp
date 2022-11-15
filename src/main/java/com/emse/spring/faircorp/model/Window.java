package com.emse.spring.faircorp.model;

import javax.persistence.*;

//1
@Entity
//2
@Table(name="RWINDOW")
public class Window {
    //3
    @Id
    @GeneratedValue
    private Long id;
    //4
    @Column(nullable = false)
    private String name;

    @ManyToOne
    private Room room;
    //5
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private WindowStatus window_status;

    public Window(){
    }

    public Window(String name, WindowStatus window_status, Room room){
        this.window_status = window_status;
        this.name = name;
        this.room = room;
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

    public Room getRoom(){ return room; }

    public void setRoom(Room room){ this.room = room; }



    public WindowStatus getWindowStatus() {
        return window_status;
    }

    public void setWindowStatus(WindowStatus window_status) {
        this.window_status = window_status;
    }
}
