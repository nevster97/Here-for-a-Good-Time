package com.example.kory.donationtracker.Models.LocationClasses;

public class Address {
    private String _street;
    private String _city;
    private String _state;
    private int _zip;

    /**
     * constructor for an address
     * @param street street name
     * @param city city
     * @param state state
     * @param zip zip code
     */
    public Address(String street, String city, String state, String zip) {
        _street = street;
        _city = city;
        _state = state;
        _zip = Integer.parseInt(zip);
    }

    /**
     * string version of an address
     * @return string version of an address
     */
    @Override
    public String toString() {
        String str = _street + ", " +  _city + ", " + _state + " " + _zip;
        return str;
    }

    /**
     * overridden equals method
     * @param o object to compare to
     * @return if address.equals(o)
     */
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

    /**
     * overriding hashCode
     * @return hashCode of address
     */
    @Override
    public int hashCode() {
        String str1 = this._street + this._city + this._state + this._zip;
        return str1.hashCode();
    }

    /**
     * gets the street
     * @return street name
     */
    public String getStreet() { return _street; }

    /**
     * gets the city
     * @return city name
     */
    public String getCity() { return _city; }

    /**
     * gets the state
     * @return state name
     */
    public String getState() { return _state; }

    /**
     * gets the zip code
     * @return zip code
     */
    public int getZip() { return _zip; }
}
