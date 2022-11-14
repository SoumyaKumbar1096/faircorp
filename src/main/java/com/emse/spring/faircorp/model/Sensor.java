package com.emse.spring.faircorp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Sensor {

    @Id
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column
    private Long siteId;



}
