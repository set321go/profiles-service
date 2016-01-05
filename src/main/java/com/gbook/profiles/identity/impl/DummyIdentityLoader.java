package com.gbook.profiles.identity.impl;

import com.gbook.profiles.identity.Identity;
import com.gbook.profiles.identity.IdentityLoader;
import com.google.inject.Singleton;

import java.util.Optional;

/**
 * Created with IntelliJ IDEA.
 * User: set321go
 * Date: 2016-01-04
 * Time: 19:31
 */
@Singleton
public class DummyIdentityLoader implements IdentityLoader {
    @Override
    public Optional<Identity> find(String aToken) {
        return aToken.equals("testToken") ? Optional.of(new Identity()) : Optional.empty();
    }
}
