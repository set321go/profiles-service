package com.gbook.profiles.common;

import com.gbook.profiles.ProfileData;

/**
 * Created with IntelliJ IDEA.
 * User: set321go
 * Date: 2016-01-04
 * Time: 18:16
 */
public class ProfileCommon implements ProfileData {
    private String name;

    public ProfileCommon() {
        this("");
    }

    public ProfileCommon(String aName) {
        name = aName;
    }

    public String getName() {
        return name;
    }
}
