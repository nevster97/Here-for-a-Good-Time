package com.example.kory.donationtracker.Models.UserClasses;

import java.util.Arrays;
import java.util.List;

/**
 * UserType enum
 */
public enum UserType {

    USER("User", false, false, false, false),
    EMPLOYEE("Employee", false, false, true, false),
    MANAGER("Manager", true, false, true, true),
    ADMIN("Administrator", true, true, true, true);

    private String _type;
    private boolean _canAddUser;
    private boolean _canLockUser;
    private boolean _canUpdateLoc;
    private boolean _canManageLoc;

    private static List<UserType> list = Arrays.asList(USER, EMPLOYEE, MANAGER, ADMIN);

    /**
     * creates a UserType enum
     * @param type type
     * @param add can add users
     * @param lock can lock users
     * @param update can update users
     * @param manage can manage users
     */
    UserType(String type, boolean add, boolean lock, boolean update, boolean manage) {
        _type = type;
        _canAddUser = add;
        _canLockUser = lock;
        _canUpdateLoc = update;
        _canManageLoc = manage;
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

    /**
     * getter for canAddUser
     * @return canAddUser
     */
    public boolean canAddUser() { return _canAddUser; }

    /**
     * getter for canLockUser
     * @return canLockUser
     */
    public boolean canLockUser() { return _canLockUser; }

    /**
     * getter for canUpdateLocation
     * @return canUpdateLoc
     */
    public boolean canUpdateLocation() { return _canUpdateLoc; }

    /**
     * getter for canManageLocation
     * @return canManageLoc
     */
    public boolean canManageLocations() { return _canManageLoc; }

    /**
     * returns a list of the possible enums (for spinners)
     * @return a list of possible enums
     */
    public static List<UserType> getList() { return list; }
}
