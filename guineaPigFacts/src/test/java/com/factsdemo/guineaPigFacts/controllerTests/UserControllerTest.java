package com.factsdemo.guineaPigFacts.controllerTests;

import com.factsdemo.guineaPigFacts.controllers.UserController;
import com.factsdemo.guineaPigFacts.models.Contact;
import com.factsdemo.guineaPigFacts.models.User;
import com.factsdemo.guineaPigFacts.services.UserService;
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


import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private UserService userService;

    private final String goodUserId = "100000000000000000000001";
    private final String badUserId = "1";
    private final String goodUserName = "testUser_1";
    private final String badUserName = "t11#";
    private final String goodUserPassword = "testPassword1";
    private final String badUserPassword = "testPassword";

    private final String goodUserEmail = "test@gmail.com";
    private final boolean dailyUpdate = true;

    private Contact goodContact;
    private User goodUser;
    private User badUser;

    private String errorMessageId = "";
    List<User> userArray = new ArrayList<User>();

    @Before
    public void setup() {
        errorMessageId = "User "+ badUserId +  " was not found";
        goodContact = new Contact(goodUserEmail, dailyUpdate);
        goodUser = new User(goodUserName, goodUserPassword, goodContact);
        goodUser.setId(goodUserId);
        badUser = new User(badUserName, badUserPassword, goodContact);
    }

    /**
     * Get controller test '/user/{id}' with a userId that is mocked to exist
     */
    @Test
    public void getByIdControllerTest_ShouldReturnStatusOk() throws Exception {
        when(userService.findById(goodUserId)).thenReturn(java.util.Optional.ofNullable(goodUser));
        mockMvc.perform(get("/user/{id}", goodUserId)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userName").value(goodUser.getUserName()));
    }

    /**
     * Get controller test '/user/{id}' with a userId that is mocked to exist
     * Verifies that password does not exist
     */
    @Test
    public void getByIdPasswordControllerTest_ShouldReturnPasswordDoesNotExist() throws Exception {
        when(userService.findById(goodUserId)).thenReturn(java.util.Optional.ofNullable(goodUser));
        mockMvc.perform(get("/user/{id}", goodUserId)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.password").doesNotExist());
    }
    /**
     * Get controller test '/user/{id}' with a userId that is mocked to not exist
     */
    @Test
    public void getByInvalidIdControllerTest_ShouldReturnIdNotFound() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/user/{id}", badUserId)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andReturn();
        assertEquals(mvcResult.getResponse().getContentAsString(), errorMessageId);
    }

    /**
     * Delete controller test '/user/{id}' with a userId that is mocked to exist
     */
    @Test
    public void deleteControllerTest_ShouldReturnStatusOk() throws Exception {
        when(userService.findById(goodUserId)).thenReturn(java.util.Optional.ofNullable(goodUser));
        Mockito.doNothing().when(userService).deleteUser(goodUserId);

        mockMvc.perform(delete("/user/{id}", goodUserId)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    /**
     * Delete controller test '/user/{id}' with a userId that is mocked to not exist
     */
    @Test
    public void deleteInvalidIdControllerTest_ShouldReturnIdIsNotFound() throws Exception {
        MvcResult mvcResult = mockMvc.perform(delete("/user/{id}", badUserId)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andReturn();
        assertEquals(mvcResult.getResponse().getContentAsString(), errorMessageId);
    }

    /**
     * Post controller test '/user/' with a User object that fits all variable validation criteria
     */
    @Test
    public void postControllerTest_ShouldReturnStatusCreated() throws Exception {
        String userAsJsonString = new ObjectMapper().writeValueAsString(goodUser);

        mockMvc.perform(post("/user/")
                .content(userAsJsonString)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists());
    }
    /**
     * Post controller test '/user/' with a User object that fits all variable validation criteria
     * Tests that Password does not exist
     */
    @Test
    public void postPasswordControllerTest_ShouldReturnDoesNotExist() throws Exception {
        String userAsJsonString = new ObjectMapper().writeValueAsString(goodUser);

        mockMvc.perform(post("/user/")
                .content(userAsJsonString)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.password").doesNotExist());
    }

    /**
     * Post controller test '/user/' with a User object that contains an email already in the database
     */
    @Test
    public void postDuplicateEmailControllerTest_ShouldReturnUserFound() throws Exception {
        String errorMessageUserEmail = "Need unique values for email. " +goodUserEmail+ " is not unique.";
        when(userService.findByContact_Email(goodUserEmail)).thenReturn(goodUser);
        String userAsJsonString = new ObjectMapper().writeValueAsString(goodUser);

        MvcResult mvcResult= mockMvc.perform(post("/user/")
                .content(userAsJsonString)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isMethodNotAllowed())
                .andReturn();
        assertEquals(mvcResult.getResponse().getContentAsString(), errorMessageUserEmail);
    }

    /**
     * Post controller test '/user/' with a User object that contains a username already in the database
     */
    @Test
    public void postDuplicateUserNameControllerTest_ShouldReturnUserFound() throws Exception {
        String errorMessageUserName = "Need unique values for username. " +goodUserName+ " is not unique.";
        when(userService.findByUserName(goodUserName)).thenReturn(goodUser);
        String userAsJsonString = new ObjectMapper().writeValueAsString(goodUser);

        MvcResult mvcResult =  mockMvc.perform(post("/user/")
                .content(userAsJsonString)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isMethodNotAllowed())
                .andReturn();
        assertEquals(mvcResult.getResponse().getContentAsString(), errorMessageUserName);
    }

    /**
     * Post controller test '/user/' with a User object that doesn't fit all valid variable criteria
     */
    @Test
    public void postInvalidBodyControllerTest_ShouldReturnBadRequest() throws Exception {
        String userAsJsonString = new ObjectMapper().writeValueAsString(badUser);

        mockMvc.perform(post("/user/")
                .content(userAsJsonString)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    /**
     * Put controller test '/user/{id}' with a userId that is mocked to exist
     */
    @Test
    public void putControllerTest_ShouldReturnStatusOk() throws Exception {
        Mockito.when(userService.findById(goodUserId)).thenReturn(java.util.Optional.ofNullable(goodUser));
        goodUser.setUserName("newTestUsername");
        String newUserAsJsonString = new ObjectMapper().writeValueAsString(goodUser);
        mockMvc.perform(put("/user/{id}", goodUserId)
                .content(newUserAsJsonString)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(goodUserId))
                .andExpect(MockMvcResultMatchers.jsonPath("$.userName").value("newTestUsername"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.contactInfo.email").value(goodUserEmail))
                .andExpect(MockMvcResultMatchers.jsonPath("$.contactInfo.dailyUpdate").value(dailyUpdate));
    }

    /**
     * Put controller test '/user/{id}' with a userId that is mocked to exist, checks that password does
     * not exist
     */
    @Test
    public void putPasswordControllerTest_ShouldReturnPasswordDoesNotExist() throws Exception {
        Mockito.when(userService.findById(goodUserId)).thenReturn(java.util.Optional.ofNullable(goodUser));
        goodUser.setUserName("newTestUsername");
        String newUserAsJsonString = new ObjectMapper().writeValueAsString(goodUser);
        mockMvc.perform(put("/user/{id}", goodUserId)
                .content(newUserAsJsonString)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.password").doesNotExist());
    }
    /**
     * Put controller test '/user/{id}' with a userId that is mocked to not exist
     */
    @Test
    public void putInvalidIdControllerTest_ShouldReturnIdIsNotFound() throws Exception {
        String userAsJsonString = new ObjectMapper().writeValueAsString(goodUser);
        MvcResult mvcResult = mockMvc.perform(put("/user/{id}", badUserId)
                .content(userAsJsonString)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andReturn();
        assertEquals(mvcResult.getResponse().getContentAsString(), errorMessageId);
    }

    /**
     * Put controller test '/user/' with a User object that doesn't fit all valid variable criteria
     */
    @Test
    public void putInvalidBodyControllerTest_ShouldReturnIsBadRequest() throws Exception {
        Mockito.when(userService.findById(goodUserId)).thenReturn(java.util.Optional.ofNullable(goodUser));
        String newUserAsJsonString = new ObjectMapper().writeValueAsString(badUser);
        mockMvc.perform(put("/user/{id}", goodUserId)
                .content(newUserAsJsonString)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }
}
