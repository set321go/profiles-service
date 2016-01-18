package com.gbook.profiles.common;

import com.gbook.profiles.model.Result;
import com.gbook.profiles.identity.Identity;
import com.google.inject.Singleton;
import ratpack.handling.Context;
import ratpack.handling.Handler;
import ratpack.registry.Registry;

import javax.inject.Inject;

import static ratpack.jackson.Jackson.json;

/**
 * Created with IntelliJ IDEA.
 * User: set321go
 * Date: 2016-01-04
 * Time: 18:02
 */
@Singleton
public class ProfileCommonHandler implements Handler {
    private CommonProfileDataService commonProfileDataService;

    @Inject
    public ProfileCommonHandler(CommonProfileDataService aCommonProfileDataService) {
        commonProfileDataService = aCommonProfileDataService;
    }

    @Override
    public void handle(Context ctx) throws Exception {
        Identity identity = ctx.get(Identity.class);

        ctx.byMethod(methodSpec ->
            methodSpec.get(() -> ctx.next(Registry.single(commonProfileDataService.find(identity))))
                .patch(() ->
                    ctx.parse(ProfileCommon.class)
                            .onError(throwable -> ctx.getResponse().status(400).send("Invaild content, unable to parse 'name'"))
                            .then(profileCommon -> {
                                Result result = commonProfileDataService.update(identity, profileCommon);
                                result.processResponse(ctx);
                            })
            )
        );
    }
}
