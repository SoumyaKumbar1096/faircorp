package com.emse.spring.faircorp.dao;

import com.emse.spring.faircorp.model.Building;
import com.emse.spring.faircorp.model.Heater;
import com.emse.spring.faircorp.model.Room;
import com.emse.spring.faircorp.model.Window;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class BuildingDaoTest {

    @Autowired
    private BuildingDao buildingDao;

    @Test
    public void shouldFindABuilding(){
        Building building = buildingDao.getReferenceById(-10L);
        Assertions.assertThat(building.getName()).isEqualTo("Cours Fauriel");
        Assertions.assertThat(building.getId()).isEqualTo(-10l);
        Assertions.assertThat(building.getOutsideTemperature()).isEqualTo(10);
    }




    @Test
    public void shouldNotFindWindows(){
        List<Window> windowList = buildingDao.findAllWindows(-9L);
        Assertions.assertThat(windowList).isEmpty();
    }




    @Test
    public void shouldNotFindHeaters(){
        List<Heater> heaterList = buildingDao.findAllHeaters(-9L);
        Assertions.assertThat(heaterList).isEmpty();
    }

}
