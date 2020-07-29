package com.factsdemo.guineaPigFacts.controllers;

import com.factsdemo.guineaPigFacts.errorHandling.IdNotFoundException;
import com.factsdemo.guineaPigFacts.models.Fact;
import com.factsdemo.guineaPigFacts.services.FactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
* Rest endpoints
* GET /fact/
** Retrieves all facts in the Fact collection
* GET /fact/{id}
** Retrieves fact with the ID passed in the URL path
* POST /fact/
** Adds a fact to the Fact collection
* DELETE /fact/{id}
** Deletes a document with the ID passed in the URL path
*/

@RestController
@RequestMapping("/fact")
public class FactController {
    @Autowired
    private FactService factService;

    /**
     * Retrieves fact with the ID passed in the URL path
     * @param id as a String
     * @return Fact object
     * @throws IdNotFoundException
     */
    @GetMapping("/{id}")
    public Fact findById(@PathVariable("id") String id)  {
        Fact fact = factService.findById(id).orElseThrow(() -> new IdNotFoundException(id, "Fact"));
        return fact;
    }

    /**
     * Retrieves all facts in the Fact collection
     * @return ResponseEntity with List<Fact> and the HttpStatus
     */
    @GetMapping(value = {"", "/", "/home"})
    public ResponseEntity<?>  findAll() {
        List<Fact> facts = factService.findAll();
        return new ResponseEntity<List<Fact>>(facts, HttpStatus.OK);
    }

    /**
     * Adds a fact to the Fact collection
     * @param fact as a Fact object
     * @return ResponseEntity with the fact and the HttpStatus
     */
    @PostMapping("/")
    public ResponseEntity<?> saveOrUpdate(@Valid @RequestBody Fact fact) {
        factService.saveFact(fact);
        return new ResponseEntity<Fact>(fact, HttpStatus.CREATED);
    }

    /**
     * Updates a fact in the Fact collection
     * @param updatedFact as a Fact object
     * @param id as a String
     * @return ResponseEntity with the updated fact and the HttpStatus
     */
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable("id") String id, @Valid @RequestBody Fact updatedFact) {
        factService.findById(id).orElseThrow(() -> new IdNotFoundException(id, "Fact"));
        updatedFact.setId(id);
        factService.saveFact(updatedFact);
        return new ResponseEntity<Fact>(updatedFact, HttpStatus.OK);
    }

    /**
     * Deletes a document with the ID passed in the URL path
     * @param id as a String
     * @return ResponseEntity with a message and the HttpStatus
     * @throws IdNotFoundException
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") String id) throws IdNotFoundException {
        factService.findById(id).orElseThrow(() -> new IdNotFoundException(id, "Fact"));
        factService.delete(id);
        return new ResponseEntity<String>("Deleted", HttpStatus.OK);
    }

}
