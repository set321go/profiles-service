package com.gbook.profiles.contact;

import com.gbook.profiles.contact.model.ProfileContact;
import com.gbook.profiles.identity.Identity;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: set321go
 * Date: 2016-01-10
 * Time: 10:41
 */
public interface ProfileContactDataLoader {
    List<ProfileContact> findAllFor(Identity aIdentity);

    void updateContactInfo(Identity aIdentity, ProfileContact aProfileContacts) throws Exception;

    void create(Identity aIdentity, ProfileContact aProfileContact);
}
