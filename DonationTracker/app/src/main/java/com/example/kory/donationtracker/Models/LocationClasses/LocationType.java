package com.example.kory.donationtracker.Models.LocationClasses;

/**
 * Location type enum
 */
public enum LocationType {
    DROPOFF("Drop Off"),
    STORE("Store"),
    WAREHOUSE("Warehouse");

    private final String _type;
//    private final boolean _dropOff;
//    private final boolean _sales;
//    private final boolean _inventoryStorage;

    /**
     * creates a LocationType enum
     * @param type type
     */
    LocationType(String type) {
        _type = type;
//        _dropOff = dropOff;
//        _sales = sales;
//        _inventoryStorage = true;
    }

    /**
     * gets the string type
     * @return string type
     */
    public String getStringType() {
        return _type;
    }

// --Commented out by Inspection START (11/12/18, 2:33 PM):
//    /**
//     * gets if a type is a dropoff location
//     * @return if a type is a dropoff
//     */
//    public boolean isDropOffLocation() {
//        return _dropOff;
//    }
// --Commented out by Inspection STOP (11/12/18, 2:33 PM)

// --Commented out by Inspection START (11/12/18, 3:15 PM):
//    /**
//     * gets if a type is a sales location
//     * @return if a type is a sales location
//     */
//    public boolean isSalesLocation() {
//        return _sales;
//    }
// --Commented out by Inspection STOP (11/12/18, 3:15 PM)

// --Commented out by Inspection START (11/12/18, 3:17 PM):
//    /**
//     * gets if a type is a storage location
//     * @return if a type is a storage location
//     */
//    public boolean isStorageLocation() { return _inventoryStorage; }
// --Commented out by Inspection STOP (11/12/18, 3:17 PM)
}
