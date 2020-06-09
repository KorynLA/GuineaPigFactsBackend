package com.factsdemo.guineaPigFacts.services;

public class ContactService {
    Contact findById(String id);
    Contact findByEmail(String email);
    void saveOrUpdateContact(Contact contact);
}
