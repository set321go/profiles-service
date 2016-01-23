package com.gbook.profiles.contact.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

/**
 * Created with IntelliJ IDEA.
 * User: set321go
 * Date: 2016-01-10
 * Time: 11:22
 */
@JsonDeserialize(as = DefaultContactData.class)
public interface ContactData {
    String flatten();
}
