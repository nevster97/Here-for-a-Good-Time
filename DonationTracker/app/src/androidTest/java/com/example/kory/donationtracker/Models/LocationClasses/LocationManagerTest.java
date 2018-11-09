package com.example.kory.donationtracker.Models.LocationClasses;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class LocationManagerTest {

    private Location locationOne;
    private Location locationTwo;
    private LocationManager locationManager;

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

    @Test
    public void testAddAndRemoveLocation() {
        assertEquals(true, locationManager.addLocation(locationOne));
        assertEquals(true, locationManager.addLocation(locationTwo));
        assertEquals(false, locationManager.addLocation(locationTwo));
        locationManager.removeLocation(locationTwo);
        assertEquals(true, locationManager.addLocation(locationTwo));
    }
}