package com.example.kory.donationtracker.Models.UserClasses;

import org.junit.Before;
import org.junit.Test;
// import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

//import static org.junit.Assert.*;

/**
 * User Manager Test
 */
public class UserManagerTest {
//    private User userOne;
//    private User userTwo;
    private UserManager userManager;

    /**
     * Sets up the method
     */
    @Before
    public void setUp() {
//        userOne = new User("Username1", "Password1", "Name1",
//                "email@email.com","Employee", "Location1");
//        userTwo = new User("Username2", "Password2", "Name2",
//                "email2@email.com", "User", "Location2");
        UserFacade userFacade = UserFacade.getInstance();
        userManager = userFacade.getManager();
    }

    /**
     * Tests an add to the method
     */
    @Test
    public void testAddUser() {
        assertTrue(userManager.addUser("Username1", "Password1", "Name1",
                "email@email.com","Employee", "Location1"));
        assertTrue(userManager.addUser("Username2", "Password2", "Name2",
                "email2@email.com", "User", "Location2"));
        assertFalse(userManager.addUser("Username2", "Password2", "Name2",
                "email2@email.com", "User", "Location2"));

        userManager.removeUser("Username1");
        userManager.removeUser("Username2");
    }

}