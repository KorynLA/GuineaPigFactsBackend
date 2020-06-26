package com.factsdemo.guineaPigFacts.controllerTests;

import com.factsdemo.guineaPigFacts.controllers.FactController;
import com.factsdemo.guineaPigFacts.models.Fact;
import org.apache.tomcat.util.http.parser.MediaType;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.net.ServerSocket;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(FactController.class)
public class FactControllerTests {
    @Autowired
    private MockMvc mvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    private Fact fact1;
    private Fact fact2;
    private final String id1 = "test1_id";
    private final String factValue1 = "Guinea pigs are small";
    private final String id2 = "test2_id";
    private final String factValue2 = "Guinea pigs drink water";

    @MockBean
    FactController factController;

    @Before
    public void setup() {
        //mvc = webAppContextSetup(webApplicationContext).build();
        fact1 = new Fact(factValue1);
        fact1.setId(id1);
        fact2 = new Fact(factValue2);
        fact2.setId(id2);
    }

    @Test
    public void factLoads() throws Exception{
        assertThat(factController).isNotNull();
    }

    @Test
    public void getFactByID() throws Exception {
        this.mvc.perform(get("/fact/"+id1)
                .accept(MediaType.parseMediaType))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.id").value(id1));
    }



}
