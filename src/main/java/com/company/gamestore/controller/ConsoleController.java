package com.company.gamestore.controller;

import com.company.gamestore.model.Console;
import com.company.gamestore.repository.ConsoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
public class ConsoleController {
    @Autowired
    ConsoleRepository consoleRepo;

    //create console
    @PostMapping(path = "/console")
    @ResponseStatus(HttpStatus.CREATED)
    public Console createConsole(@RequestBody @Valid Console console) {
        return consoleRepo.save(console);
    }

    //update console
    @PutMapping("/console")
    @ResponseStatus(HttpStatus.CREATED)
    public Console updateConsole(@RequestBody @Valid Console console) {
        return consoleRepo.save(console);
    }

    //delete console
    @DeleteMapping("/console/{consoleId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteConsole(@PathVariable Integer consoleId) {
        consoleRepo.deleteById(consoleId);
    }

    //get all consoles
    @GetMapping("/console")
    @ResponseStatus(HttpStatus.OK)
    public List<Console> getAllConsoles() {
        return consoleRepo.findAll();
    }

    //get console by id
    @GetMapping("/console/{consoleId}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<Console> findConsoleById(@PathVariable Integer consoleId) {
        return consoleRepo.findById(consoleId);
    }

    //get console by manufacturer
    @GetMapping("/console/manufacturer/{manufacturer}")
    @ResponseStatus(HttpStatus.OK)
    public List<Console> findConsolesByManufacturer(@PathVariable String manufacturer) {
        return consoleRepo.findByManufacturer(manufacturer);
    }
}
