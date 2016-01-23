package com.gbook.profiles.contact;

import com.gbook.profiles.contact.model.ProfileContact;
import com.gbook.profiles.identity.Identity;
import rx.Observable;

/**
 * Created with IntelliJ IDEA.
 * User: set321go
 * Date: 2016-01-10
 * Time: 10:41
 */
public interface ProfileContactDataLoader {
    Observable<ProfileContact> findAllFor(Identity aIdentity);

    Observable<Boolean> updateContactInfo(Identity aIdentity, ProfileContact aProfileContacts);

    Observable<Boolean> create(Identity aIdentity, ProfileContact aProfileContact);
}
