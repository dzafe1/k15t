package com.k15t.pat.service;

import com.k15t.pat.ApplicationBootstrap;
import com.k15t.pat.exception.ItemFoundException;
import com.k15t.pat.exception.ItemNotFoundException;
import com.k15t.pat.model.User;
import com.k15t.pat.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ApplicationBootstrap.class)
public class UserServiceTest {

    @Autowired
    UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Before
    public void setUp() {
        userRepository.deleteAll();
    }

    @Test(expected = ItemFoundException.class)
    public void createUserForRegistrationTest() {
        User user = new User(null, "testName", "password", "address", "email@e.com", "+38722121");

        userService.createUserForRegistration(user);
        assertEquals(1, userService.getAllRegisteredUsers().size());
        assertEquals("testName", userRepository.findAll().get(0).getName());
        userService.createUserForRegistration(user);
    }

    @Test(expected = ItemFoundException.class)
    public void getAllRegisteredUsersTest() {
        User user1 = new User(null, "testName", "password", "address", "email@e.com", "+38722121");
        User user2 = new User(null, "testName", "password", "address", "email@e.com", "+38722121");
        User user3 = new User(null, "testName", "password", "address", "email@e1.com", "+38722121");

        userService.createUserForRegistration(user1);
        assertEquals(1, userService.getAllRegisteredUsers().size());
        user3 = userService.createUserForRegistration(user3);
        assertEquals(2, userService.getAllRegisteredUsers().size());
        userService.deleteRegisteredUserById(user3.getId());
        assertEquals(1, userService.getAllRegisteredUsers().size());
        userService.createUserForRegistration(user2);
    }

    @Test(expected = ItemNotFoundException.class)
    public void getRegisteredUserByIdTest() {
        User user1 = new User(null, "testName", "password", "address", "email@e.com", "+38722121");

        user1 = userService.createUserForRegistration(user1);
        assertEquals("email@e.com", userService.getRegisteredUserById(user1.getId()).getEmail());
        userService.getRegisteredUserById(0L);
    }

    @Test(expected = ItemNotFoundException.class)
    public void deleteRegisteredUserById() {
        User user1 = new User(null, "testName", "password", "address", "email@e.com", "+38722121");

        user1 = userService.createUserForRegistration(user1);
        assertEquals(1, userService.getAllRegisteredUsers().size());
        userService.deleteRegisteredUserById(user1.getId());
        assertEquals(0, userService.getAllRegisteredUsers().size());
        userService.deleteRegisteredUserById(0L);
    }
}
