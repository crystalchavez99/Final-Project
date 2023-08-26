package com.company.gamestore.repository;

import com.company.gamestore.model.Game;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
public class GameRepositoryTest {
    @Autowired
    private GameRepository gameRepository;

    private Game game;

    @BeforeEach
    public void setUp() {
        gameRepository.deleteAll();;

        game = new Game();

        BigDecimal decimal = new BigDecimal("24.99");
        MathContext mc = new MathContext(4);

        game.setTitle("Super Monkey Ball");
        game.setEsrbRating("Everyone");
        game.setDescription("Call your friends and warn your neighbors, it's time to have a ball! Go bananas with 90+ stages, multi-player madness, and 7 cool ways to play! Equal parts \"party\" and \"game\", Super Monkey Ball could be the most \"well-rounded\" game you've ever played!");
        game.setPrice(decimal.round(mc));
        game.setStudio("Amusement Vision");
        game.setQuantity(5);

        game = gameRepository.save(game);
    }

    @Test
    void shouldCreateGame(){
        Optional<Game> game2 = gameRepository.findById(game.getId());
        assertEquals(game2.get(), game);
    }

    @Test
    void shouldFindAllGames(){
        List<Game> games = gameRepository.findAll();
        assertEquals(1,games.size());
    }

    @Test
    void shouldFindGameById() {
        Optional<Game> game2 = gameRepository.findById(game.getId());
        assertEquals(game2.get(), game);
    }

    @Test
    void shouldUpdateGame(){
        game.setTitle("Super Monkey Ball 2");

        gameRepository.save(game);

        Optional<Game> game2 = gameRepository.findById(game.getId());
        assertEquals(game2.get(), game);
    }

    @Test
    void shouldDeleteGameById(){
        gameRepository.deleteById(game.getId());

        Optional<Game> game2 = gameRepository.findById(game.getId());
        assertFalse(game2.isPresent());
    }

    @Test
    void shouldFindGameByStudio() {
        List<Game> game2 = gameRepository.findByStudio(game.getStudio());
        assertEquals(1, game2.size());
    }

    @Test
    void shouldFindGameByRating() {
        List<Game> game2 = gameRepository.findByRating(game.getEsrbRating());
        assertEquals(1, game2.size());
    }

    @Test
    void shouldFindGameByTitle() {
        List<Game> game2 = gameRepository.findByTitle(game.getTitle());
        assertEquals(1, game2.size());
    }
}
