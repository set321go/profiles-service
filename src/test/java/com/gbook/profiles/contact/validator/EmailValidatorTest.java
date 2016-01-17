package com.gbook.profiles.contact.validator;

import com.gbook.profiles.contact.model.DefaultContactData;
import com.gbook.profiles.contact.model.ProfileContact;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created with IntelliJ IDEA.
 * User: set321go
 * Date: 2016-01-16
 * Time: 10:44
 */
public class EmailValidatorTest {
    ProfileContactValidator validator;

    @Before
    public void setup() {
        validator = new EmailValidator();
    }

    @Test
    public void validEmailIsSuccessfullyValidated() {
        ProfileContact contact = new ProfileContact("guid", "email", new DefaultContactData("a@a.com"), true);
        assertTrue(validator.validate(contact));
    }

    @Test
    public void invalidEmailIsInvalidValidated() {
        ProfileContact contact = new ProfileContact("guid", "email", new DefaultContactData("string"), true);
        assertFalse(validator.validate(contact));
    }

    @Test
    public void emptyEmailIsInvalidValidated() {
        ProfileContact contact = new ProfileContact("guid", "email", new DefaultContactData(""), true);
        assertFalse(validator.validate(contact));
    }

    @Test
    public void nullEmailIsInvalidValidated() {
        ProfileContact contact = new ProfileContact("guid", "email", new DefaultContactData(null), true);
        assertFalse(validator.validate(contact));
    }
}
