package com.factsdemo.guineaPigFacts.repositories;
import com.factsdemo.guineaPigFacts.models.Contact;
import org.springframework.data.mongodb.repository.MongoRepository;

/***
 * Implements the operations in ContactRepository by extending to the MongoRepository
 * Spring MongoDB creates the implementation when the application is ran
 ***/
public interface ContactRepository extends MongoRepository<Contact, String>  {
    Contact findByContactId(String id);
    Contact findByEmail(String email);
}
