package com.emse.spring.faircorp.dao;

import com.emse.spring.faircorp.model.Heater;
import com.emse.spring.faircorp.model.HeaterStatus;
import com.emse.spring.faircorp.model.Room;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.stream.Collectors;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class HeaterDaoTest {

    @Autowired
    private RoomDao roomDao;

    @Autowired
    private HeaterDao heaterDao;

    @Test
    public void shouldFindAHeater(){
        Heater heater = heaterDao.getReferenceById(-10L);

        Assertions.assertThat(heater.getId()).isEqualTo(-10L);
        Assertions.assertThat(heater.getName()).isEqualTo("Heater1");
        Assertions.assertThat(heater.getHeaterStatus()).isEqualTo(HeaterStatus.ON);
        Assertions.assertThat(heater.getRoom().getId()).isEqualTo(-10L);
    }

    @Test
    public void shouldDeleteAHeaterByRoom(){
        Room room = roomDao.getReferenceById(-10L);
        List<Long> heaterIdList = room.getAllHeaters().stream().map(Heater::getId).collect(Collectors.toList());
        Assertions.assertThat(heaterIdList.size()).isEqualTo(2);

        heaterDao.deleteByRoomId(-10L);

        List<Heater> heaterList = heaterDao.findAllById(heaterIdList);
        Assertions.assertThat(heaterList).isEmpty();
    }



}
