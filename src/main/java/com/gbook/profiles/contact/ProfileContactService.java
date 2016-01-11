package com.gbook.profiles.contact;

import com.gbook.profiles.Result;
import com.gbook.profiles.contact.model.ProfileContact;
import com.gbook.profiles.contact.model.ProfileContacts;
import com.gbook.profiles.identity.Identity;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: set321go
 * Date: 2016-01-10
 * Time: 09:41
 */
@Singleton
public class ProfileContactService {
    private ProfileContactDataLoader loader;

    @Inject
    public ProfileContactService(ProfileContactDataLoader aLoader) {
        loader = aLoader;
    }

    public List<ProfileContact> findAll(Identity aIdentity) {
        return loader.findAllFor(aIdentity);
    }

    public Result updateContacts(Identity aIdentity, ProfileContacts aProfileContacts) {
//        loader.updateContactInfo
        return null;
    }
}
