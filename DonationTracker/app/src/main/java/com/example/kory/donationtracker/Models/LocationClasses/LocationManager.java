package com.example.kory.donationtracker.Models.LocationClasses;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.LinkedList;

public class LocationManager {

    private static List<Location> _locations;
    // TODO
    public LocationManager() { _locations = new LinkedList<>(); }

    public boolean addLocation(Location location) {
        return _locations.add(location);
    }

    public boolean removeLocation(Location location) {
        return _locations.remove(location);
    }
    

}
