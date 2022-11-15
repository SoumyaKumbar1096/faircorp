package com.emse.spring.faircorp.api;

import com.emse.spring.faircorp.dao.BuildingDao;
import com.emse.spring.faircorp.dao.HeaterDao;
import com.emse.spring.faircorp.dao.RoomDao;
import com.emse.spring.faircorp.dao.WindowDao;
import com.emse.spring.faircorp.dto.BuildingDto;
import com.emse.spring.faircorp.model.Building;
import com.emse.spring.faircorp.model.Heater;
import com.emse.spring.faircorp.model.Room;
import com.emse.spring.faircorp.model.Window;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/buildings")
@Transactional
@CrossOrigin
public class BuildingController {

    private final BuildingDao buildingDao;
    private final RoomDao roomDao;

    private final WindowDao windowDao;

    private final HeaterDao heaterDao;

    public BuildingController(BuildingDao buildingDao, RoomDao roomDao, HeaterDao heaterDao, WindowDao windowDao){
        this.buildingDao = buildingDao;
        this.roomDao = roomDao;
        this.heaterDao = heaterDao;
        this.windowDao = windowDao;
    }

    // /api/buildings (GET) send buildings list
    @GetMapping
    public List<BuildingDto> findAll(){
        return buildingDao.findAll().stream().map(BuildingDto::new).collect(Collectors.toList());
    }

    // /api/buildings (POST) add or update a building
    @PostMapping
    public BuildingDto create(@RequestBody BuildingDto dto){
        Building building = null;

        if(dto.getId() == null){
            building = buildingDao.save(new Building(dto.getName(), dto.getOutsideTemperature()));
        } else {
            building = buildingDao.getReferenceById(dto.getId());
            building.setOutsideTemperature(dto.getOutsideTemperature());
        }
        return new BuildingDto(building);
    }

    // /api/buildings/{building_id} (GET) read a building
    @GetMapping(path = "/{id}")
    public BuildingDto findById(@PathVariable Long id){
        return buildingDao.findById(id).map(BuildingDto::new).orElse(null);

    }

    // /api/buildings/{building_id} (DELETE) delete a building, delete all Rooms, Windows, Heaters
    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable Long id){
        Building building = buildingDao.getReferenceById(id);
        List<Room> roomList = building.getRooms();

        //delete all rooms from this building
        for(Room r: roomList){
            if (r.getBuilding().getId() == id){
                List<Window> windowList = r.getAllWindows();

                //delete all windows from this room
                for(Window w: windowList){
                    if(w.getRoom().getId() == id){
                        windowDao.deleteById(w.getId());
                    }
                }

                //delete all heaters from this room
                List<Heater> heaterList = r.getAllHeaters();
                for(Heater h: heaterList) {
                    if (h.getRoom().getId() == id) {
                        heaterDao.deleteById(h.getId());
                    }
                }
                roomDao.deleteById(r.getId());
            }
        }
        buildingDao.deleteById(id);
    }

}
