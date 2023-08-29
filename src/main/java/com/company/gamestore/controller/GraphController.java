package com.company.gamestore.controller;

import com.company.gamestore.model.Console;
import com.company.gamestore.model.Game;
import com.company.gamestore.repository.ConsoleRepository;
import com.company.gamestore.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Optional;

@Controller
public class GraphController {

    @Autowired
    ConsoleRepository consoleRepository;

    @Autowired
    GameRepository gameRepository;

    @QueryMapping
    public List<Game> games(){
        return gameRepository.findAll();
    }

    @QueryMapping
    public Optional<Game> findGameById(@Argument Integer game_id){
        return gameRepository.findById(game_id);
    }

    @QueryMapping
    public List<Game> findGamesByTitle(@Argument String title){
        return gameRepository.findByTitle(title);
    }

    @QueryMapping
    public List<Game> findGamesByEsrbRating(@Argument String esrb_rating) {
        return gameRepository.findByEsrbRating(esrb_rating);
    }

    @QueryMapping
    public List<Game> findGamesByStudio(@Argument String studio) {
        return gameRepository.findByStudio(studio);
    }

    @QueryMapping
    public List<Console> consoles() {
        return consoleRepository.findAll();
    }

    @QueryMapping
    public Optional<Console> findConsoleById(@Argument Integer console_id) {
        return consoleRepository.findById(console_id);
    }

    @QueryMapping
    public List<Console> findConsolesByManufacturer(@Argument String manufacturer) {
        return consoleRepository.findByManufacturer(manufacturer);
    }
}
