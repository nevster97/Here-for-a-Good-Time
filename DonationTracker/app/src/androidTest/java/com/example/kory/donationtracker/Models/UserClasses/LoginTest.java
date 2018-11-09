package com.example.kory.donationtracker.Models.UserClasses;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * Login test
 */
public class LoginTest {

    private UserManager manager;

    private User user1;
    private User user2;

    /**
     * sets up the junit test
     */
    @Before
    public void setup() {

        manager = new UserManager();

        user1 = new User("buzz", "yackets", "Buzz", "buzz", "User");
        user2 = new User("woody", "andy", "Sherriff Woody", "woody", "User");

        manager.addUserTest(user1);
        manager.addUserTest(user2);
    }

    /**
     * tests a valid user && valid password
     */
    @Test
    public void testLoginU1() {
        assertEquals(user1, manager.login(user1.getUsername(), user1.getPassword()));
    }

    /**
     * tests a valid user && invalid password
     */
    @Test
    public void testLoginU2() {
        assertNull(manager.login(user2.getUsername(), "randy"));
    }

    /**
     * tests an invalid user
     */
    @Test
    public void testLoginU3() {
        assertNull(manager.login("nonexistent user", "nonexistent password"));
    }
}
