package com.example.isaac.donationtracker.models;

public class User {
    private String _username;
    private String _password;
    private String _name;
    private String _contact;
    private UserType _type;

    public User(String username, String password, String name, String contact, UserType type) {
        _username = username;
        _password = password;
        _name = name;
        _contact = contact;
        _type = type;
    }

    public String get_name() { return _name; }
    public String get_contact() { return _contact; }
    public UserType get_type() { return _type; }

    public boolean checkPassword(String password) {
        return _password.equals(password);
    }


}
