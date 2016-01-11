package com.gbook.profiles.contact.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.gbook.profiles.ProfileData;
import com.google.common.collect.Maps;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: set321go
 * Date: 2016-01-10
 * Time: 11:05
 */
public class ProfileContacts implements ProfileData {
    private List<ProfileContact> contactList;

    public ProfileContacts(@JsonProperty("contacts") List<ProfileContact> aContactList) {
        contactList = aContactList;
    }

    @Override
    public Map<? extends String, ?> asMap() {
        Map<String, Object> data = Maps.newHashMap();
        data.put("contacts", contactList);
        return data;
    }
}
