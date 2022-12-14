package com.emse.spring.faircorp.web;

import com.emse.spring.faircorp.api.WindowController;
import com.emse.spring.faircorp.dao.BuildingDao;
import com.emse.spring.faircorp.dao.RoomDao;
import com.emse.spring.faircorp.dao.WindowDao;

import com.emse.spring.faircorp.dto.WindowDto;
import com.emse.spring.faircorp.model.Building;
import com.emse.spring.faircorp.model.Room;
import com.emse.spring.faircorp.model.Window;
import com.emse.spring.faircorp.model.WindowStatus;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import org.springframework.security.test.context.support.WithMockUser;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(WindowController.class)
class WindowControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private WindowDao windowDao;

    @MockBean
    private RoomDao roomDao;

    @MockBean
    private BuildingDao buildingDao;

    @Test
    @WithMockUser(username = "user", password = "password", roles = "USER")
    void shouldLoadWindows() throws Exception {
        given(windowDao.findAll()).willReturn(List.of(
                createWindow("window 1"),
                createWindow("window 2")
        ));

        mockMvc.perform(get("/api/windows").accept(APPLICATION_JSON))
                // check the HTTP response
                .andExpect(status().isOk())
                // the content can be tested with Json path
                .andExpect(jsonPath("[*].name").value(containsInAnyOrder("window 1", "window 2")));
    }

    @Test
    @WithMockUser(username = "user", password = "password", roles = "USER")
    void shouldLoadAWindowAndReturnNullIfNotFound() throws Exception {
        given(windowDao.findById(999L)).willReturn(Optional.empty());

        mockMvc.perform(get("/api/windows/999").accept(APPLICATION_JSON))
                // check the HTTP response
                .andExpect(status().isOk())
                // the content can be tested with Json path
                .andExpect(content().string(""));
    }

    @Test
    @WithMockUser(username = "user", password = "password", roles = "USER")
    void shouldLoadAWindow() throws Exception {
        given(windowDao.findById(999L)).willReturn(Optional.of(createWindow("window 1")));

        mockMvc.perform(get("/api/windows/999").accept(APPLICATION_JSON))
                // check the HTTP response
                .andExpect(status().isOk())
                // the content can be tested with Json path
                .andExpect(jsonPath("$.name").value("window 1"));
    }

    @Test
    @WithMockUser(username = "user", password = "password", roles = "USER")
    void shouldSwitchWindow() throws Exception {
        Window expectedWindow = createWindow("window 1");
        Assertions.assertThat(expectedWindow.getWindowStatus()).isEqualTo(WindowStatus.OPEN);

        given(windowDao.findById(999L)).willReturn(Optional.of(expectedWindow));

        mockMvc.perform(put("/api/windows/999/switch").accept(APPLICATION_JSON))
                // check the HTTP response
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("window 1"))
                .andExpect(jsonPath("$.windowStatus").value("CLOSED"));
    }

    @Test
    @WithMockUser(username = "user", password = "password", roles = "USER")
    void shouldUpdateWindow() throws Exception {
        Window expectedWindow = createWindow("window 1");
        expectedWindow.setId(1L);
        String json = objectMapper.writeValueAsString(new WindowDto(expectedWindow));

        given(roomDao.getReferenceById(anyLong())).willReturn(expectedWindow.getRoom());
        given(windowDao.getReferenceById(anyLong())).willReturn(expectedWindow);

        mockMvc.perform(post("/api/windows").content(json).contentType(APPLICATION_JSON_VALUE))
                // check the HTTP response
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("window 1"))
                .andExpect(jsonPath("$.id").value("1"));
    }

    @Test
    @WithMockUser(username = "user", password = "password", roles = "USER")
    void shouldCreateWindow() throws Exception {
        Window expectedWindow = createWindow("window 1");
        expectedWindow.setId(null);
        String json = objectMapper.writeValueAsString(new WindowDto(expectedWindow));

        given(roomDao.getReferenceById(anyLong())).willReturn(expectedWindow.getRoom());
        given(windowDao.save(any())).willReturn(expectedWindow);

        mockMvc.perform(post("/api/windows").content(json).contentType(APPLICATION_JSON_VALUE))
                // check the HTTP response
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("window 1"));
    }

    @Test
    @WithMockUser(username = "admin", password = "password", roles = "ADMIN")
    void shouldDeleteWindow() throws Exception {
        mockMvc.perform(delete("/api/windows/-9").accept(APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    private Window createWindow(String name) {
        Building building = buildingDao.getReferenceById(-10L);
        Room room = new Room("S1", 2, building);
        return new Window(name, WindowStatus.OPEN, room);
    }

}