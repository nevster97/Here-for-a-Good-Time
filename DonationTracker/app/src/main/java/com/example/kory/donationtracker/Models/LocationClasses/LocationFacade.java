package com.example.kory.donationtracker.Models.LocationClasses;

import java.util.List;

/**
 * LocationFacade class
 */
public final class LocationFacade {
    private static final LocationFacade INSTANCE = new LocationFacade();
    private final LocationManager manager;
    private Location current;

    /**
     * gets the location facade instance
     * @return the facade instance
     */
    public static LocationFacade getInstance() { return INSTANCE; }

    /**
     * constructs the location facade
     */
    private LocationFacade() {
        manager = new LocationManager();
    }

    /**
     * gets the current location in the facade
     * @return the current location
     */
    public Location getCurrentLocation() {
        return current;
    }

    /**
     * sets the current location
     * @param location the new location
     */
    public void setCurrentLocation(Location location) {
        current = location;
    }

    /**
     * adds a new location to the location manager
     * @param location the new location
     */
    public void addLocation(Location location) {
        manager.addLocation(location);
    }

    /**
     * gets a desired location from the location manager
     * @param address the address of the desired location
     * @return desired location
     */
    public Location getLocation(String address) {
        return manager.getLocation(address);
    }

    /**
     * checks if the location manager is empty
     * @return true if empty, otherwise false
     */
    public boolean checkIfEmpty() {
        return manager.checkIfEmpty();
    }

    /**
     * returns the locations within the manager in list form
     * @return the list of locations
     */
    public List<Location> getList() {
        return manager.getList();
    }

    // public void logout() { }

    /**
     * sneaky firebase helper :)
     */
    public void setup() {
        manager.setup();
    }

    /**
     * updates the location manager in firebase
     */
    public void update() {
        manager.update();
    }
}
