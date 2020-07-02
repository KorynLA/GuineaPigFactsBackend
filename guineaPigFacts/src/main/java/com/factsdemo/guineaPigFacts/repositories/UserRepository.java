package com.factsdemo.guineaPigFacts.repositories;
import com.factsdemo.guineaPigFacts.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;

/***
 * Implements the operations in UserRepository by extending to the MongoRepository
 * Spring MongoDB creates the implementation when the application is ran
 ***/
public interface UserRepository extends MongoRepository<User, String> {
    /**
     * Finds the User collection by its userName
     * @param userName as a String
     * @return User
     */
    User findByUserName(String userName);

    /**
     * Finds the User associated with an email
     * @param email as a String
     * @return User
     */
    User findByContactInfo_Email(String email);
}
