package com.example.kory.donationtracker.Models.LocationClasses;

public class Address {
    private String _street;
    private String _city;
    private String _state;
    private int _zip;

    public Address(String street, String city, String state, String zip) {
        _street = street;
        _city = city;
        _state = state;
        _zip = Integer.parseInt(zip);
    }

    @Override
    public String toString() {
        String str = _street + ", " +  _city + ", " + _state + " " + _zip;
        return str;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Address) {
            Address a = (Address) o;
            String str1 = this._street + this._city + this._state + this._zip;
            String str2 = a.getStreet() + a.getCity() + a.getState() + a.getZip();
            return str1.equals(str2);
        }
        return false;
    }

    @Override
    public int hashCode() {
        String str1 = this._street + this._city + this._state + this._zip;
        return str1.hashCode();
    }

    public String getStreet() { return _street; }
    public String getCity() { return _city; }
    public String getState() { return _state; }
    public int getZip() { return _zip; }
}
