package com.example.kory.donationtracker.Models.UserClasses;

/**
 * User class
 */
public class User {

    // private DatabaseReference db = FirebaseDatabase.getInstance().getReference();

    private String username;
    private String password;
    private String name;
    private String contact;
    private UserType type;
    private String employeeLocation;

    /**
     * Constructor for User (non employee)
     * @param username  username
     * @param password password
     * @param name name
     * @param contact email address
     * @param type type of user
     */
    public User(String username, String password, String name, String contact, String type) {
        this(username, password, name, contact, type, null);
    }

    // use this constructor when instantiating a location employee

    /**
     *  Constructor for User
     * @param username username
     * @param password password
     * @param name name
     * @param contact email address
     * @param type type of user
     * @param employeeLocation locations
     */
    public User(String username, String password, String name,
                String contact, String type, String employeeLocation) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.contact = contact;
        this.type = UserType.typeFix(type);
        this.employeeLocation = employeeLocation;

        // db.child("users").child(username).setValue(this);
    }


//    /**
//     * converts a user to a map of the user's attributes
//     * @return a map with the user's attributes
//     */
//    public Map<String, Object> toMap() {
//        Map<String, Object> map = new HashMap<>();
//        map.put("username", username);
//        map.put("password", password);
//        map.put("name", name);
//        map.put("contact", contact);
//        map.put("type", type.getStringType());
//        map.put("employeeLocation", employeeLocation);
//        return map;
//    }

    /**
     * checks if a passed in password equals a user's password
     * @param password the password to check
     * @return whether or not the param is equal to a user's password
     */
    public boolean checkPassword(String password) {
        return this.password.equals(password);
    }

    /**
     * gets the username
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * gets the password
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * gets the name
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * gets the email
     * @return the email
     */
    public String getContact() {
        return contact;
    }

    /**
     * gets the user's type in string form
     * @return the user's type in string form
     */
    public String getType() {
        return type.getStringType();
    }

    /**
     * gets the user's type
     * @return the user's type
     */
    public UserType getUserType() {
        return type;
    }

    /**
     * gets the employee location
     * @return the location
     */
    public String getEmployeeLocation() {
        return employeeLocation;
    }

    /**
     * sets the username
     * @param username new username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * sets the password
     * @param password new password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * sets the name
     * @param name new name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * sets the email
     * @param contact new email
     */
    public void setContact(String contact) {
        this.contact = contact;
    }

    /**
     * sets the type
     * @param type new type
     */
    public void setType(String type) {
        this.type = UserType.valueOf(type);
    }

    /**
     * sets the employee location
     * @param employeeLocation new location
     */
    public void setEmployeeLocation(String employeeLocation) {
        this.employeeLocation = employeeLocation;
    }

//    public void disableUser() { _isDisabled = true; }
//    public void enableUser() { _isDisabled = false; }
}
