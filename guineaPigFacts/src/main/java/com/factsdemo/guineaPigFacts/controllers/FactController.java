package com.factsdemo.guineaPigFacts.controllers;

import com.factsdemo.guineaPigFacts.errorHandling.IdNotFoundException;
import com.factsdemo.guineaPigFacts.models.Fact;
import com.factsdemo.guineaPigFacts.services.FactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/*
* Rest endpoints
* GET /fact/
** Retrieves all facts in the Fact collection
* GET /fact/{id}
** Retrieves fact with the ID passed in the URL path
* POST /fact/add
** Adds a fact to the Fact collection
* DELETE /fact/delete/{id}
** Deletes a document with the ID passed in the URL path
*/

@RestController
@RequestMapping("/fact")
public class FactController {
    @Autowired
    private FactService factService;

    @GetMapping("/{id}")
    public Fact findById(@PathVariable("id") String id)  {
        Fact fact = factService.findById(id).orElseThrow(() -> new IdNotFoundException(id, "Fact"));
        return fact;
    }
    @GetMapping(value = {"", "/", "/home"})
    public ResponseEntity<?>  findAll() throws Exception {
        List<Fact> facts = factService.findAll();
        if(facts.isEmpty()) {
            return new ResponseEntity<String>("There are no facts", HttpStatus.OK);
        }
        else {
            List<String> factVal = new ArrayList<String>();
            for(Fact fact : facts) {
                factVal.add(fact.getFactValue());
            }
            return new ResponseEntity<List<String>>(factVal, HttpStatus.OK);
        }
    }

    @PostMapping("/")
    public ResponseEntity<?> saveOrUpdate(@RequestBody Fact fact) {
        factService.saveOrUpdateFact(fact);
        return new ResponseEntity<String>("Added", HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") String id) throws IdNotFoundException {
        factService.findById(id).orElseThrow(() -> new IdNotFoundException(id, "Fact"));
        factService.delete(id);
        return new ResponseEntity<String>("Deleted", HttpStatus.OK);
    }

}
