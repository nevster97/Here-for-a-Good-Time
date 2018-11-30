package com.example.kory.donationtracker.Models.LocationClasses;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

/**
 * Location Manager Test
 */
public class LocationManagerTest {

    private Location locationOne;
    private Location locationTwo;
    private LocationManager locationManager;

    /**
     * sets up the test
     */
    @Before
    public void setUp() {

        locationOne = new Location("Location One", "111", "111",
                "111 One Road","Drop Off", "111-111-1111",
                "locationone.com");
        locationTwo = new Location("Location Two", "222", "222",
                "222 Two Road","Store", "222-222-2222",
                "locationone.com");
        locationManager = new LocationManager();
    }

    /**
     * runs the test
     */
    @Test
    public void testAddAndRemoveLocation() {
        assertTrue(locationManager.addLocation(locationOne));
        assertTrue(locationManager.addLocation(locationTwo));
        assertFalse(locationManager.addLocation(locationTwo));
        locationManager.removeLocation(locationTwo);
        assertTrue(locationManager.addLocation(locationTwo));
    }
}