package com.gbook.profiles.common;

import com.gbook.profiles.model.Result;
import com.gbook.profiles.identity.Identity;
import org.apache.commons.lang3.StringUtils;

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

    public Result update(Identity aIdentity, ProfileCommon aProfileCommon) {
        if (StringUtils.isBlank(aProfileCommon.getName())) {
            return Result.withClientCause("Unable to process 'name' must have a value");
        }

        try {
            loader.updateFor(aIdentity, aProfileCommon);
        } catch (Exception exception) {
            return Result.withServerCause("There was an unexpected error processing your request.");
        }

        return Result.success();
    }

    public Result create(Identity aIdentity) {
        return create(aIdentity, new ProfileCommon("Anonymous"));
    }

    public Result create(Identity aIdentity, ProfileCommon aProfileCommon) {
        if (StringUtils.isBlank(aProfileCommon.getName())) {
            return Result.withClientCause("Unable to process 'name' must have a value, remove this property to use default (Anon");
        }

        try {
            loader.create(aIdentity, aProfileCommon);
        } catch (Exception exception) {
            return Result.withServerCause("There was an unexpected error processing your request.");
        }

        return Result.success();
    }
}
