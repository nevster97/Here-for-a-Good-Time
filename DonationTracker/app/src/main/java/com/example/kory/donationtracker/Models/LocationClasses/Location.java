package com.example.kory.donationtracker.Models.LocationClasses;

public class Location {
    private String _name;
    private double _latitude;
    private double _longitude;
    private Address _address;
    private LocationType _type;
    private String _phone;
    private String _website;

    public Location(String name, double latitude, double longitude, Address address,
                    LocationType type, String phone, String website) {
        _name = name;
        _latitude = latitude;
        _longitude = longitude;
        _address = address;
        _type = type;
        _phone = phone;
        _website = website;
    }
}
