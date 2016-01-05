package com.gbook.profiles.common.impl;

import com.gbook.profiles.common.CommonProfileDataLoader;
import com.gbook.profiles.common.ProfileCommon;
import com.gbook.profiles.identity.Identity;

import java.util.Optional;

/**
 * Created with IntelliJ IDEA.
 * User: set321go
 * Date: 2016-01-04
 * Time: 19:34
 */
public class DummyProfileDataLoader implements CommonProfileDataLoader{
    @Override
    public Optional<ProfileCommon> findFor(Identity aIdentity) {
        return Optional.of(new ProfileCommon("Dummy Name"));
    }
}
