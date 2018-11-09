package com.example.kory.donationtracker.Models.LocationClasses.InventoryClasses;

/**
 * ItemType enum
 */
public enum ItemType {
    CLOTHING("Clothing"),
    HAT("Hat"),
    KITCHEN("Kitchen"),
    ELECTRONICS("Electronics"),
    HOUSEHOLD("Household"),
    OTHER("Other");

    private String _type;

    /**
     * constructs the Item enum
     * @param type item type
     */
    private ItemType(String type) {
        _type = type;
    }

    /**
     * gets the string type of an enum
     * @return the string type
     */
    public String getStringType() {
        return _type;
    }
}
