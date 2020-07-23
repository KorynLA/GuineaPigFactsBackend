package com.factsdemo.guineaPigFacts.controllerTests;

import com.factsdemo.guineaPigFacts.controllers.FactController;
import com.factsdemo.guineaPigFacts.services.FactService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

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
    /*
    * Get controller test '/fact/'
     */
    @Test
    public void getBackslashControllerTest() {
    }
    /*
     * Get controller test '/fact/home'
     */
    @Test
    public void getHomeControllerTest() {
    }
    /*
     * Get controller test '/fact'
     */
    @Test
    public void getControllerTest() {
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
