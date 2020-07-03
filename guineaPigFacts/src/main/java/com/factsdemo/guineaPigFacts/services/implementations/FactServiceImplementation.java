package com.factsdemo.guineaPigFacts.services.implementations;

import com.factsdemo.guineaPigFacts.models.Fact;
import com.factsdemo.guineaPigFacts.repositories.FactRepository;
import com.factsdemo.guineaPigFacts.services.FactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/***
 * Implements the business logic needed for the Fact part of the API.
 * Calls a FactRepository object which will run the data access calls.
 */
@Service
public class FactServiceImplementation implements FactService {
    @Autowired
    private FactRepository factRepository;

    public void delete(String id){
        factRepository.deleteById(id);
    }

    public void saveOrUpdateFact(Fact fact){
        factRepository.save(fact);
    }

    public List<Fact> findAll(){
        return factRepository.findAll();
    }

    public Optional<Fact> findById(String id) {
        return factRepository.findById(id);
    }

}
