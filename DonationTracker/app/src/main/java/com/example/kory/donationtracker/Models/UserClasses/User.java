package com.example.kory.donationtracker.Models.UserClasses;

public class User {

    private String _username;
    private String _password;
    private String _name;
    private String _contact;
    private UserType _type;
    private boolean _isDisabled;

    public User(String username, String password, String name, String contact, UserType type) {
        _username = username;
        _password = password;
        _name = name;
        _contact = contact;
        _type = type;
        _isDisabled = false;
    }

    public String get_name() { return _name; }
    public String get_contact() { return _contact; }
    public UserType get_type() { return _type; }

    public boolean checkPassword(String password) {
        return _password.equals(password);
    }
    public void disableUser() { _isDisabled = true; }
    public void enableUser() { _isDisabled = false; }
}
