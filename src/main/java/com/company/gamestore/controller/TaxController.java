package com.company.gamestore.controller;

import com.company.gamestore.model.Tax;
import com.company.gamestore.repository.TaxRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
public class TaxController {

    @Autowired
    TaxRepository taxRepository;

    //create tax
    @PostMapping("/taxes")
    @ResponseStatus(HttpStatus.CREATED)
    public Tax createTax(@RequestBody @Valid Tax tax) {
        return taxRepository.save(tax);
    }

    //update tax
    @PutMapping("/taxes")
    @ResponseStatus(HttpStatus.OK)
    public Tax updateTax(@RequestBody @Valid Tax tax) {
        return taxRepository.save(tax);
    }

    //get tax by id
    @GetMapping("/taxes/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<Tax> getTaxById(@PathVariable String id) {
        return taxRepository.findById(id);
    }

    //get all tax rates
    @GetMapping("/taxes")
    @ResponseStatus(HttpStatus.OK)
    public List<Tax> getAllTaxRates() {
        return taxRepository.findAll();
    }

    //delete tax by id
    @DeleteMapping("/taxes/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTax(@PathVariable String id) {
        taxRepository.deleteById(id);
    }
}
