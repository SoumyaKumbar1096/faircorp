package com.emse.spring.faircorp.dao;

import com.emse.spring.faircorp.model.Heater;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.emse.spring.faircorp.model.Room;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface HeaterDao extends JpaRepository<Heater, Long>{

    Heater getReferenceById(Long id);

    void deleteByRoomId(Long id);
}
