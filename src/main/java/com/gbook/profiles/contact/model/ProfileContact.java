package com.gbook.profiles.contact.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.gbook.profiles.ProfileData;
import com.google.common.collect.Maps;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: set321go
 * Date: 2016-01-10
 * Time: 09:36
 */
public class ProfileContact implements ProfileData {
    private String guid;
    private String type;
    private ContactData value;
    private boolean defaultContact;

    public ProfileContact(@JsonProperty("guid") String aGuid,
                          @JsonProperty("type") String aType,
                          @JsonProperty("value") ContactData aValue,
                          @JsonProperty("defaultContact") boolean aDefaultContact) {
        guid = aGuid;
        type = aType;
        value = aValue;
        defaultContact = aDefaultContact;
    }

    public String getGuid() {
        return guid;
    }

    public String getType() {
        return type;
    }

    public ContactData getValue() {
        return value;
    }

    public boolean isDefaultContact() {
        return defaultContact;
    }

    @Override
    public Map<? extends String, ?> asMap() {
        Map<String, Object> data = Maps.newHashMap();
        data.put("guid", guid);
        data.put("type", type);
        data.put("value", value);
        data.put("isDefaultContact", defaultContact);
        return data;
    }
}
