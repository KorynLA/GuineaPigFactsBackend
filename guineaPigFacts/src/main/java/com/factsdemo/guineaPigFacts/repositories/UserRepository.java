package com.factsdemo.guineaPigFacts.repositories;
import com.factsdemo.guineaPigFacts.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;

/***
 * Implements the operations in UserRepository by extending to the MongoRepository
 * Spring MongoDB creates the implementation when the application is ran
 ***/
public interface UserRepository extends MongoRepository<User, String> {
    User findByUserName(String userName);
    User findByEmail(String email);
}
