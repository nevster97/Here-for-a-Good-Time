package com.example.kory.donationtracker.Models.UserClasses;

import com.example.kory.donationtracker.Models.LocationClasses.Location;

public class User {

    private String _username;
    private String _password;
    private String _name;
    private String _contact;
    private UserType _type;
    private boolean _isDisabled;
    private Location _employeeLocation;

    public User(String username, String password, String name, String contact, UserType type) {
        _username = username;
        _password = password;
        _name = name;
        _contact = contact;
        _type = type;
        _isDisabled = false;
        _employeeLocation = null;
    }

    // use this constructor when instantiating a location employee
    public User(String username, String password, String name, String contact, UserType type, Location employeeLocation) {
        _username = username;
        _password = password;
        _name = name;
        _contact = contact;
        _type = type;
        _isDisabled = false;
        _employeeLocation = employeeLocation;
    }


    public String get_name() { return _name; }
    public String get_contact() { return _contact; }
    public UserType get_type() { return _type; }
    public String get_password() {return _password; }
    public Location get_employeeLocation() {return _employeeLocation; }
    public void set_employeeLocation(Location l) {
        _employeeLocation = l;
//        if (l.addLocationEmployee(this)) {
//            System.out.println("ItS ADDING THE FUCKING BABY TO THIS GOD DAMN THING THAT OWNT WORK");
//        }
    }

    public boolean checkPassword(String password) {
        return _password.equals(password);
    }
    public void disableUser() { _isDisabled = true; }
    public void enableUser() { _isDisabled = false; }
}
