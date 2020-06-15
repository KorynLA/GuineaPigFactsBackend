package com.factsdemo.guineaPigFacts.repositories;

import com.factsdemo.guineaPigFacts.models.Fact;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/***
 * Implements the operations in FactRepository by extending to the MongoRepository
 * Spring MongoDB creates the implementation when the application is ran
 ***/
@Repository
public interface FactRepository extends MongoRepository<Fact, String> {
    Optional<Fact> findAllOrderByDateCreated();
}
