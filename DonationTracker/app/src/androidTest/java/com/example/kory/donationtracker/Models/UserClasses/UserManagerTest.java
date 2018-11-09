package com.example.kory.donationtracker.Models.UserClasses;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

//import static org.junit.Assert.*;

public class UserManagerTest {
    private User userOne;
    private User userTwo;
    private UserManager userManager;

    @Before
    public void setUp() {
//        userOne = new User("Username1", "Password1", "Name1",
//                "email@email.com","Employee", "Location1");
//        userTwo = new User("Username2", "Password2", "Name2",
//                "email2@email.com", "User", "Location2");
        userManager = new UserManager();
    }

    @Test
    public void testAddUser() {
        assertEquals(true, userManager.addUser("Username1", "Password1", "Name1",
                "email@email.com","Employee", "Location1"));
        assertEquals(true, userManager.addUser("Username2", "Password2", "Name2",
                "email2@email.com", "User", "Location2"));
        assertEquals(false, userManager.addUser("Username2", "Password2", "Name2",
                "email2@email.com", "User", "Location2"));
    }

}