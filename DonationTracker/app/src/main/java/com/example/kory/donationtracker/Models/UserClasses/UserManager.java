package com.example.kory.donationtracker.Models.UserClasses;

import java.util.HashMap;
import java.util.Map;

public class UserManager {

    private Map<String, User> _userMap;

    public UserManager() {
        _userMap = new HashMap<>();
    }

    public boolean addUser(String username, String password, String name, String email, UserType type) {
        User user = new User(username, password, name, email, type);
        if (_userMap.containsKey(username)) {
            return false;
        }
        _userMap.put(username, user);
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

}
