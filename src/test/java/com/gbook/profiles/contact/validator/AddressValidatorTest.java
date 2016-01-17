package com.gbook.profiles.contact.validator;

import com.gbook.profiles.contact.model.AddressContact;
import com.gbook.profiles.contact.model.ProfileContact;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created with IntelliJ IDEA.
 * User: set321go
 * Date: 2016-01-16
 * Time: 20:40
 */
public class AddressValidatorTest {
    ProfileContactValidator validator;

    @Before
    public void setup() {
        validator = new AddressValidator();
    }

    @Test
    public void validAddressIsSuccessfullyValidated() {
        ProfileContact contact = new ProfileContact("guid", "email", new AddressContact("street", "city", "region", "postcode", "CA"), true);
        assertTrue(validator.validate(contact));
    }

    @Test
    public void invalidCountryCodeIsInValid() {
        ProfileContact contact = new ProfileContact("guid", "email", new AddressContact("street", "city", "region", "postcode", "Potato"), true);
        assertFalse(validator.validate(contact));
    }
}
