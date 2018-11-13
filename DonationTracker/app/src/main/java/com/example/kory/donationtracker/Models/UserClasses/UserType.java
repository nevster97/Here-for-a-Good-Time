package com.example.kory.donationtracker.Models.UserClasses;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * UserType enum
 */
public enum UserType {

    USER("User"),
    EMPLOYEE("Employee"),
    MANAGER("Manager"),
    ADMIN("Administrator");

    private final String _type;
//    private final boolean _canAddUser;
//    private final boolean _canLockUser;
//    private final boolean _canUpdateLoc;
//    private final boolean _canManageLoc;

    private static final List<UserType> list = Arrays.asList(USER, EMPLOYEE, MANAGER, ADMIN);

    /**
     * creates a UserType enum
     * @param type type
     */
    UserType(String type) {
        _type = type;
//        _canAddUser = add;
//        _canLockUser = lock;
//        _canUpdateLoc = update;
//        _canManageLoc = manage;
    }

    /**
     * gets the string type of the enum
     * @return string type of the enum
     */
    public String getStringType() { return _type; }

    /**
     * this was created because Enum.valueOf(String s) did not work at one point
     * @param s string type
     * @return corresponding user type
     */
    public static UserType typeFix(String s) {
        switch (s) {
            case "Employee":
                return EMPLOYEE;
            case "Manager":
                return MANAGER;
            case "Administrator":
                return ADMIN;
            default:
                return USER;
        }
    }

// --Commented out by Inspection START (11/12/18, 3:06 PM):
//    /**
//     * getter for canAddUser
//     * @return canAddUser
//     */
//    public boolean canAddUser() { return _canAddUser; }
// --Commented out by Inspection STOP (11/12/18, 3:06 PM)

//    /**
//     * getter for canLockUser
//     * @return canLockUser
//     */
//    public boolean canLockUser() { return _canLockUser; }
//
//    /**
//     * getter for canUpdateLocation
//     * @return canUpdateLoc
//     */
//    public boolean canUpdateLocation() { return _canUpdateLoc; }
//
//    /**
//     * getter for canManageLocation
//     * @return canManageLoc
//     */
//    public boolean canManageLocations() { return _canManageLoc; }

    /**
     * returns a list of the possible enums (for spinners)
     * @return a list of possible enums
     */
    public static List<UserType> getList() { return Collections.unmodifiableList(list); }
}
