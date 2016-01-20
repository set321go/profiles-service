package com.gbook.profiles.common;

import com.gbook.profiles.model.Result;
import com.gbook.profiles.identity.Identity;
import org.apache.commons.lang3.StringUtils;
import rx.Observable;

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

    public Observable<ProfileCommon> find(Identity aIdentity) {
        return loader.findFor(aIdentity)
                .defaultIfEmpty(new ProfileCommon());
    }

    public Observable<Result> update(Identity aIdentity, ProfileCommon aProfileCommon) {
        if (StringUtils.isBlank(aProfileCommon.getName())) {
            return Observable.just(Result.withClientCause("Unable to process 'name' must have a value"));
        }

        return loader.updateFor(aIdentity, aProfileCommon)
                .map(aBoolean -> {
                    if (aBoolean) {
                        return Result.success();
                    } else {
                        throw new IllegalStateException("Update Failed");
                    }
                })
                .onErrorReturn(aThrowable -> Result.withServerCause("There was an unexpected error processing your request."));
    }

    public Observable<Result> create(Identity aIdentity) {
        return create(aIdentity, new ProfileCommon("Anonymous"));
    }

    public Observable<Result> create(Identity aIdentity, ProfileCommon aProfileCommon) {
        if (StringUtils.isBlank(aProfileCommon.getName())) {
            return Observable.just(Result.withClientCause("Unable to process 'name' must have a value, remove this property to use default (Anon"));
        }

       return loader.create(aIdentity, aProfileCommon)
                .map(aBoolean -> {
                    if (aBoolean) {
                        return Result.success();
                    } else {
                        throw new IllegalStateException("Update Failed");
                    }
                })
                .onErrorReturn(aThrowable -> Result.withServerCause("There was an unexpected error processing your request."));
    }
}
