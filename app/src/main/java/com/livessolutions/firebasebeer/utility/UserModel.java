package com.livessolutions.firebasebeer.utility;

/**
 * Created by Admins on 11/26/17.
 */

public class UserModel {

    private String uidString, nameDisplayString;

    public UserModel() {

    } // For Getter

    public UserModel(String uidString, String nameDisplayString) {
        this.uidString = uidString;
        this.nameDisplayString = nameDisplayString;
    } // For Setter

    public String getUidString() {
        return uidString;
    }

    public void setUidString(String uidString) {
        this.uidString = uidString;
    }

    public String getNameDisplayString() {
        return nameDisplayString;
    }

    public void setNameDisplayString(String nameDisplayString) {
        this.nameDisplayString = nameDisplayString;
    }
}   // Main Class
