package com.example.kory.donationtracker.Models.UserClasses;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Map;
import java.util.HashMap;

public class User {

    // private DatabaseReference db = FirebaseDatabase.getInstance().getReference();

    private String username;
    private String password;
    private String name;
    private String contact;
    private UserType type;
    private String employeeLocation;

    public User() {

    }

    public User(String username, String password, String name, String contact, String type) {
        this(username, password, name, contact, type, null);
    }

    // use this constructor when instantiating a location employee
    public User(String username, String password, String name, String contact, String type, String employeeLocation) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.contact = contact;
        this.type = UserType.typeFix(type);
        this.employeeLocation = employeeLocation;

        // db.child("users").child(username).setValue(this);
    }


//    public String get_name() { return _name; }
////    public String get_contact() { return _contact; }
////    public UserType get_type() { return _type; }
////    public String get_password() {return _password; }
////    // public Location get_employeeLocation() {return LocationFacade.getInstance().getLocation(_employeeLocation); }
////    public String get_employeeLocation() { return _employeeLocation; }
////    public void set_employeeLocation(String s) { _employeeLocation = s; }



    public Map<String, Object> toMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("username", username);
        map.put("password", password);
        map.put("name", name);
        map.put("contact", contact);
        map.put("type", type.getStringType());
        map.put("employeeLocation", employeeLocation);
        return map;
    }

    public boolean checkPassword(String password) {
        return this.password.equals(password);
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getContact() {
        return contact;
    }

    public String getType() {
        return type.getStringType();
    }

    public UserType getUserType() {
        return type;
    }

    public String getEmployeeLocation() {
        return employeeLocation;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public void setType(String type) {
        this.type = UserType.valueOf(type);
    }

    public void setEmployeeLocation(String employeeLocation) {
        this.employeeLocation = employeeLocation;
    }

//    public void disableUser() { _isDisabled = true; }
//    public void enableUser() { _isDisabled = false; }
}
