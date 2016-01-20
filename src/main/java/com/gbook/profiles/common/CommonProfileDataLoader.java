package com.gbook.profiles.common;

import com.gbook.profiles.identity.Identity;
import rx.Observable;

/**
 * Created with IntelliJ IDEA.
 * User: set321go
 * Date: 2016-01-04
 * Time: 18:19
 */
public interface CommonProfileDataLoader {
    Observable<ProfileCommon> findFor(Identity aIdentity);

    Observable<Boolean> updateFor(Identity aIdentity, ProfileCommon aProfileCommon);

    Observable<Boolean> create(Identity aIdentity, ProfileCommon aProfileCommon);
}
