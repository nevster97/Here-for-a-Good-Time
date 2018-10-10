package com.example.kory.donationtracker.Models.LocationClasses;

import java.util.Arrays;
import java.util.List;

public enum LocationType {
    DROPOFF("Drop-Off", true, false, true),
    STORE("Store", true, true, true),
    WAREHOUSE("Warehouse", false, false, true);

    private String _type;
    private boolean _dropOff;
    private boolean _sales;
    private boolean _inventoryStorage;

    LocationType(String type, boolean dropOff, boolean sales, boolean inventoryStorage) {
        _type = type;
        _dropOff = dropOff;
        _sales = sales;
        _inventoryStorage = inventoryStorage;
    }

    public String getStringType() { return _type; }

    public boolean isDropOffLocation() { return _dropOff; }
    public boolean isSalesLocation() { return _sales; }
    public boolean isStorageLocation() { return _inventoryStorage; }
}
