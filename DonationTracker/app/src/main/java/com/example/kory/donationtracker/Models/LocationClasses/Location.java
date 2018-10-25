package com.example.kory.donationtracker.Models.LocationClasses;

public class Location {
    private String _name;
    private String _latitude;
    private String _longitude;
    private Address _address;
    private LocationType _type;
    private String _phone;
    private String _website;


    public Location(String name, String latitude, String longitude, Address address,
                    String type, String phone, String website) {
        _name = name;
        // _latitude = Double.parseDouble(latitude);
        // _longitude = Double.parseDouble(longitude);
        _latitude = latitude;
        _longitude = longitude;
        _address = address;
        // _type = LocationType.valueOf(type);
        if (type.equals("Drop Off")) {
            _type = LocationType.DROPOFF;
        } else if (type.equals("Store")) {
            _type = LocationType.STORE;
        } else {
            _type = LocationType.WAREHOUSE;
        }
        _phone = phone;
        _website = website;
    }

    public String getName() { return _name; }
    public String getLat() { return _latitude; }
    public String getLon() { return _longitude; }
    public Address getAddress() { return _address; }
    public LocationType getType() { return _type; }
    public String getPhone() { return _phone; }
    public String getWebsite() { return _website; }

    @Override
    public String toString() {
        return _name + "\n" + _address + "\n" + _phone + "\n" + _website;
    }

}
