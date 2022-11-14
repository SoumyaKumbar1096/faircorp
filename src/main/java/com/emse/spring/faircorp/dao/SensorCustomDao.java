package com.emse.spring.faircorp.dao;

import com.emse.spring.faircorp.model.Sensor;

import java.util.List;

public interface SensorCustomDao {

    List<Sensor> findBySiteText(String searchText);

}
