package com.factsdemo.guineaPigFacts.serviceTests;

import com.factsdemo.guineaPigFacts.models.Fact;
import com.factsdemo.guineaPigFacts.repositories.FactRepository;
import com.factsdemo.guineaPigFacts.services.FactService;
import com.factsdemo.guineaPigFacts.services.implementations.FactServiceImplementation;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
public class FactServiceTest {

    @TestConfiguration
    static class FactServiceTestContextConfiguration {
        @Bean
        public FactService factService() {
            return new FactServiceImplementation();
        }
    }
    @Autowired
    FactService factService;
    @MockBean
    FactRepository factRepository;
    
    private Fact fact1;
    private Fact fact2;
    private final String factVal1 = "dummy1";
    private final String idVal1 = "two1";
    private final String factVal2 = "dummy2";
    private final String idVal2 = "two2";
    private final String idVal3 = "test";
    private final  boolean approved = false;
    private final String date = "09/08/2020";
    private final ArrayList<Fact> facts = new ArrayList<Fact>();

    @Before
    public void setup() {
        fact1 = new Fact(factVal1, approved);
        fact1.setId(idVal1);
        fact2 = new Fact(factVal2, approved);
        fact2.setId(idVal2);
        facts.add(fact1);
        facts.add(fact2);
    }

    /**
     * Test to verify fact is saved
     */
    @Test
    public void factSaveTest() {
        factService.saveFact(fact1);
        Mockito.verify(factRepository, Mockito.times(1)).save(fact1);
    }

    /**
     * Test to verify List of facts are returned
     */
    @Test
    public void factFindAllTest(){
        Mockito.when(factRepository.findAll()).thenReturn(facts);
        ArrayList<Fact> foundFacts = (ArrayList<Fact>) factService.findAll();
        assertEquals(2, foundFacts.size());
    }
    /**
     * Test to verify null is returned when there are no facts
     */
    @Test
    public void factFindNoneTest(){
        Mockito.when(factRepository.findAll()).thenReturn(null);
        ArrayList<Fact> foundFacts = (ArrayList<Fact>) factService.findAll();
        assertNull(foundFacts);
    }


    /**
     * Test to verify fact with ID is deleted once from database
     */
    @Test
    public void factDeleteByIdTest() {
        factService.delete(fact1.getId());
        Mockito.verify(factRepository, Mockito.times(1)).deleteById(fact1.getId());
    }

    /**
     * Test to verify fact is returned when an id in the database is found
     */
    @Test
    public void factFindByIdTestGoodID() {
        Mockito.when(factRepository.findById(fact1.getId())).thenReturn(Optional.of(fact1));
        Optional<Fact> found = factService.findById(idVal1);
        assertTrue(found.isPresent());
    }

    /**
     * Test to verify null is returned when an id is not found in the database
     */
    @Test
    public void factFindByIdTestBadID() {
        Mockito.when(factRepository.findById(idVal3)).thenReturn(null);
        Optional<Fact> found = factService.findById(idVal3);
        assertNull(found);
    }
}
