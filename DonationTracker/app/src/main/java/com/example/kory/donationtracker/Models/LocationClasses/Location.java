package com.example.kory.donationtracker.Models.LocationClasses;

import com.example.kory.donationtracker.Models.LocationClasses.InventoryClasses.Inventory;
import com.example.kory.donationtracker.Models.UserClasses.User;
import com.example.kory.donationtracker.Models.UserClasses.UserType;

import java.util.ArrayList;

public class Location {
    private String _name;
    private String _latitude;
    private String _longitude;
    // private Address _address;
    private String _address;
    private LocationType _type;
    private String _phone;
    private String _website;
    private Inventory _inventory;
    private ArrayList<User> _locationEmployees;


    public Location(String name, String latitude, String longitude, String address,
                    String type, String phone, String website) {
        _name = name;
        // _latitude = Double.parseDouble(latitude);
        // _longitude = Double.parseDouble(longitude);
        _latitude = latitude;
        _longitude = longitude;
        _address = address;
        // _type = LocationType.valueOf(type);
        if (type.equals("Drop Off")) {
            _type = LocationType.DROPOFF;
        } else if (type.equals("Store")) {
            _type = LocationType.STORE;
        } else {
            _type = LocationType.WAREHOUSE;
        }
        _phone = phone;
        _website = website;
        _inventory = new Inventory(this);
    }

    // use this constructor when taking in an inventory object
    public Location(String name, String latitude, String longitude, String address,
                    String type, String phone, String website, Inventory inventory) {
        _name = name;
        _latitude = latitude;
        _longitude = longitude;
        _address = address;
        if (type.equals("Drop Off")) { _type = LocationType.DROPOFF; }
        else if (type.equals("Store")) { _type = LocationType.STORE; }
        else { _type = LocationType.WAREHOUSE; }
        _phone = phone;
        _website = website;
        _inventory = inventory;
    }

    public String getName() { return _name; }
    public String getLat() { return _latitude; }
    public String getLon() { return _longitude; }
    public String getAddress() { return _address; }
    public LocationType getType() { return _type; }
    public String getPhone() { return _phone; }
    public String getWebsite() { return _website; }
    public Inventory getInventory() { return _inventory; }
    public ArrayList<User> getLocationEmployees() {return _locationEmployees; }

    public boolean addLocationEmployee(User u) {
        UserType ut = u.get_type();
        if (ut.equals(UserType.EMPLOYEE)) {
//            String username = u.get_name();
//            String password = u.get_password();
//            String name = u.get_name();
//            String contact = u.get_contact();
//            UserType type = u.get_type();
//            User us = new User(username, password, name, contact, type, this);
            _locationEmployees.add(u);
            return true;
        } else {
            return false;
        }
    }

    public void setInventory (Inventory inv) {
        _inventory = inv;
    }

    @Override
    public String toString() {
        return _name + "\n" + _address + "\n" + _phone + "\n" + _website;
    }

}
