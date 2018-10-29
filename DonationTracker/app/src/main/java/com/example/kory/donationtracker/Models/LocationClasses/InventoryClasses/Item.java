package com.example.kory.donationtracker.Models.LocationClasses.InventoryClasses;

import com.example.kory.donationtracker.Models.LocationClasses.Location;

import java.util.Date;

public class Item {

    private Date _timestamp;
    private Location _location;
    private String _shortDesc;          // for display purposes
    private String _fullDesc;
    private double _value;
    private ItemType _category;

    // optional fields
    // private String comments;
    // private Picture picture;

    public Item(Location location, String shortDesc, String fullDesc, String value, ItemType category) {
        _timestamp = new Date();
        _location = location;
        _shortDesc = shortDesc;
        _fullDesc = fullDesc;
        _value = Double.parseDouble(value);
        _category = category;
    }

    public void updateItem(Date timestamp, Location location, String shortDesc, String fullDesc, String value, ItemType category) {
        _timestamp = timestamp;
        _location = location;
        _shortDesc = shortDesc;
        _fullDesc = fullDesc;
        _value = Double.parseDouble(value);
        _category = category;
    }

    public Date getTimestamp() { return _timestamp; }
    public Location getLocation() { return _location; }
    public String getShort() { return _shortDesc; }
    public String getFull() { return _fullDesc; }
    public double getValue() { return _value; }
    public ItemType getItemType() { return _category; }

}
