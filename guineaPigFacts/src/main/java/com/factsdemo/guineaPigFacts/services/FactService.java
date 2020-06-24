package com.factsdemo.guineaPigFacts.services;

import com.factsdemo.guineaPigFacts.models.Fact;
import com.factsdemo.guineaPigFacts.repositories.FactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/***
 * Implements the business logic needed for the Fact part of the API.
 * Calls a FactRepository object which will run the data access calls.
 */
@Service
public class FactService {
    @Autowired
    private FactRepository factRepository;

    public void delete(String id){
        factRepository.deleteById(id);
    }

    public void saveOrUpdateFact(Fact fact){
        factRepository.save(fact);
    }

    public List<Fact> findAll(){
        if(factRepository.findAll().size() == 0) {
            throw new IllegalArgumentException("The list is empty");
        }
        return factRepository.findAll();
    }

    public Optional<Fact> findById(String id) {
        return factRepository.findById(id);
    }

}
