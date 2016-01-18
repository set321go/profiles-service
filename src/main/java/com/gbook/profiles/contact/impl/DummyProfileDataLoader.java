package com.gbook.profiles.contact.impl;

import com.gbook.profiles.contact.model.AddressContact;
import com.gbook.profiles.contact.model.DefaultContactData;
import com.gbook.profiles.contact.model.ProfileContact;
import com.gbook.profiles.contact.ProfileContactDataLoader;
import com.gbook.profiles.identity.Identity;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: set321go
 * Date: 2016-01-10
 * Time: 10:46
 */
public class DummyProfileDataLoader implements ProfileContactDataLoader {
    private Map<UUID, List<ProfileContact>> profileContacts;

    public DummyProfileDataLoader() {
        profileContacts = Maps.newHashMap();
        profileContacts.put(UUID.fromString("85968854-b7f4-11e5-9912-ba0be0483c18"), Lists.newArrayList(
                new ProfileContact("1", "email", new DefaultContactData("a@a.com"), true),
                new ProfileContact("2", "tel", new DefaultContactData("123-123-1234"), false),
                new ProfileContact("3", "address", new AddressContact("Street", "City", "Region", "Postcode", "Country"), false)));
    }

    @Override
    public List<ProfileContact> findAllFor(Identity aIdentity) {
        return profileContacts.get(aIdentity.getGuid());
    }

    @Override
    public void updateContactInfo(Identity aIdentity, ProfileContact aProfileContact) throws Exception {
        Optional<ProfileContact> contact = profileContacts.get(aIdentity.getGuid())
                .stream()
                .filter(profileContact -> profileContact.getGuid().equalsIgnoreCase(aProfileContact.getGuid()))
                .findFirst();

        if (contact.isPresent()) {
            profileContacts.get(aIdentity.getGuid()).remove(contact.get());
            profileContacts.get(aIdentity.getGuid()).add(aProfileContact);
        } else {
            profileContacts.get(aIdentity.getGuid()).add(aProfileContact);
        }
    }

    @Override
    public void create(Identity aIdentity, ProfileContact aProfileContact) {
        profileContacts.get(aIdentity.getGuid()).add(aProfileContact);
    }
}
