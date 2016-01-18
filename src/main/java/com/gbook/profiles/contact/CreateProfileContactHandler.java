package com.gbook.profiles.contact;

import com.gbook.profiles.contact.model.ProfileContacts;
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
 * Time: 16:07
 */
@Singleton
public class CreateProfileContactHandler implements Handler {
    private ProfileContactService profileContactService;

    @Inject
    public CreateProfileContactHandler(ProfileContactService aProfileContactService) {
        profileContactService = aProfileContactService;
    }

    @Override
    public void handle(Context ctx) throws Exception {
        Identity identity = ctx.get(Identity.class);
        ctx.parse(ProfileContacts.class)
                .onError(throwable -> ctx.next())
                .then(profileContacts -> {
                    Result result = profileContactService.create(identity, profileContacts);
                    result.processResponse(ctx);
                });
    }
}
