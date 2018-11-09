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

    /**
     * constructor for an item
     * @param shortDesc short description
     * @param fullDesc full description
     * @param value value
     * @param category item type
     */
    public Item(String shortDesc, String fullDesc, String value, String category) {
        this.shortDesc = shortDesc;
        this.fullDesc = fullDesc;
        this.value = Double.parseDouble(value);
        this.category = category;
    }

    /**
     * updates an item
     * @param timestamp timestamp
     * @param location location
     * @param shortDesc short description
     * @param fullDesc full description
     * @param value value
     * @param category item type
     */
    public void updateItem(Date timestamp, Location location, String shortDesc, String fullDesc, String value, String category) {
        // _timestamp = timestamp;
        // _location = location;
        this.shortDesc = shortDesc;
        this.fullDesc = fullDesc;
        this.value = Double.parseDouble(value);
        this.category = category;
    }

    /**
     * gets the short description
     * @return short description
     */
    public String getShort() {
        return shortDesc;
    }

    /**
     * gets the full description
     * @return full description
     */
    public String getFull() {
        return fullDesc;
    }

    /**
     * gets the value
     * @return value
     */
    public double getValue() {
        return value;
    }

    /**
     * gets the item type
     * @return item type
     */
    public String getItemType() {
        return category;
    }

}
