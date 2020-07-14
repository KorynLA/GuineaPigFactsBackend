package com.factsdemo.guineaPigFacts.serviceTests;

import com.factsdemo.guineaPigFacts.models.Contact;
import com.factsdemo.guineaPigFacts.models.User;
import com.factsdemo.guineaPigFacts.repositories.UserRepository;
import com.factsdemo.guineaPigFacts.services.UserService;
import com.factsdemo.guineaPigFacts.services.implementations.UserServiceImplementation;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
public class UserServiceTest {

    @TestConfiguration
    static class FactServiceTestContextConfiguration {
        @Bean
        public UserService userService() {
            return new UserServiceImplementation();
        }
    }
    @Autowired
    UserService userService;
    @MockBean
    UserRepository userRepository;
    private final String username = "testName";
    private final String usernameTest1 = "test1";
    private final String usernameTest2 = "test2";
    private final String password = "testPassword";
    private final String email = "testEmail@gmail.com";
    private final boolean dailyUpdate = false;
    private final String id = "100000";
    private User user;
    private Contact contactInfo;

    /**
     * Set up user and contact objects for testing
     */
    @Before
    public void setUp() {
        contactInfo = new Contact(email, dailyUpdate);
        user = new User(username, password, contactInfo);
        user.setId(id);
    }

    /**
     * Test to verify user is saved once
     */
    @Test
    public void userSaveOrUpdateTest() {
        userService.saveOrUpdateUser(user);
        Mockito.verify(userRepository, Mockito.times(1)).save(user);
    }

    /**
     * Test to verify user is deleted once
     */
    @Test
    public void userDeleteByIdTest() {
        userService.deleteUser(user.getId());
        Mockito.verify(userRepository, Mockito.times(1)).deleteById(user.getId());
    }

    /**
     * Test to verify user can be found by id
     */
    @Test
    public void userFindByIdTest() {
        Mockito.when(userRepository.findById(user.getId())).thenReturn(java.util.Optional.ofNullable(user));
        Optional<User> found = userService.findById(id);
        assertTrue(found.isPresent());
    }

    /**
     * Test to verify user can be found by username
     */
    @Test
    public void findByUsernameTest() {
        Mockito.when(userRepository.findByUserName(user.getUserName())).thenReturn(user);
        User found = userService.findByUserName(username);
        assertNotNull(found);
    }

    /**
     * Test to verify user can be found by email
     */
    @Test
    public void findByEmailTest() {
        Mockito.when(userRepository.findByContactInfo_Email(user.getContactInfo().getEmail())).thenReturn(user);
        User found = userService.findByContact_Email(email);
        assertNotNull(found);
    }

    /**
     * Test to verify findByID is used when updating user
     * Need to test save is called once
     */
    @Test
    public void updateUserTest() {
        user.setUserName(usernameTest1);
        User newUser = userService.updateUser(id, user);
        Mockito.verify(userRepository, Mockito.times(1)).findById(id);
    }

    /**
     * Test to verify username has been updated for given id
     */
    @Test
    public void updateUserTestUserNameChanged() {
        user.setUserName(usernameTest2);
        userService.updateUser(id, user);
        Mockito.when(userRepository.findByUserName(user.getUserName())).thenReturn(user);
        assertEquals(user.getUserName(), usernameTest2);
    }
}
