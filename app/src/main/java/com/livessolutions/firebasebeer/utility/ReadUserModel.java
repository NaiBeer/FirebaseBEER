package com.livessolutions.firebasebeer.utility;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Admins on 11/26/17.
 */

public class ReadUserModel {

    private String uidString,nameDisplayString;
    public Map<String, Boolean> stars = new HashMap<>();


    public ReadUserModel() {
    }

    public ReadUserModel(String uidString, String nameDisplayString) {
        this.uidString = uidString;
        this.nameDisplayString = nameDisplayString;
    }


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
} // Main Class
