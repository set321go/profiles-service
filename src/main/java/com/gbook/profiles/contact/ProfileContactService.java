package com.gbook.profiles.contact;

import com.gbook.profiles.Result;
import com.gbook.profiles.contact.model.ProfileContact;
import com.gbook.profiles.contact.model.ProfileContacts;
import com.gbook.profiles.contact.validator.AddressValidator;
import com.gbook.profiles.contact.validator.EmailValidator;
import com.gbook.profiles.contact.validator.ProfileContactValidator;
import com.gbook.profiles.identity.Identity;
import com.google.common.collect.ImmutableMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: set321go
 * Date: 2016-01-10
 * Time: 09:41
 */
@Singleton
public class ProfileContactService {
    private final static Logger LOGGER = LoggerFactory.getLogger(ProfileContactService.class);

    private ProfileContactDataLoader loader;
    private Map<String, ProfileContactValidator> validators = ImmutableMap.of(
            "email", new EmailValidator(),
            "addressValidator", new AddressValidator()
    );

    @Inject
    public ProfileContactService(ProfileContactDataLoader aLoader) {
        loader = aLoader;
    }

    public List<ProfileContact> findAll(Identity aIdentity) {
        return loader.findAllFor(aIdentity);
    }

    public Result updateContacts(Identity aIdentity, ProfileContacts aProfileContacts) {
        if (validateContacts(aProfileContacts)) {
            return Result.withClientCause("Invalid Contacts");
        }

        try {
            for (ProfileContact profileContact : aProfileContacts.getContactList()) {
                loader.updateContactInfo(aIdentity, profileContact);
            }
        } catch (Exception exception) {
            LOGGER.warn("Failed to update contact info", exception);
            return Result.withServerCause(exception.getMessage());
        }

        return Result.success();
    }

    private boolean validateContacts(ProfileContacts aProfileContacts) {
        boolean isValid = false;
        for (ProfileContact profileContact : aProfileContacts.getContactList()) {
            ProfileContactValidator validator = validators.get(profileContact.getType().toLowerCase());
            isValid = validator.validate(profileContact);
        }

        return isValid;
    }
}
