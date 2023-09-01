package com.company.gamestore.controller;

import com.company.gamestore.model.Game;
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

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(TShirtController.class)
public class TShirtControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TShirtRepository tShirtRepository;

    private TShirt tshirt;

    private ObjectMapper mapper = new ObjectMapper();

    @BeforeEach
    public void setUp() {
        tshirt = new TShirt();
        BigDecimal decimal = new BigDecimal("24.99");
        MathContext mc = new MathContext(4);
        tshirt.setSize("Medium");
        tshirt.setColor("Red");
        tshirt.setDescription("100% Cotton, Machine Wash Cold, Air Dry or Tumble Dry Low");
        tshirt.setPrice(decimal.round(mc));
        tshirt.setQuantity(20);
    }

    @Test
    void shouldCreateTShirt() throws Exception {
        String input = mapper.writeValueAsString(tshirt);

        mockMvc.perform(post("/tshirts")
                        .content(input)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    void shouldGetAllTShirts() throws Exception {
        mockMvc.perform(get("/tshirts"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void shouldGetTShirtById() throws Exception {
        mockMvc.perform(get("/tshirts/1"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void shouldUpdateTShirt() throws Exception {
        tshirt.setColor("Black");
        String inputJson = mapper.writeValueAsString(tshirt);

        mockMvc.perform(put("/tshirts")
                        .content(inputJson)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    void shouldDeleteTShirtById() throws Exception {
        mockMvc.perform(delete("/tshirts/1"))
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    @Test
    void shouldGetTShirtByColor() throws Exception {
        mockMvc.perform(get("/tshirts/color/Red"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void shouldGetTShirtBySize() throws Exception {
        mockMvc.perform(get("/tshirts/size/Medium"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void shouldReturn422ErrorCode() throws Exception {
        TShirt tshirt2 = new TShirt();

        String input = mapper.writeValueAsString(tshirt2);

        mockMvc.perform(post("/tshirts")
                        .content(input)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }
}
