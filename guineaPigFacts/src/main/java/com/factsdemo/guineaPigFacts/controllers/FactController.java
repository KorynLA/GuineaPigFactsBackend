package com.factsdemo.guineaPigFacts.controllers;

import com.factsdemo.guineaPigFacts.models.Fact;
import com.factsdemo.guineaPigFacts.services.FactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/fact")
public class FactController {
    @Autowired
    private FactService factService;

    @GetMapping("/{id}")
    public Optional<Fact> findById(@PathVariable("id") String id) {
        return factService.findById(id);
    }
    @GetMapping("/")
    public List<Fact> findAll() {
        return factService.findAll();
    }
    @PostMapping("/add")
    public ResponseEntity<?> saveOrUpdate(@RequestBody Fact fact) {
        factService.saveOrUpdateFact(fact);
        return new ResponseEntity<String>("Added",HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") String id) {
        factService.delete(id);
        return new ResponseEntity<String>("Added",HttpStatus.OK);
    }

}
