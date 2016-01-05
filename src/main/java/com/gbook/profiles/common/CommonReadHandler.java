package com.gbook.profiles.common;

import com.gbook.profiles.identity.Identity;
import com.google.inject.Singleton;
import ratpack.handling.Context;
import ratpack.handling.Handler;
import ratpack.registry.Registry;

import javax.inject.Inject;

/**
 * Created with IntelliJ IDEA.
 * User: set321go
 * Date: 2016-01-04
 * Time: 18:02
 */
@Singleton
public class CommonReadHandler implements Handler {
    private CommonProfileDataService commonProfileDataService;

    @Inject
    public CommonReadHandler(CommonProfileDataService aCommonProfileDataService) {
        commonProfileDataService = aCommonProfileDataService;
    }

    @Override
    public void handle(Context ctx) throws Exception {
        Identity identity = ctx.get(Identity.class);
        ProfileCommon profileCommon = commonProfileDataService.find(identity);
        ctx.next(Registry.single(profileCommon));
    }
}
