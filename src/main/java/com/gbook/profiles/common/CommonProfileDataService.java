package com.gbook.profiles.common;

import com.gbook.profiles.identity.Identity;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created with IntelliJ IDEA.
 * User: set321go
 * Date: 2016-01-04
 * Time: 18:13
 */
@Singleton
public class CommonProfileDataService {
    private CommonProfileDataLoader loader;

    @Inject
    private CommonProfileDataService(CommonProfileDataLoader aLoader) {
        loader = aLoader;
    }

    public ProfileCommon find(Identity aIdentity) {
        return loader.findFor(aIdentity)
                .orElse(new ProfileCommon());
    }
}
