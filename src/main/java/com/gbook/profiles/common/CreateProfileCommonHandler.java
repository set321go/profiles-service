package com.gbook.profiles.common;

import com.gbook.profiles.identity.Identity;
import com.google.inject.Singleton;
import ratpack.handling.Context;
import ratpack.handling.Handler;
import ratpack.rx.RxRatpack;

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
            .onError(throwable -> RxRatpack.promiseSingle(commonProfileDataService.create(identity)).then(aResult -> aResult.processResponse(ctx)))
            .then(profileCommon -> RxRatpack.promiseSingle(commonProfileDataService.create(identity, profileCommon)).then(aResult -> aResult.processResponse(ctx)));
    }
}
