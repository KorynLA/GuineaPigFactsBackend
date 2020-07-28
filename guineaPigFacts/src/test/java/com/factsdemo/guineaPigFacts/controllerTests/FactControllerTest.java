package com.factsdemo.guineaPigFacts.controllerTests;

import com.factsdemo.guineaPigFacts.controllers.FactController;
import com.factsdemo.guineaPigFacts.models.Fact;
import com.factsdemo.guineaPigFacts.services.FactService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(FactController.class)
//@Import(SpringSecurityConfig.class)
public class FactControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private FactService factService;

    private final String goodFactValue = "Guinea pigs are rodents.";
    private final String badFactValue = ". .";
    private final String goodId = "100000000000000000000000";
    private final String badId = "1";
    private final boolean approved = false;
    private Fact fact;
    List<Fact> factArray = new ArrayList<Fact>();
    @Before
    public void setup() {
        Fact fact = new Fact(goodFactValue, approved);
        fact.setId(goodId);
        factArray.add(fact);
    }
    /*
    * Get controller test '/fact/'
     */
    @Test
    public void getBackslashControllerTest() throws Exception {
        Mockito.when(factService.findAll()).thenReturn(factArray);
        mockMvc.perform(get("/fact/")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].factValue", is(factArray.get(0).getFactValue())));
    }
    /*
     * Get controller test '/fact/home'
     */
    @Test
    public void getHomeControllerTest() throws Exception {
        Mockito.when(factService.findAll()).thenReturn(factArray);
        mockMvc.perform(get("/fact/home")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].factValue", is(factArray.get(0).getFactValue())));
    }
    /*
     * Get controller test '/fact'
     */
    @Test
    public void getControllerTest() throws Exception {
        Mockito.when(factService.findAll()).thenReturn(factArray);
        mockMvc.perform(get("/fact")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].factValue", is(factArray.get(0).getFactValue())));
    }
    @Test
    public void getEmptyControllerTest() throws Exception {
        List<Fact> emptyFactList = new ArrayList<Fact>();
        Mockito.when(factService.findAll()).thenReturn(emptyFactList);
        mockMvc.perform(get("/fact")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));
    }
    /*
     * Get controller test '/{id}' with good id
     */
    @Test
    public void getGoodIdControllerTest() {

    }
    /*
     * Get controller test '/{id}' with bad id
     */
    @Test
    public void getBadIdControllerTest() {
    }
    /*
     * Post controller test '/'
     */
    @Test
    public void postControllerTest() {
    }
    /*
     * Delete controller test '/'
     */
    @Test
    public void deleteControllerTest() {
    }
}
