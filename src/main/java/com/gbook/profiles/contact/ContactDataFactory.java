package com.gbook.profiles.contact;

import com.gbook.profiles.contact.model.AddressContact;
import com.gbook.profiles.contact.model.ContactData;
import com.gbook.profiles.contact.model.DefaultContactData;
import com.google.inject.Singleton;

/**
 * Created with IntelliJ IDEA.
 * User: set321go
 * Date: 2016-01-23
 * Time: 13:54
 */
@Singleton
public class ContactDataFactory {
    public ContactData getContactData(String contactType, String contactValue) {
        switch (contactType) {
            case "phone" : return new DefaultContactData(contactValue);
            case "email" : return new DefaultContactData(contactValue);
            case "address" : return new AddressContact(contactValue);
            default: throw new IllegalArgumentException("No matching contact type found for " + contactType);
        }
    }
}
