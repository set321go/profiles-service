package com.gbook.profiles.contact.validator;

import com.gbook.profiles.contact.model.DefaultContactData;
import com.gbook.profiles.contact.model.ProfileContact;

/**
 * Created with IntelliJ IDEA.
 * User: set321go
 * Date: 2016-01-13
 * Time: 21:41
 */
public class EmailValidator implements ProfileContactValidator {
    @Override
    public boolean validate(ProfileContact profileContact) {
        DefaultContactData contactData = (DefaultContactData) profileContact.getValue();
        return org.apache.commons.validator.routines.EmailValidator.getInstance().isValid(contactData.getContact());
    }
}
