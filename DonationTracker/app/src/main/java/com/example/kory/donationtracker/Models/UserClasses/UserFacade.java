package com.example.kory.donationtracker.Models.UserClasses;

//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;

// << interface >>

/**
 * User facade class
 */
public final class UserFacade {

    /**
     * This is the only instance of UserFacade EVER!!!!
     */
    private static final UserFacade INSTANCE = new UserFacade();
    private final UserManager manager;
    private User currentUser;

//    private FirebaseDatabase database = FirebaseDatabase.getInstance();
//    private DatabaseReference ref = database.getReference();


    /**
     * gets the instance of the user facade
     * @return the instance
     */
    public static UserFacade getInstance() {
        return INSTANCE;
    }

    /**
     * Constructs the UserFacade
     */
    private UserFacade() {
        manager = new UserManager();
    }

    /**
     * gets the current user
     * @return current user
     */
    public User getCurrentUser() {
        return currentUser;
    }

    /**
     * checks if a current user exists
     * @return if a current user exists, true. otherwise false
     */
    private boolean hasLoggedInUser() {
        return currentUser != null;
    }

    /**
     * registers a new user
     * @param username username
     * @param pass password
     * @param name name
     * @param contact email
     * @param type user type
     * @return true if registration is successful, false otherwise
     */
    public boolean register(String username, String pass,
                            String name, String contact, String type) {
        // manager.downloadFromDB();
        if (hasLoggedInUser()) {
            return false;
        }
        // User user = null;
        return manager.addUser(username, pass, name, contact, type);
    }

    /**
     * logs in an existing user
     * @param username username
     * @param password password
     * @return true if login successful, otherwise false
     */
    public boolean login(String username, String password) {
        // manager.downloadFromDB();
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

    /**
     * a secret function that helps our database ;)
     */
    public void setup() {
        manager.setup();
    }

    /**
     * logs a current user out of the facade
     */
    public void logout() {
        currentUser = null;
    }


}
