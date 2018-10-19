package com.example.kory.donationtracker.Models.LocationClasses;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

//import java.util.List;
////import java.util.ArrayList;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;

public class LocationManager {

    private static Map<Address, Location> _locations;
    // TODO
    public LocationManager() { _locations = new HashMap<>(); }

    public boolean addLocation(Location location) {
        if (_locations.containsKey(location.getAddress())) {
            return false;
        }
        _locations.put(location.getAddress(), location);
        return true;
    }

    public void removeLocation(Location location) {
        _locations.remove(location.getAddress());
    }

    public Location getLocation(Address address) {
        return _locations.get(address);
    }

    public boolean checkIfEmpty() {
        return _locations.isEmpty();
    }

    public ArrayList<Location> getList() {
        return new ArrayList(_locations.values());
    }


}
