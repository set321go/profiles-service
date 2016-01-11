package com.gbook.profiles.contact.impl;

import com.gbook.profiles.contact.model.AddressContact;
import com.gbook.profiles.contact.model.DefaultContactData;
import com.gbook.profiles.contact.model.ProfileContact;
import com.gbook.profiles.contact.ProfileContactDataLoader;
import com.gbook.profiles.identity.Identity;
import com.google.common.collect.Lists;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: set321go
 * Date: 2016-01-10
 * Time: 10:46
 */
public class DummyProfileDataLoader implements ProfileContactDataLoader {
    @Override
    public List<ProfileContact> findAllFor(Identity aIdentity) {
        return Lists.newArrayList(
                new ProfileContact("email", new DefaultContactData("a@a.com"), true),
                new ProfileContact("tel", new DefaultContactData("123-123-1234"), false),
                new ProfileContact("address", new AddressContact("Street", "City", "Region", "Postcode", "Country"), false));
    }
}
