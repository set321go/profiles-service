package com.gbook.profiles.common;

import com.gbook.profiles.identity.Identity;
import com.gbook.profiles.model.Result;
import com.google.inject.Singleton;
import ratpack.handling.Context;
import ratpack.handling.Handler;

import javax.inject.Inject;

/**
 * Created with IntelliJ IDEA.
 * User: set321go
 * Date: 2016-01-17
 * Time: 13:06
 */
@Singleton
public class CreateProfileCommonHandler implements Handler {
    private CommonProfileDataService commonProfileDataService;

    @Inject
    public CreateProfileCommonHandler(CommonProfileDataService aCommonProfileDataService) {
        commonProfileDataService = aCommonProfileDataService;
    }

    @Override
    public void handle(Context ctx) throws Exception {
        Identity identity = ctx.get(Identity.class);
        ctx.parse(ProfileCommon.class)
                .onError(throwable -> {
                    Result result = commonProfileDataService.create(identity);
                    result.processResponse(ctx);
                })
                .then(profileCommon -> {
                    Result result = commonProfileDataService.create(identity, profileCommon);
                    result.processResponse(ctx);
                });
    }
}
