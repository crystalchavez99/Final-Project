package com.company.gamestore.controller;

import com.company.gamestore.model.Console;
import com.company.gamestore.model.Game;
import com.company.gamestore.controller.GameController;
import com.company.gamestore.repository.GameRepository;
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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(GameController.class)
public class GameControllerTest {
    @MockBean
    private GameRepository gameRepository;

    @Autowired
    private MockMvc mockMvc;

    private Game game;

    ObjectMapper mapper = new ObjectMapper();

    @BeforeEach
    public void setUp() throws Exception{
        game = new Game();

        BigDecimal decimal = new BigDecimal("24.99");
        MathContext mc = new MathContext(4);

        game.setTitle("Super Monkey Ball");
        game.setEsrbRating("Everyone");
        game.setDescription("Call your friends and warn your neighbors, it's time to have a ball! Go bananas with 90+ stages, multi-player madness, and 7 cool ways to play! Equal parts \"party\" and \"game\", Super Monkey Ball could be the most \"well-rounded\" game you've ever played!");
        game.setPrice(decimal.round(mc));
        game.setStudio("Amusement Vision");
        game.setQuantity(5);
    }

    @Test
    void shouldCreateGame() throws Exception {
        String input = mapper.writeValueAsString(game);

        mockMvc.perform(post("/games")
                        .content(input)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    void shouldGetAllGames() throws Exception {
        mockMvc.perform(get("/games"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void shouldGetGameById() throws Exception {
        mockMvc.perform(get("/games/1"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void shouldUpdateGame() throws Exception {
        game.setTitle("Super Monkey Ball 2");
        String inputJson = mapper.writeValueAsString(game);

        mockMvc.perform(put("/games")
                        .content(inputJson)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    @Test
    void shouldDeleteGameById() throws Exception {
        mockMvc.perform(delete("/games/1"))
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    @Test
    void shouldGetGameByStudio() throws Exception {
        mockMvc.perform(get("/games/studio/Amusement+Vision"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void shouldGetGameByRating() throws Exception {
        mockMvc.perform(get("/games/rating/Everyone"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void shouldGetGameByTitle() throws Exception {
        mockMvc.perform(get("/games/title/Super+Monkey+Ball"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void shouldReturn422ErrorCode() throws Exception {
        Game game2 = new Game();

        String input = mapper.writeValueAsString(game2);

        mockMvc.perform(post("/games")
                        .content(input)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }
}
