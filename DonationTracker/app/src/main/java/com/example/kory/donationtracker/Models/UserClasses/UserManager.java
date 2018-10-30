package com.example.kory.donationtracker.Models.UserClasses;

import java.util.HashMap;
import java.util.Map;

import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class UserManager {

    private Map<String, User> _userMap;
    // private FirebaseDatabase database;
    private DatabaseReference ref = FirebaseDatabase.getInstance().getReference("users");

    public UserManager() {
        _userMap = new HashMap<>();
        // database = FirebaseDatabase.getInstance();
        // ref = database.getReference("users");
    }

    public boolean addUser(String username, String password, String name, String email, UserType type, String location) {
        User user = new User(username, password, name, email, type, location);
        if (_userMap.containsKey(username)) {
            return false;
        }
        _userMap.put(username, user);
        ref.setValue(_userMap);
        return true;
    }

    public boolean addUser(String username, String password, String name, String email, UserType type) {
        User user = new User(username, password, name, email, type);
        if (_userMap.containsKey(username)) {
            return false;
        }
        _userMap.put(username, user);
        ref.setValue(_userMap);
        return true;
    }

    public User login(String username, String password) {
        User user;
        if (_userMap.containsKey(username)) {
            user = _userMap.get(username);
            if (user.checkPassword(password)) {
                return user;
            } else {
                return null;
            }
        }
        return null;
    }

    public void uploadToDB() {
        ref.setValue(_userMap);
    }

    public void downloadFromDB() {
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
//                Map<String, Object> map = dataSnapshot.getValue(Map.class);
//                for (String s : map.keySet()) {
//                    System.out.print(s);
//                }
//                // System.out.println(value);
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    String name = (String) ds.child("_name").getValue();
                    String contact = (String) ds.child("_contact").getValue();
                    String password = (String) ds.child("_password").getValue();
                    String type = (String) ds.child("_type").getValue();

                    if (UserType.valueOf(type) == UserType.EMPLOYEE) {
                        // Map<String, Object> location = (Map) ds.child("_employeeLocation").getValue();
                        String location = (String) ds.child("_employeeLocation").getValue();
//                        if (location != null) {
//
//                            boolean catcher = addUser(contact, password, name, contact, UserType.EMPLOYEE, address);
//                        }
//                        boolean catcher = addUser(contact, password, name, contact, UserType.EMPLOYEE, null);
                        boolean catcher = addUser(contact, password, name, contact, UserType.valueOf(type), location);

                    } else {
                        boolean catcher = addUser(contact, password, name, contact, UserType.valueOf(type));
                    }
                }
                uploadToDB();
            }

            @Override
            public void onCancelled(DatabaseError error) {
                System.out.println(error);
            }
        });
    }

}
