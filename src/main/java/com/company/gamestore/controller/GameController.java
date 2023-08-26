package com.company.gamestore.controller;

import com.company.gamestore.model.Game;
import com.company.gamestore.model.Invoice;
import com.company.gamestore.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Optional;

@RestController
public class GameController {
    @Autowired
    GameRepository gameRepository;

    @GetMapping("/games")
    public List<Game> getAllGames() {
        return gameRepository.findAll();
    }

    //create game
    @PostMapping("/games")
    @ResponseStatus(HttpStatus.CREATED)
    public Game createGame(@RequestBody Game game) {
        return gameRepository.save(game);
    }

    // read game by id
    @GetMapping("/games/{id}")
    public Game getGameById(@PathVariable int id) {
        Optional<Game> returnVal = gameRepository.findById(id);
        if (returnVal.isPresent()) {
            return returnVal.get();
        }
        return null;
    }

    //update game
    @PutMapping("/games")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateGame(@RequestBody Game game) {
        gameRepository.save(game);
    }

    //delete game by id
    @DeleteMapping("/games/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteGameById(@PathVariable int id) {
        gameRepository.deleteById(id);
    }

    //read game by studio
    @GetMapping("/games/studio/{studio}")
    public List<Game> getGamesByStudio(@PathVariable String studio) {
        return gameRepository.findByStudio(studio);
    }

    //read game by esrb rating
    @GetMapping("/games/rating/{esrbRating}")
    public List<Game> getGamesByRating(@PathVariable String esrbRating) {
        return gameRepository.findByRating(esrbRating);
    }

    //read game by title
    @GetMapping("/games/title/{title}")
    public List<Game> getGamesByTitle(@PathVariable String title) {
        return gameRepository.findByTitle(title);
    }
}
