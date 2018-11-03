package com.example.kory.donationtracker.Models.LocationClasses;

import com.example.kory.donationtracker.Models.LocationClasses.InventoryClasses.Inventory;
import com.example.kory.donationtracker.Models.LocationClasses.InventoryClasses.Item;
import com.example.kory.donationtracker.Models.UserClasses.User;
import com.example.kory.donationtracker.Models.UserClasses.UserType;

import java.util.ArrayList;

public class Location {
    private String name;
    private String latitude;
    private String longitude;
    // private Address _address;
    private String address;
    private LocationType type;
    private String phone;
    private String website;
    private Inventory inventory;
    // private ArrayList<User> locationEmployees;


    public Location(String name, String latitude, String longitude, String address,
                    String type, String phone, String website) {
        this.name = name;
        // _latitude = Double.parseDouble(latitude);
        // _longitude = Double.parseDouble(longitude);
        this.latitude = latitude;
        this.longitude = longitude;
        this.address = address;
        // _type = LocationType.valueOf(type);
        if (type.equals("Drop Off")) {
            this.type = LocationType.DROPOFF;
        } else if (type.equals("Store")) {
            this.type = LocationType.STORE;
        } else {
            this.type = LocationType.WAREHOUSE;
        }
        this.phone = phone;
        this.website = website;
        this.inventory = new Inventory(this);
    }

    // use this constructor when taking in an inventory object
    public Location(String name, String latitude, String longitude, String address,
                    String type, String phone, String website, Inventory inventory) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.address = address;
        if (type.equals("Drop Off")) {
            this.type = LocationType.DROPOFF;
        } else if (type.equals("Store")) {
            this.type = LocationType.STORE;
        } else {
            this.type = LocationType.WAREHOUSE;
        }
        this.phone = phone;
        this.website = website;
        this.inventory = inventory;
    }

    public String getName() {
        return name;
    }

    public String getLat() {
        return latitude;
    }

    public String getLon() {
        return longitude;
    }

    public String getAddress() {
        return address;
    }

    public LocationType getType() {
        return type;
    }

    public String getPhone() {
        return phone;
    }

    public String getWebsite() {
        return website;
    }

    public Inventory getInventory() {
        return inventory;
    }

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

    public void setInventory (Inventory inventory) {
        this.inventory = inventory;
    }

    @Override
    public String toString() {
        return name + "\n" + address + "\n" + phone + "\n" + website;
    }

}
