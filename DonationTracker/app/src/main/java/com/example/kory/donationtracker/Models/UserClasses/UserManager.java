package com.example.kory.donationtracker.Models.UserClasses;

import java.util.HashMap;
import java.util.Map;

import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class UserManager {

    private Map<String, User> userMap;
    private static DatabaseReference db = FirebaseDatabase.getInstance().getReference("users");
    private static User user;

    public UserManager() {
        // database = FirebaseDatabase.getInstance();
        // ref = database.getReference("users");
        userMap = new HashMap<>();
//        db.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
////                Map<String, Object> map = dataSnapshot.getValue(Map.class);
////                for (String s : map.keySet()) {
////                    System.out.println(s);
////                }
//                for (DataSnapshot ds : dataSnapshot.getChildren()) {
//                    String username = (String) ds.child("username").getValue();
//                    String password = (String) ds.child("password").getValue();
//                    String name = (String) ds.child("name").getValue();
//                    String contact = (String) ds.child("contact").getValue();
//                    String type = (String) ds.child("type").getValue();
//                    String employeeLocation = (String) ds.child("employeeLocation").getValue();
//
//                    boolean catchThisBool;
//                    if (employeeLocation != null) {
//                        catchThisBool = addUser(username, password, name, contact, type, employeeLocation);
//                    } else {
//                        System.out.println(type);
//                        System.out.println(type.length());
//                        catchThisBool = addUser(username, password, name, contact, type);
//                    }
//
//                }
//                db.setValue(userMap);
//
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//                System.out.println("Error -> it's Firebase's fault");
//            }
//        });

    }

    public boolean addUser(String username, String password, String name, String email, String type, String location) {
        user = new User(username, password, name, email, type, location);
        if (userMap.containsKey(username)) {
            return false;
        }
        userMap.put(username, user);
        db.setValue(userMap);
        return true;
    }

    public boolean addUser(String username, String password, String name, String email, String type) {
        User user = new User(username, password, name, email, type);
        if (userMap.containsKey(username)) {
            return false;
        }
        userMap.put(username, user);
        db.setValue(userMap);
        return true;
    }

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

    public void setup() {
        db.addValueEventListener(new ValueEventListener() {
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
                        System.out.println(type);
                        System.out.println(type.length());
                        catchThisBool = addUser(username, password, name, contact, type);
                    }

                }
                db.setValue(userMap);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("Error -> it's Firebase's fault");
            }
        });
    }

//    public void uploadToDB() {
//        ref.setValue(_userMap);
//    }

//    public void downloadFromDB() {
//        ref.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
////                Map<String, Object> map = dataSnapshot.getValue(Map.class);
////                for (String s : map.keySet()) {
////                    System.out.print(s);
////                }
////                // System.out.println(value);
//                for (DataSnapshot ds : dataSnapshot.getChildren()) {
//                    String name = (String) ds.child("_name").getValue();
//                    String contact = (String) ds.child("_contact").getValue();
//                    String password = (String) ds.child("_password").getValue();
//                    String type = (String) ds.child("_type").getValue();
//
//                    if (UserType.valueOf(type) == UserType.EMPLOYEE) {
//                        // Map<String, Object> location = (Map) ds.child("_employeeLocation").getValue();
//                        String location = (String) ds.child("_employeeLocation").getValue();
////                        if (location != null) {
////
////                            boolean catcher = addUser(contact, password, name, contact, UserType.EMPLOYEE, address);
////                        }
////                        boolean catcher = addUser(contact, password, name, contact, UserType.EMPLOYEE, null);
//                        boolean catcher = addUser(contact, password, name, contact, UserType.valueOf(type), location);
//
//                    } else {
//                        boolean catcher = addUser(contact, password, name, contact, UserType.valueOf(type));
//                    }
//                }
//                uploadToDB();
//            }
//
//            @Override
//            public void onCancelled(DatabaseError error) {
//                System.out.println(error);
//            }
//        });
//    }

}
