package com.gbook.profiles.common;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.gbook.profiles.ProfileData;
import com.google.common.collect.Maps;

import java.util.HashMap;
import java.util.Map;

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

    public ProfileCommon(@JsonProperty("name") String aName) {
        name = aName;
    }

    public String getName() {
        return name;
    }

    @Override
    public Map<? extends String, ?> asMap() {
        Map<String, String> data = Maps.newHashMap();
        data.put("name", name);
        return data;
    }
}
