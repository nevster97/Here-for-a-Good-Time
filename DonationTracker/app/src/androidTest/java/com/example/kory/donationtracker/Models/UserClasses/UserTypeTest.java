package com.example.kory.donationtracker.Models.UserClasses;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserTypeTest {
    @Test
    public void testUserType() {
        assertEquals(UserType.EMPLOYEE, UserType.typeFix("Employee"));
        assertEquals(UserType.ADMIN, UserType.typeFix("Administrator"));
        assertEquals(UserType.USER, UserType.typeFix("User"));
        assertEquals(UserType.MANAGER, UserType.typeFix("Manager"));
    }
}