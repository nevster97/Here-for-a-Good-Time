package com.example.kory.donationtracker.Models.UserClasses;

// << interface >>
public class UserFacade {

    /**
     * This is the only instance of UserFacade EVER!!!!
     */
    private static UserFacade INSTANCE = new UserFacade();
    private UserManager manager;
    private User currentUser;

    public static UserFacade getInstance() { return INSTANCE; }

    /**
     * Constructs the UserFacade
     */
    public UserFacade() {
        manager = new UserManager();
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public boolean hasLoggedInUser() {
        return currentUser != null;
    }

    public boolean register(String username, String pass, String name, String contact, UserType type) {
        if (hasLoggedInUser()) {
            return false;
        }
        User user = null;
        if (manager.addUser(username, pass, name, contact, type)) {
            return true;
        } else {
            return false;
        }
        // return manager.addUser(username, pass, name, contact, type);
    }

    public boolean login(String username, String password) {
        if (hasLoggedInUser()) {
            return false;
        }

        User user = manager.login(username, password);
        if (user != null) {
            currentUser = user;
            return true;
        } else {
            return false;
        }
    }

    public void logout() {
        currentUser = null;
    }



}
