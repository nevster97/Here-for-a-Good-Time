package com.example.kory.donationtracker.Models.LocationClasses.InventoryClasses;

import com.example.kory.donationtracker.Models.LocationClasses.Location;

import java.util.Date;

public class Item {

    // private Date _timestamp;
    // private Location _location;
    private String shortDesc;          // for display purposes
    private String fullDesc;
    private Double value;
    private String category;

    // optional fields
    // private String comments;
    // private Picture picture;

//    public Item(Location location, String shortDesc, String fullDesc, String value, String category) {
//        // _timestamp = new Date();
//        // _location = location;
//        _shortDesc = shortDesc;
//        _fullDesc = fullDesc;
//        _value = Double.parseDouble(value);
//        _category = category;
//    }

    public Item(String shortDesc, String fullDesc, String value, String category) {
        this.shortDesc = shortDesc;
        this.fullDesc = fullDesc;
        this.value = Double.parseDouble(value);
        this.category = category;
    }

    public void updateItem(Date timestamp, Location location, String shortDesc, String fullDesc, String value, String category) {
        // _timestamp = timestamp;
        // _location = location;
        this.shortDesc = shortDesc;
        this.fullDesc = fullDesc;
        this.value = Double.parseDouble(value);
        this.category = category;
    }

    // public Date getTimestamp() { return _timestamp; }
    // public Location getLocation() { return _location; }
    public String getShort() {
        return shortDesc;
    }

    public String getFull() {
        return fullDesc;
    }

    public double getValue() {
        return value;
    }

    public String getItemType() {
        return category;
    }

}
