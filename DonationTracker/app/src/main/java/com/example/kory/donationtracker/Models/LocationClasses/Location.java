package com.example.kory.donationtracker.Models.LocationClasses;

import android.support.annotation.NonNull;

import com.example.kory.donationtracker.Models.LocationClasses.InventoryClasses.Inventory;
import com.example.kory.donationtracker.Models.LocationClasses.InventoryClasses.Item;

/**
 * Location class
 */
public class Location {
    private final String name;
    private final String latitude;
    private final String longitude;
    // private Address _address;
    private final String address;
    private final LocationType type;
    private final String phone;
    private final String website;
    private final Inventory inventory;
    // private ArrayList<User> locationEmployees;


    /**
     * location constructor
     * @param name name
     * @param latitude latitude
     * @param longitude longitude
     * @param address address
     * @param type type
     * @param phone phone number
     * @param website website address
     */
    public Location(String name, String latitude, String longitude, String address,
                    String type, String phone, String website) {
        this.name = name;
        // _latitude = Double.parseDouble(latitude);
        // _longitude = Double.parseDouble(longitude);
        this.latitude = latitude;
        this.longitude = longitude;
        this.address = address;
        // _type = LocationType.valueOf(type);
        switch (type) {
            case "Drop Off":
                this.type = LocationType.DROPOFF;
                break;
            case "Store":
                this.type = LocationType.STORE;
                break;
            default:
                this.type = LocationType.WAREHOUSE;
                break;
        }
        this.phone = phone;
        this.website = website;
        this.inventory = new Inventory(this);
    }

    // use this constructor when taking in an inventory object

    /**
     * constructor for location with existing inventory
     * @param name name
     * @param latitude latitude
     * @param longitude longitude
     * @param address address
     * @param type type
     * @param phone phone number
     * @param website website address
     * @param inventory inventory
     */
    public Location(String name, String latitude, String longitude, String address,
                    String type, String phone, String website, Inventory inventory) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.address = address;
        switch (type) {
            case "Drop Off":
                this.type = LocationType.DROPOFF;
                break;
            case "Store":
                this.type = LocationType.STORE;
                break;
            default:
                this.type = LocationType.WAREHOUSE;
                break;
        }
        this.phone = phone;
        this.website = website;
        this.inventory = inventory;
    }

    /**
     * gets the name
     * @return location name
     */
    public String getName() {
        return name;
    }

    /**
     * gets the latitude
     * @return latitude
     */
    public String getLat() {
        return latitude;
    }

    /**
     * gets the longitude
     * @return longitude
     */
    public String getLon() {
        return longitude;
    }

    /**
     * gets the address
     * @return address
     */
    public String getAddress() {
        return address;
    }

    /**
     * gets the location type
     * @return location's type
     */
    public LocationType getType() {
        return type;
    }

    /**
     * gets the location's phone number
     * @return location's phone number
     */
    public String getPhone() {
        return phone;
    }

    /**
     * gets the location's website
     * @return website
     */
    public String getWebsite() {
        return website;
    }

    /**
     * gets a location's inventory
     * @return a location's inventory
     */
    public Inventory getInventory() {
        return inventory;
    }

    /**
     * adds an item to a location's inventory
     * @param item item to be added
     */
    public void addItem(Item item) {
        inventory.addItem(item);
    }
    // public ArrayList<User> getLocationEmployees() {return ; }

//    public boolean addLocationEmployee(User u) {
//        UserType ut = u.getUserType();
//        if (ut.equals(UserType.EMPLOYEE)) {
////            String username = u.get_name();
////            String password = u.get_password();
////            String name = u.get_name();
////            String contact = u.get_contact();
////            UserType type = u.get_type();
////            User us = new User(username, password, name, contact, type, this);
//            _locationEmployees.add(u);
//            return true;
//        } else {
//            return false;
//        }
//    }

//    /**
//     * sets a location's inventory
//     * @param inventory the new inventory
//     */
//    public void setInventory(Inventory inventory) {
//        this.inventory = inventory;
//    }

    /**
     * overridden toString()
     * @return the new Location.toString()
     */
    @NonNull
    @Override
    public String toString() {
        return name + "\n" + address + "\n" + phone + "\n" + website;
    }

}
