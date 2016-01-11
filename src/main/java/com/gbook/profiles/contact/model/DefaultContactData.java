package com.gbook.profiles.contact.model;


import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created with IntelliJ IDEA.
 * User: set321go
 * Date: 2016-01-10
 * Time: 11:21
 */
public class DefaultContactData implements ContactData {
    private String contact;

    public DefaultContactData(@JsonProperty("contact") String aContact) {
        contact = aContact;
    }

    public String getContact() {
        return contact;
    }
}
