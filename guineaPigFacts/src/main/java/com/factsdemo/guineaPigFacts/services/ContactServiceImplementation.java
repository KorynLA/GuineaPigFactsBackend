package com.factsdemo.guineaPigFacts.services;
import com.factsdemo.guineaPigFacts.models.Contact;
import com.factsdemo.guineaPigFacts.repositories.ContactRepository;
import org.springframework.stereotype.Service;

/***
 * Implements the business logic needed for the Contact part of the API.
 * Calls a ContactRepository object which will run the data access calls.
 */
@Service
public class ContactServiceImplementation {
    private ContactRepository contactRepository;

    Contact findById(String id){
        return contactRepository.findByContactId(id);
    }

    Contact findByEmail(String email){
        return contactRepository.findByEmail(email);
    }

    void saveOrUpdateContact(Contact contact){
        contactRepository.save(contact);
    }
}
