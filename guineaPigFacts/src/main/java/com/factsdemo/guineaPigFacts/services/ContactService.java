package com.factsdemo.guineaPigFacts.services;

import com.factsdemo.guineaPigFacts.models.Contact;
import com.factsdemo.guineaPigFacts.repositories.ContactRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

/***
 * Implements the business logic needed for the Contact part of the API.
 * Calls a ContactRepository object which will run the data access calls.
 */
@Service
public class ContactService {
    private ContactRepository contactRepository;

    Optional<Contact> findById(String id){
        return contactRepository.findById(id);
    }

    Optional<Contact> findByEmail(String email){
        return contactRepository.findByEmail(email);
    }

    void saveOrUpdateContact(Contact contact){
        contactRepository.save(contact);
    }
}
