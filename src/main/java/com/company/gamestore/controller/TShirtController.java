package com.company.gamestore.controller;

import com.company.gamestore.model.Console;
import com.company.gamestore.model.TShirt;
import com.company.gamestore.repository.TShirtRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
public class TShirtController {

    @Autowired
    private TShirtRepository tShirtRepository;

    //get all tshirts
    @GetMapping("/tshirts")
    public List<TShirt> getAllTShirts() {
        return tShirtRepository.findAll();
    }

    //create tshirt
    @PostMapping("/tshirts")
    @ResponseStatus(HttpStatus.CREATED)
    public TShirt createTShirt(@RequestBody @Valid TShirt tShirt) {
        return tShirtRepository.save(tShirt);
    }

    //get tshirt by id
    @GetMapping("tshirts/{id}")
    public TShirt getTShirtById(@PathVariable int id) {
        Optional<TShirt> returnVal = tShirtRepository.findById(id);
        if (returnVal.isPresent()) {
            return returnVal.get();
        }
        return null;
    }

    //update tshirt
    @PutMapping("/tshirts")
    @ResponseStatus(HttpStatus.CREATED)
    public TShirt updateConsole(@RequestBody @Valid TShirt tShirt) {
        return tShirtRepository.save(tShirt);
    }

    //delete tshirt by id
    @DeleteMapping("tshirts/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTShirt(@PathVariable int id) {
        tShirtRepository.deleteById(id);
    }

    //get tshirt by color
    @GetMapping("tshirts/color/{color}")
    public List<TShirt> getTShirtsByColor(@PathVariable String color) {
        return tShirtRepository.findByColor(color);
    }

    //get tshirt by size
    @GetMapping("tshirts/size/{size}")
    public List<TShirt> getTShirtsBySize(@PathVariable String size) {
        return tShirtRepository.findBySize(size);
    }
}
