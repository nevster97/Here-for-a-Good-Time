package com.example.kory.donationtracker.Models.LocationClasses;

import java.util.List;

public class LocationFacade {
    private static LocationFacade INSTANCE = new LocationFacade();
    private LocationManager manager;
    private Location current;

    public static LocationFacade getInstance() { return INSTANCE; }

    public LocationFacade() {
        manager = new LocationManager();
    }

    public Location getCurrentLocation() { return current; }
    public void setCurrentLocation(Location location) { current = location; }

    public boolean addLocation(Location location) {
        return manager.addLocation(location);
    }

    public Location getLocation(String address) {
        return manager.getLocation(address);
    }

    public boolean checkIfEmpty() {
        return manager.checkIfEmpty();
    }

    public List<Location> getList() {
        return manager.getList();
    }

    public void logout() { }

    public void setup() {
        manager.setup();
    }

    public void update(Location location) {
        manager.update(location);
    }

//    public void refresh() {
//        manager.downloadFromDB();
//    }
//
//    public void send() { manager.uploadToDB(); }
}
