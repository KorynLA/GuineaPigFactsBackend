package com.factsdemo.guineaPigFacts.serviceTests;

import com.factsdemo.guineaPigFacts.models.Fact;
import com.factsdemo.guineaPigFacts.repositories.FactRepository;
import com.factsdemo.guineaPigFacts.services.FactService;
import org.junit.Assert;
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
            return new FactService();
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
    private final ArrayList<Fact> facts = new ArrayList<Fact>();
    //Mocking support for repository

    @Before
    public void setup() {
        fact1 = new Fact(factVal1);
        fact1.setId(idVal1);
        fact2 = new Fact(factVal2);
        fact2.setId(idVal2);
        facts.add(fact1);
        facts.add(fact2);
    }
    @Test
    public void factSaveOrUpdateTest() {
        factService.saveOrUpdateFact(fact1);
        Mockito.verify(factRepository, Mockito.times(1)).save(fact1);
    }
    @Test
    public void factFindAllTest(){
        Mockito.when(factRepository.findAll()).thenReturn(facts);
        ArrayList<Fact> foundFacts = (ArrayList<Fact>) factService.findAll();
        assertEquals(2, foundFacts.size());
    }

    @Test
    public void factDeleteByIdTest() {
        factService.delete(fact1.getId());
        Mockito.verify(factRepository, Mockito.times(1)).deleteById(fact1.getId());
    }
    @Test
    public void factFindByIdTest() {
        Mockito.when(factRepository.findById(fact1.getId())).thenReturn(Optional.of(fact1));
        Optional<Fact> found = factService.findById(idVal1);
        assertTrue(found.isPresent());
    }
}
