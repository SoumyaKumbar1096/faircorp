package com.emse.spring.faircorp.api;

import com.emse.spring.faircorp.dao.BuildingDao;
import com.emse.spring.faircorp.dao.HeaterDao;
import com.emse.spring.faircorp.dao.RoomDao;
import com.emse.spring.faircorp.dao.WindowDao;
import com.emse.spring.faircorp.dto.HeaterDto;
import com.emse.spring.faircorp.dto.RoomDto;
import com.emse.spring.faircorp.dto.WindowDto;
import com.emse.spring.faircorp.model.*;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/rooms")
@Transactional
public class RoomController {

    private final RoomDao roomDao;
    private final BuildingDao buildingDao;

    private final WindowDao windowDao;

    private final HeaterDao heaterDao;

    public RoomController(RoomDao roomDao, BuildingDao buildingDao, WindowDao windowDao, HeaterDao heaterDao){
        this.roomDao = roomDao;
        this.buildingDao = buildingDao;
        this.windowDao = windowDao;
        this.heaterDao = heaterDao;
    }

    @GetMapping
    public List<RoomDto> findAll(){

        return roomDao.findAll().stream().map(RoomDto::new).collect(Collectors.toList());
    }

    @PostMapping
    public RoomDto create(@RequestBody RoomDto dto){
        Building building = buildingDao.getReferenceById(dto.getBuildingId());
        Room room = null;

        if(dto.getId() == null){
            room = roomDao.save(new Room(dto.getName(), dto.getFloor(), building));
        } else {
            room = roomDao.getReferenceById(dto.getId());
            room.setCurrentTemperature(dto.getCurrentTemperature());
            room.setTargetTemperature(dto.getTargetTemperature());
        }
        return new RoomDto(room);
    }

    @GetMapping(path = "/{id}")
    public RoomDto findById(@PathVariable Long id){
        return roomDao.findById(id).map(RoomDto::new).orElse(null);
    }

    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable Long id){
        Room room = roomDao.getReferenceById(id);
        List<Window> windowList = room.getAllWindows();

        //delete all windows from this room
        for(Window w: windowList){
            if(w.getRoom().getId() == id){
                windowDao.deleteById(w.getId());
            }
        }

        //delete all heaters from this room
        List<Heater> heaterList = room.getAllHeaters();
        for(Heater h: heaterList){
            if(h.getRoom().getId() == id){
                heaterDao.deleteById(h.getId());
            }
        }
        roomDao.deleteById(id);
    }

    @PutMapping(path = "/{id}/switchWindow")
    public List<WindowDto> switchWindowStatus(@PathVariable Long id){
        Room room = roomDao.getReferenceById(id);
        List<Window> windowList = room.getAllWindows();

        List<WindowDto> windowDtoList = null;
        for (Window w: windowList){
            if (w.getWindowStatus() == WindowStatus.CLOSED){
                w.setWindowStatus(WindowStatus.OPEN);
            } else {
                w.setWindowStatus(WindowStatus.CLOSED);
            }
            windowDtoList.add(new WindowDto(w));
        }

        return windowDtoList;
    }

    @PutMapping(path = "{id}/switchHeaters")
    public List<HeaterDto> switchHeaterStatus(@PathVariable Long id){
        Room room = roomDao.getReferenceById(id);
        List<Heater> heaterList = room.getAllHeaters();
        List<HeaterDto> heaterDtoList = null;
        for (Heater h: heaterList){
            if (h.getHeaterStatus() == HeaterStatus.ON){
                h.setHeaterStatus(HeaterStatus.OFF);
            }else {
                h.setHeaterStatus(HeaterStatus.ON);
            }
            heaterDtoList.add(new HeaterDto(h));
        }

        return heaterDtoList;
    }

}
