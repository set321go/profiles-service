package com.gbook.profiles.identity.impl;

import com.gbook.profiles.identity.Identity;
import com.gbook.profiles.identity.IdentityLoader;
import com.google.common.collect.Maps;
import com.google.inject.Singleton;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;

/**
 * Created with IntelliJ IDEA.
 * User: set321go
 * Date: 2016-01-04
 * Time: 19:31
 */
@Singleton
public class DummyIdentityLoader implements IdentityLoader {
    Map<String, Identity> identities;

    public DummyIdentityLoader() {
        identities = Maps.newHashMap();
        identities.put("testToken", new Identity(UUID.fromString("85968854-b7f4-11e5-9912-ba0be0483c18")));
    }

    @Override
    public Optional<Identity> find(String aToken) {
        return identities.containsKey(aToken) ? Optional.of(identities.get(aToken)) : Optional.empty();
    }
}
