package com.company.gamestore.controller;

import com.company.gamestore.model.Console;
import com.company.gamestore.model.TShirt;
import com.company.gamestore.repository.ConsoleRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(ConsoleController.class)
class ConsoleControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ConsoleRepository consoleRepository;
    private ObjectMapper mapper = new ObjectMapper();

    private Console console;

    @BeforeEach
    public void setUp() {
        console = new Console();
        console.setPrice(BigDecimal.valueOf(299.99));
        console.setModel("Nintendo Switch");
        console.setManufacturer("Nintendo");
        console.setProcessor("ARM 4 Cortex-A57");
        console.setQuantity(1);
    }

    @Test
    public void getAllConsoles() throws Exception {
        mockMvc.perform(get("/console"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void getConsoleById() throws Exception {
        mockMvc.perform(get("/console/2"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void getConsolesByManufacturer() throws Exception {
        mockMvc.perform(get("/console/manufacturer/2"))
                .andDo(print())
                .andExpect(status().isOk());
    }
    @Test
    public void createConsole() throws Exception {
        String input = mapper.writeValueAsString(console);

        mockMvc.perform(post("/console")
                        .content(input)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    void shouldUpdateConsole() throws Exception {
        console.setManufacturer("Sega");
        String inputJson = mapper.writeValueAsString(console);

        mockMvc.perform(put("/console")
                        .content(inputJson)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    public void deleteConsole() throws Exception {
        mockMvc.perform(delete("/console/3"))
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    @Test
    void shouldReturn422ErrorCode() throws Exception {
        Console console2 = new Console();

        String input = mapper.writeValueAsString(console2);

        mockMvc.perform(post("/console")
                        .content(input)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }
}
