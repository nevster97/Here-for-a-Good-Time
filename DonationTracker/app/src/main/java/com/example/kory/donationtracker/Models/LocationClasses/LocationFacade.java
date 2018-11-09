package com.example.kory.donationtracker.Models.LocationClasses;

import java.util.List;

public class LocationFacade {
    private static LocationFacade INSTANCE = new LocationFacade();
    private LocationManager manager;
    private Location current;

    /**
     * gets the location facade instance
     * @return the facade instance
     */
    public static LocationFacade getInstance() { return INSTANCE; }

    /**
     * constructs the location facade
     */
    public LocationFacade() {
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
     * @return true if the add was successful
     */
    public boolean addLocation(Location location) {
        return manager.addLocation(location);
    }

    /**
     * gets a desired location from the location manager
     * @param address the address of the desired location
     * @return
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
     * @param location edited location
     */
    public void update(Location location) {
        manager.update(location);
    }
}
