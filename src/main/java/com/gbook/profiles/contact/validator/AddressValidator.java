package com.gbook.profiles.contact.validator;

import com.gbook.profiles.contact.model.AddressContact;
import com.gbook.profiles.contact.model.ProfileContact;
import org.apache.commons.lang3.StringUtils;

import java.util.Locale;

/**
 * Created with IntelliJ IDEA.
 * User: set321go
 * Date: 2016-01-13
 * Time: 21:50
 */
public class AddressValidator implements ProfileContactValidator {
    @Override
    public boolean validate(ProfileContact profileContact) {
        AddressContact addressContact = (AddressContact) profileContact.getValue();
        return isValidCountry(addressContact.getCountry())
                && StringUtils.isNotBlank(addressContact.getStreet())
                && StringUtils.isNotBlank(addressContact.getCity())
                && StringUtils.isNoneBlank(addressContact.getRegion())
                && StringUtils.isNotBlank(addressContact.getPostcode());
    }

    private boolean isValidCountry(String aCountry) {
        for (String code : Locale.getISOCountries()) {
            if (aCountry.equalsIgnoreCase(code)) {
                return true;
            }
        }

        return false;
    }
}
