package com.example.kory.donationtracker.Models.UserClasses;

import android.util.Log;

import java.util.HashMap;
import java.util.Map;

import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

class UserManager {

    private Map<String, User> userMap;
    private static DatabaseReference db = FirebaseDatabase.getInstance().getReference("users");
    private static User user;

    /**
     * constructs the user manager
     */
    public UserManager() {
        userMap = new HashMap<>();
    }

    /**
     * adds a user to the manager
     * @param username username
     * @param password password
     * @param name name
     * @param email email
     * @param type user tyoe
     * @param location employee location
     * @return true if add succeeds, otherwise false
     */
    private boolean addUser(String username, String password, String name, String email, String type, String location) {
        user = new User(username, password, name, email, type, location);
        if (userMap.containsKey(username)) {
            return false;
        }
        userMap.put(username, user);
        db.setValue(userMap);
        return true;
    }

    /**
     * adds a user to the manager (no location)
     * @param username username
     * @param password password
     * @param name name
     * @param email email
     * @param type user type
     * @return true if add succeeds, otherwise false
     */
    public boolean addUser(String username, String password, String name, String email, String type) {
        User user = new User(username, password, name, email, type);
        if (userMap.containsKey(username)) {
            return false;
        }
        userMap.put(username, user);
        db.setValue(userMap);
        return true;
    }

    public void addUserTest(User user) {
        userMap.put(user.getUsername(), user);
    }

    /**
     * gets a user from the manager and returns it (if exists)
     * @param username username
     * @param password password
     * @return the user corresponding to the username
     */
    public User login(String username, String password) {
        User user;
        if (userMap.containsKey(username)) {
            user = userMap.get(username);
            if (user.checkPassword(password)) {
                return user;
            } else {
                return null;
            }
        }
        return null;
    }

    /**
     * adds listener to firebase, provides instructions on how to create users n stuff
     */
    public void setup() {
        db.addValueEventListener(new ValueEventListener() {
            /**
             * provides instructions on how to create users from firebase data
             * @param dataSnapshot a dataSnapshot from firebase
             */
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
//                Map<String, Object> map = dataSnapshot.getValue(Map.class);
//                for (String s : map.keySet()) {
//                    System.out.println(s);
//                }
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    String username = (String) ds.child("username").getValue();
                    String password = (String) ds.child("password").getValue();
                    String name = (String) ds.child("name").getValue();
                    String contact = (String) ds.child("contact").getValue();
                    String type = (String) ds.child("type").getValue();
                    String employeeLocation = (String) ds.child("employeeLocation").getValue();

                    boolean catchThisBool;
                    if (employeeLocation != null) {
                        catchThisBool = addUser(username, password, name, contact, type, employeeLocation);
                    } else {
                        catchThisBool = addUser(username, password, name, contact, type);
                    }

                }
                db.setValue(userMap);

            }

            /**
             * this function "handles" firebase's errors
             * @param databaseError the databaseError generated
             */
            @Override
            public void onCancelled(DatabaseError databaseError) {
                // System.out.println("Error -> it's Firebase's fault");
                Log.d("UserManager.java", "Error -> it's Firebase's fault");
            }
        });
    }
}
