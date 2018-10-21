package com.example.kory.donationtracker.Models.LocationClasses.InventoryClasses;

public enum ItemType {
    CLOTHING("Clothing"),
    HAT("Hat"),
    KITCHEN("Kitchen"),
    ELECTRONICS("Electronics"),
    HOUSEHOLD("Household"),
    OTHER("Other");

    private String _type;

    ItemType(String type) {
        _type = type;
    }

    public String getStringType() {
        return _type;
    }
}
