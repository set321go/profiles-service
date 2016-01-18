package com.gbook.profiles.common;

import com.gbook.profiles.identity.Identity;

import java.util.Optional;

/**
 * Created with IntelliJ IDEA.
 * User: set321go
 * Date: 2016-01-04
 * Time: 18:19
 */
public interface CommonProfileDataLoader {
    Optional<ProfileCommon> findFor(Identity aIdentity);

    void updateFor(Identity aIdentity, ProfileCommon aProfileCommon);

    void create(Identity aIdentity, ProfileCommon aProfileCommon);
}
