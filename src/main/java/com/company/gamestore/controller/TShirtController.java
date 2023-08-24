package com.company.gamestore.controller;

import com.company.gamestore.model.TShirt;
import com.company.gamestore.repository.TShirtRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tshirts")
public class TShirtController {

    @Autowired
    private TShirtRepository tShirtRepository;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TShirt createTShirt(@RequestBody TShirt tShirt) {
        return tShirtRepository.save(tShirt);
    }

    @GetMapping("/{id}")
    public TShirt getTShirtById(@PathVariable int id) {
        Optional<TShirt> tShirt = tShirtRepository.findById(id);
        return tShirt.orElse(null);
    }

    @GetMapping
    public List<TShirt> getAllTShirts() {
        return tShirtRepository.findAll();
    }

    @PutMapping("/{id}")
    public TShirt updateTShirt(@PathVariable int id, @RequestBody TShirt tShirt) {
        if (tShirtRepository.existsById(id)) {
            tShirt.setId(id);
            return tShirtRepository.save(tShirt);
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public void deleteTShirt(@PathVariable int id) {
        tShirtRepository.deleteById(id);
    }

    @GetMapping("/color/{color}")
    public List<TShirt> getTShirtsByColor(@PathVariable String color) {
        return tShirtRepository.findByColor(color);
    }

    @GetMapping("/size/{size}")
    public List<TShirt> getTShirtsBySize(@PathVariable String size) {
        return tShirtRepository.findBySize(size);
    }
}
