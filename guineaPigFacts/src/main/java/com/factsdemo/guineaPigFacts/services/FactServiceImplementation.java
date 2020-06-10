package com.factsdemo.guineaPigFacts.services;
import com.factsdemo.guineaPigFacts.models.Fact;
import com.factsdemo.guineaPigFacts.repositories.FactRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import java.util.List;

/***
 * Implements the business logic needed for the Fact part of the API.
 * Calls a FactRepository object which will run the data access calls.
 */
@Service
public class FactServiceImplementation {
    private FactRepository factRepository;

    void deleteFact(Fact fact){
        factRepository.delete(fact);
    }

    void saveOrUpdateFact(Fact fact){
        factRepository.save(fact);
    }

    List<Fact> findAll(){
        return factRepository.findAllOrderByDateCreated();
    }

    Fact findByFactId(@Param("id") String factId){
        return factRepository.findByFactId(factId);
    }
}
