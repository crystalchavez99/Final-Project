package com.company.gamestore.controller;

import com.company.gamestore.model.TShirt;
import com.company.gamestore.repository.TShirtRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(TShirtController.class)
public class TShirtControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TShirtRepository tShirtRepository;

    private TShirt tShirt;

    @BeforeEach
    public void setUp() {
        tShirt = new TShirt();
        tShirt.setId(1);
        tShirt.setColor("Red");
        tShirt.setSize("M");
    }

    @Test
    public void shouldCreateTShirt() throws Exception {
        when(tShirtRepository.save(any(TShirt.class))).thenReturn(tShirt);

        mockMvc.perform(post("/tshirts")
                        .content("{\"color\":\"Red\",\"size\":\"M\"}")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.color").value("Red"))
                .andExpect(jsonPath("$.size").value("M"));

        verify(tShirtRepository, times(1)).save(any(TShirt.class));
    }

    @Test
    public void shouldGetTShirtById() throws Exception {
        when(tShirtRepository.findById(1)).thenReturn(Optional.of(tShirt));

        mockMvc.perform(get("/tshirts/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.color").value("Red"))
                .andExpect(jsonPath("$.size").value("M"));

        verify(tShirtRepository, times(1)).findById(1);
    }

    @Test
    public void shouldGetAllTShirts() throws Exception {
        List<TShirt> tShirts = Arrays.asList(tShirt);

        when(tShirtRepository.findAll()).thenReturn(tShirts);

        mockMvc.perform(get("/tshirts"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].color").value("Red"))
                .andExpect(jsonPath("$[0].size").value("M"));

        verify(tShirtRepository, times(1)).findAll();
    }

    @Test
    public void shouldUpdateTShirt() throws Exception {
        when(tShirtRepository.existsById(1)).thenReturn(true);

        mockMvc.perform(put("/tshirts/1")
                        .content("{\"color\":\"Blue\",\"size\":\"L\"}")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.color").value("Blue"))
                .andExpect(jsonPath("$.size").value("L"));

        verify(tShirtRepository, times(1)).save(any(TShirt.class));
    }

    @Test
    public void shouldDeleteTShirt() throws Exception {
        mockMvc.perform(delete("/tshirts/1"))
                .andExpect(status().isOk());

        verify(tShirtRepository, times(1)).deleteById(1);
    }

    @Test
    public void shouldGetTShirtsByColor() throws Exception {
        List<TShirt> tShirts = Arrays.asList(tShirt);

        when(tShirtRepository.findByColor("Red")).thenReturn(tShirts);

        mockMvc.perform(get("/tshirts/color/Red"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].color").value("Red"))
                .andExpect(jsonPath("$[0].size").value("M"));

        verify(tShirtRepository, times(1)).findByColor("Red");
    }

    @Test
    public void shouldGetTShirtsBySize() throws Exception {
        List<TShirt> tShirts = Arrays.asList(tShirt);

        when(tShirtRepository.findBySize("M")).thenReturn(tShirts);

        mockMvc.perform(get("/tshirts/size/M"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].color").value("Red"))
                .andExpect(jsonPath("$[0].size").value("M"));

        verify(tShirtRepository, times(1)).findBySize("M");
    }
}
