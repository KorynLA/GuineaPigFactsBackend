package com.factsdemo.guineaPigFacts.repositories;
import com.factsdemo.guineaPigFacts.models.Fact;
import org.springframework.data.mongodb.repository.MongoRepository;

public class FactRepository extends MongoRepository<Fact, String> {
    Fact findByFactId(@Param("id") String factId);
    List<Fact> findAllOrderBydateCreated();

}
