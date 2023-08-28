package com.company.gamestore.controller;

import com.company.gamestore.model.Console;
import com.company.gamestore.repository.ConsoleRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ConsoleController.class)
public class ConsoleControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ConsoleRepository consoleRepository;

    private Console console;

    @BeforeEach
    public void setUp() {
        console = new Console();
        console.setId(1);
        console.setManufacturer("Sony");
        console.setModel("PlayStation 5");
    }

    @Test
    public void shouldCreateConsole() throws Exception {
        when(consoleRepository.save(any(Console.class))).thenReturn(console);

        mockMvc.perform(post("/consoles")
                        .content("{\"manufacturer\":\"Sony\",\"model\":\"PlayStation 5\"}")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.manufacturer").value("Sony"))
                .andExpect(jsonPath("$.model").value("PlayStation 5"));

        verify(consoleRepository, times(1)).save(any(Console.class));
    }

    @Test
    public void shouldGetConsoleById() throws Exception {
        when(consoleRepository.findById(1)).thenReturn(Optional.of(console));

        mockMvc.perform(get("/consoles/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.manufacturer").value("Sony"))
                .andExpect(jsonPath("$.model").value("PlayStation 5"));

        verify(consoleRepository, times(1)).findById(1);
    }

    @Test
    public void shouldGetAllConsoles() throws Exception {
        List<Console> consoles = new ArrayList<>();
        consoles.add(console);

        when(consoleRepository.findAll()).thenReturn(consoles);

        mockMvc.perform(get("/consoles"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].manufacturer").value("Sony"))
                .andExpect(jsonPath("$[0].model").value("PlayStation 5"));

        verify(consoleRepository, times(1)).findAll();
    }

    @Test
    public void shouldUpdateConsole() throws Exception {
        when(consoleRepository.existsById(1)).thenReturn(true);

        mockMvc.perform(put("/consoles/1")
                        .content("{\"manufacturer\":\"Microsoft\",\"model\":\"Xbox Series X\"}")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.manufacturer").value("Microsoft"))
                .andExpect(jsonPath("$.model").value("Xbox Series X"));

        verify(consoleRepository, times(1)).save(any(Console.class));
    }

    @Test
    public void shouldDeleteConsole() throws Exception {
        mockMvc.perform(delete("/consoles/1"))
                .andExpect(status().isOk());

        verify(consoleRepository, times(1)).deleteById(1);
    }

    @Test
    public void shouldGetConsolesByManufacturer() throws Exception {
        List<Console> consoles = new ArrayList<>();
        consoles.add(console);

        when(consoleRepository.findByManufacturer("Sony")).thenReturn(consoles);

        mockMvc.perform(get("/consoles/manufacturer/Sony"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].manufacturer").value("Sony"))
                .andExpect(jsonPath("$[0].model").value("PlayStation 5"));

        verify(consoleRepository, times(1)).findByManufacturer("Sony");
    }
}
