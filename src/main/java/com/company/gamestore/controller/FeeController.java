package com.company.gamestore.controller;

import com.company.gamestore.model.Fee;
import com.company.gamestore.repository.FeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
public class FeeController {

    @Autowired
    FeeRepository feeRepository;

    @PostMapping("/fees")
    @ResponseStatus(HttpStatus.CREATED)
    public Fee createFee(@RequestBody @Valid Fee fee) {
        return feeRepository.save(fee);
    }

    @PutMapping("/fees")
    @ResponseStatus(HttpStatus.OK)
    public Fee updateFee(@RequestBody @Valid Fee fee) {
        return feeRepository.save(fee);
    }

    @GetMapping("/fees/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<Fee> getFeeById(@PathVariable String id) {
        return feeRepository.findById(id);
    }


    @GetMapping("/fees")
    @ResponseStatus(HttpStatus.OK)
    public List<Fee> getAllFees() {
        return feeRepository.findAll();
    }

    @DeleteMapping("/fees/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteFee(@PathVariable String id) {
        feeRepository.deleteById(id);
    }
}
