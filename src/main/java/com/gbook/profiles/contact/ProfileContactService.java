package com.gbook.profiles.contact;

import com.gbook.profiles.model.Result;
import com.gbook.profiles.contact.model.ProfileContact;
import com.gbook.profiles.contact.model.ProfileContacts;
import com.gbook.profiles.contact.validator.AddressValidator;
import com.gbook.profiles.contact.validator.EmailValidator;
import com.gbook.profiles.contact.validator.ProfileContactValidator;
import com.gbook.profiles.identity.Identity;
import com.google.common.collect.ImmutableMap;
import rx.Observable;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: set321go
 * Date: 2016-01-10
 * Time: 09:41
 */
@Singleton
public class ProfileContactService {
    private ProfileContactDataLoader loader;
    private Map<String, ProfileContactValidator> validators = ImmutableMap.of(
            "email", new EmailValidator(),
            "addressValidator", new AddressValidator()
    );

    @Inject
    public ProfileContactService(ProfileContactDataLoader aLoader) {
        loader = aLoader;
    }

    public Observable<ProfileContact> findAll(Identity aIdentity) {
        return loader.findAllFor(aIdentity);
    }

    public Observable<Result> updateContacts(Identity aIdentity, ProfileContacts aProfileContacts) {
        if (validateContacts(aProfileContacts)) {
            return Observable.just(Result.withClientCause("Invalid Contacts"));
        }

        return Observable.from(aProfileContacts.getContactList())
                .flatMap(aProfileContact ->
                        loader.updateContactInfo(aIdentity, aProfileContact)
                                .map(aBoolean -> {
                                    if (aBoolean) {
                                        return Result.success();
                                    } else {
                                        throw new IllegalStateException("Update Failed");
                                    }
                                })
                                .onErrorReturn(aThrowable -> Result.withServerCause("There was an unexpected error processing your request."))
                );
    }

    public Observable<Result> create(Identity aIdentity, ProfileContacts aProfileContacts) {
        if (validateContacts(aProfileContacts)) {
            return Observable.just(Result.withClientCause("Invalid Contacts"));
        }

        return Observable.from(aProfileContacts.getContactList())
                .flatMap(aProfileContact ->
                    loader.create(aIdentity, aProfileContact)
                            .map(aBoolean -> {
                                if (aBoolean) {
                                    return Result.success();
                                } else {
                                    throw new IllegalStateException("Update Failed");
                                }
                            })
                            .onErrorReturn(aThrowable -> Result.withServerCause("There was an unexpected error processing your request."))
                );
    }

    private boolean validateContacts(ProfileContacts aProfileContacts) {
        boolean isValid = false;
        for (ProfileContact profileContact : aProfileContacts.getContactList()) {
            ProfileContactValidator validator = validators.get(profileContact.getType().toLowerCase());
            isValid = validator.validate(profileContact);
        }

        return !isValid;
    }
}
