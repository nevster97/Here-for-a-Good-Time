package com.example.isaac.donationtracker.models;

public enum UserType {
    USER("User", false, false, false, false),
    EMPLOYEE("Employee", false, false, true, false),
    MANAGER("Manager", true, true, true, true),
    ADMIN("Administrator", true, true, true, true);

    private String _type;
    private boolean _canAddUser;
    private boolean _canLockUser;
    private boolean _canUpdateLoc;
    private boolean _canManageLoc;

    UserType(String type, boolean add, boolean lock, boolean update, boolean create) {
        _type = type;
        _canAddUser = add;
        _canLockUser = lock;
        _canUpdateLoc = update;
        _canManageLoc = create;
    }

    public boolean canAddUsers() { return _canAddUser; }
    public boolean canLockUsers() { return _canLockUser; }
    public boolean canUpdateLocations() { return _canUpdateLoc; }
    public boolean canManageLocations() { return _canManageLoc; }


}
