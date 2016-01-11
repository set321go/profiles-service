package com.gbook.profiles.common.impl;

import com.gbook.profiles.common.CommonProfileDataLoader;
import com.gbook.profiles.common.ProfileCommon;
import com.gbook.profiles.identity.Identity;
import com.google.inject.Singleton;

import java.util.Optional;

/**
 * Created with IntelliJ IDEA.
 * User: set321go
 * Date: 2016-01-04
 * Time: 19:34
 */
@Singleton
public class DummyProfileDataLoader implements CommonProfileDataLoader {

    @Override
    public Optional<ProfileCommon> findFor(Identity aIdentity) {
        return Optional.of(new ProfileCommon("Dummy Name"));
    }

    @Override
    public void updateFor(Identity aIdentity, ProfileCommon aProfileCommon) {
        throw new UnsupportedOperationException();
    }
}
