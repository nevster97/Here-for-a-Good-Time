package com.example.kory.donationtracker.Models.LocationClasses;

/**
 * Location type enum
 */
public enum LocationType {
    DROPOFF("Drop Off", true, false),
    STORE("Store", true, true),
    WAREHOUSE("Warehouse", false, false);

    private String _type;
    private boolean _dropOff;
    private boolean _sales;
    private boolean _inventoryStorage;

    /**
     * creates a LocationType enum
     * @param type type
     * @param dropOff is dropoff
     * @param sales is store
     */
    LocationType(String type, boolean dropOff, boolean sales) {
        _type = type;
        _dropOff = dropOff;
        _sales = sales;
        _inventoryStorage = true;
    }

    /**
     * gets the string type
     * @return string type
     */
    public String getStringType() {
        return _type;
    }

    /**
     * gets if a type is a dropoff location
     * @return if a type is a dropoff
     */
    public boolean isDropOffLocation() {
        return _dropOff;
    }

    /**
     * gets if a type is a sales location
     * @return if a type is a sales location
     */
    public boolean isSalesLocation() {
        return _sales;
    }

    /**
     * gets if a type is a storage location
     * @return if a type is a storage location
     */
    public boolean isStorageLocation() { return _inventoryStorage; }
}
