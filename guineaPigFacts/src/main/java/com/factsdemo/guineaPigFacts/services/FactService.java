package com.factsdemo.guineaPigFacts.services;

import com.factsdemo.guineaPigFacts.models.Fact;

import java.util.List;
import java.util.Optional;

public interface FactService {
    /**
     * Deletes a Fact based on the id provided. Nothing is returned.
     * @param id as a String
     */
    public void delete(String id);

    /**
     * Updates or creates a new Fact based on the Fact provided. Nothing is returned
     * @param fact as a Fact object
     */
    public void saveOrUpdateFact(Fact fact);

    /**
     * Returns all of the facts that are in the database.
     * @return Fact objects in a List
     */
    public List<Fact> findAll();

    /**
     * Returns the Fact object or null of a given String id.
     * @param id as a String
     * @return Fact or null
     */
    public Optional<Fact> findById(String id);

}
