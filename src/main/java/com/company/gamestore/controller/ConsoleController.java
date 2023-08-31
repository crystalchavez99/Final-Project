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
@RequestMapping("/consoles")
public class ConsoleController {

    @Autowired
    private ConsoleRepository consoleRepository;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Console createConsole(@RequestBody @Valid Console console) {
        return consoleRepository.save(console);
    }

    @GetMapping("/{id}")
    public Console getConsoleById(@PathVariable int id) {
        Optional<Console> console = consoleRepository.findById(id);
        return console.orElse(null);
    }

    @GetMapping
    public List<Console> getAllConsoles() {
        return consoleRepository.findAll();
    }

    @PutMapping("/{id}")
    public Console updateConsole(@PathVariable int id, @RequestBody Console console) {
        if (consoleRepository.existsById(id)) {
            console.setId(id);
            return consoleRepository.save(console);
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public void deleteConsole(@PathVariable int id) {
        consoleRepository.deleteById(id);
    }

    @GetMapping("/manufacturer/{manufacturer}")
    public List<Console> getConsolesByManufacturer(@PathVariable String manufacturer) {
        return consoleRepository.findByManufacturer(manufacturer);
    }
}
