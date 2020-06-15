package com.factsdemo.guineaPigFacts.services;

import com.factsdemo.guineaPigFacts.models.Fact;
import com.factsdemo.guineaPigFacts.repositories.FactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/***
 * Implements the business logic needed for the Fact part of the API.
 * Calls a FactRepository object which will run the data access calls.
 */
@Service
public class FactService {
    @Autowired
    private FactRepository factRepository;

    void deleteFact(Fact fact){
        factRepository.delete(fact);
    }

    void saveOrUpdateFact(Fact fact){
        factRepository.save(fact);
    }

    Optional<Fact> findAll(){
        return factRepository.findAllOrderByDateCreated();
    }

    public Optional<Fact> findById(String id) {
        return factRepository.findById(id);
    }

}
