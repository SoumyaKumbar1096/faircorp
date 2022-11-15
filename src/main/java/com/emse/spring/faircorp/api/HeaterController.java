package com.emse.spring.faircorp.api;

import com.emse.spring.faircorp.dao.HeaterDao;
import com.emse.spring.faircorp.dao.RoomDao;
import com.emse.spring.faircorp.dto.HeaterDto;
import com.emse.spring.faircorp.model.Heater;
import com.emse.spring.faircorp.model.Room;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/heaters")
@Transactional
@CrossOrigin
public class HeaterController {

    private final HeaterDao heaterDao;

    private final RoomDao roomDao;

    public HeaterController(HeaterDao heaterDao, RoomDao roomDao){
        this.heaterDao = heaterDao;
        this.roomDao = roomDao;
    }

    // /api/heaters (GET) send heaters list
    @GetMapping
    public List<HeaterDto> findAll(){
        return heaterDao.findAll().stream().map(HeaterDto::new).collect(Collectors.toList());
    }

    // /api/heaters (POST) add or update a heater
    @PostMapping
    public HeaterDto create(@RequestBody HeaterDto dto){
        Room room = roomDao.getReferenceById(dto.getRoomId());

        Heater heater = null;
        if(dto.getId() == null){
            heater = heaterDao.save(new Heater(dto.getName(), room, dto.getHeaterStatus()));
        } else {
            heater = heaterDao.getReferenceById(dto.getId());
            heater.setHeaterStatus(dto.getHeaterStatus());
            heater.setName(dto.getName());
            heater.setRoom(room);
            heater.setPower(dto.getPower());
        }

        return new HeaterDto(heater);
    }

    // /api/heaters/{heater_id} (GET) read a heater
    @GetMapping(path = "/{id}")
    public HeaterDto findById(@PathVariable Long id){
        return heaterDao.findById(id).map(HeaterDto::new).orElse(null);
    }

    // /api/heaters/{heater_id} (DELETE) delete a heater
    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable Long id){
        heaterDao.deleteById(id);
    }
}
