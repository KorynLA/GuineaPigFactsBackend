package com.factsdemo.guineaPigFacts.controllerTests;

import com.factsdemo.guineaPigFacts.controllers.FactController;
import com.factsdemo.guineaPigFacts.models.Fact;
import com.factsdemo.guineaPigFacts.services.FactService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(FactController.class)
public class FactControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private FactService factService;

    private final String goodFactValue = "Guinea pigs are rodents.";
    private final String badFactValue = ". .";
    private final String goodFactId = "100000000000000000000001";
    private final String badFactId = "1";
    private final boolean approved = false;
    private String errorMessage;
    private Fact fact;
    private Fact badFact;
    List<Fact> factArray = new ArrayList<Fact>();
    private String factAsJsonString;

    private String badFactAsJsonString;
    @Before
    public void setup() {
        errorMessage = "Fact "+ badFactId +  " was not found";
        badFact = new Fact(badFactValue, approved);
        badFact.setId(badFactId);
        fact = new Fact(goodFactValue, approved);
        fact.setId(goodFactId);
        factArray.add(fact);

        factAsJsonString = "";
        badFactAsJsonString = "";
        try {
            factAsJsonString = new ObjectMapper().writeValueAsString(fact);
            badFactAsJsonString = new ObjectMapper().writeValueAsString(badFact);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    /**
     * Get controller test '/fact/' with a mocked database of 1 value
     */
    @Test
    public void getBackslashControllerTest_ShouldReturnStatusOk() throws Exception {
        Mockito.when(factService.findAll()).thenReturn(factArray);
        mockMvc.perform(get("/fact/")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].factValue", is(factArray.get(0).getFactValue())));
    }

    /**
     * Get controller test '/fact/home' with a mocked database of 1 value
     */
    @Test
    public void getHomeControllerTest_ShouldReturnStatusOk() throws Exception {
        Mockito.when(factService.findAll()).thenReturn(factArray);
        mockMvc.perform(get("/fact/home")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].factValue", is(factArray.get(0).getFactValue())));
    }

    /**
     * Get controller test '/fact' with a mocked database of 1 value
     */
    @Test
    public void getControllerTest_ShouldReturnStatusOk() throws Exception {
        Mockito.when(factService.findAll()).thenReturn(factArray);
        mockMvc.perform(get("/fact")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].factValue", is(factArray.get(0).getFactValue())));
    }

    /**
     * Get controller test '/fact/' with a mocked database no values
     */
    @Test
    public void getEmptyControllerTest_ShouldReturnStatusOk() throws Exception {
        List<Fact> emptyFactList = new ArrayList<Fact>();
        Mockito.when(factService.findAll()).thenReturn(emptyFactList);
        mockMvc.perform(get("/fact")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));
    }

    /**
     * Get controller test '/fact/{id}' with an Id that is mocked to exist
     */
    @Test
    public void getGoodIdControllerTest_ShouldReturnStatusOk() throws Exception {
        Mockito.when(factService.findById(goodFactId)).thenReturn(java.util.Optional.ofNullable(fact));
        mockMvc.perform(get("/fact/{id}", goodFactId)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.factValue", is(fact.getFactValue())));
    }

    /**
     * Get controller test '/fact/{id}' with a Id that is mocked to not exist
     */
    @Test
    public void getInvalidIdControllerTest_ShouldReturnIdIsNotFound() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/fact/{id}", badFactId)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andReturn();
        assertEquals(mvcResult.getResponse().getContentAsString(), errorMessage);
    }

    /**
     * Post controller test '/fact/' with a Fact object that fits all variable validation criteria
     */
    @Test
    public void postControllerTest_ShouldReturnStatusCreated() throws Exception {
        mockMvc.perform(post("/fact/")
                .content(factAsJsonString)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists());
    }

    /**
     * Post controller test '/fact/' with a Fact object that doesn't fit all variable validation criteria
     */
    @Test
    public void postInvalidBodyControllerTest_ShouldReturnIsBadRequest() throws Exception {
        mockMvc.perform(post("/fact/")
                .content(badFactAsJsonString)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    /**
     * Delete controller test '/fact/{id}' with an Id that is mocked to exist
     */
    @Test
    public void deleteControllerTest_ShouldReturnStatusOk() throws Exception {
        Mockito.when(factService.findById(goodFactId)).thenReturn(java.util.Optional.ofNullable(fact));
        Mockito.doNothing().when(factService).delete(goodFactId);

        mockMvc.perform(delete("/fact/{id}", goodFactId)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    /**
     * Delete controller test '/fact/{id}' with an Id that is mocked to not exist
     */
    @Test
    public void deleteInvalidIdControllerTest_ShouldReturnIdIsNotFound() throws Exception {
        MvcResult mvcResult = mockMvc.perform(delete("/fact/{id}", badFactId)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andReturn();
        assertEquals(mvcResult.getResponse().getContentAsString(), errorMessage);
    }

    /**
     * Put controller test '/fact/{id}' with an Id that is mocked to exist
     */
    @Test
    public void putControllerTest_ShouldReturnStatusOk() throws Exception {
        String newFactAsJsonString = "";
        try {
            newFactAsJsonString = new ObjectMapper().writeValueAsString(new Fact(goodFactValue, true));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        Mockito.when(factService.findById(goodFactId)).thenReturn(java.util.Optional.ofNullable(fact));
        mockMvc.perform(put("/fact/{id}", goodFactId)
                .content(newFactAsJsonString)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(goodFactId))
                .andExpect(MockMvcResultMatchers.jsonPath("$.factValue").value(goodFactValue))
                .andExpect(MockMvcResultMatchers.jsonPath("$.approved").value("true"));
    }

    /**
     * Put controller test '/fact/{id}' with an Id that is mocked to not exist
     */
    @Test
    public void putInvalidIdControllerTest_ShouldReturnIdIsNotFound() throws Exception {
        MvcResult mvcResult = mockMvc.perform(put("/fact/{id}", badFactId)
                .content(factAsJsonString)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andReturn();
        assertEquals(mvcResult.getResponse().getContentAsString(), errorMessage);
    }

    /**
     * Put controller test '/user/{id}' with an Id that is mocked to exist but an invalid Fact object
     */
    @Test
    public void putInvalidBodyControllerTest_ShouldReturnIsBadRequest() throws Exception {
        mockMvc.perform(put("/fact/{id}", goodFactId)
                .content(badFactAsJsonString)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }
}
