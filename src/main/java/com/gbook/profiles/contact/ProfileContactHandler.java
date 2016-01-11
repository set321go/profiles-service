package com.gbook.profiles.contact;

import com.gbook.profiles.Result;
import com.gbook.profiles.contact.model.ProfileContact;
import com.gbook.profiles.contact.model.ProfileContacts;
import com.gbook.profiles.identity.Identity;
import ratpack.handling.Context;
import ratpack.handling.Handler;
import ratpack.registry.Registry;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: set321go
 * Date: 2016-01-10
 * Time: 09:33
 */
@Singleton
public class ProfileContactHandler implements Handler {
    private ProfileContactService profileContactService;

    @Inject
    public ProfileContactHandler(ProfileContactService aProfileContactService) {
        profileContactService = aProfileContactService;
    }

    @Override
    public void handle(Context ctx) throws Exception {
        Identity identity = ctx.get(Identity.class);
        ctx.byMethod(methodSpec ->
                methodSpec.get(() -> {
                    List<ProfileContact> profileContacts = profileContactService.findAll(identity);
                    ctx.next(Registry.single(new ProfileContacts(profileContacts)));
                })
                .patch(() ->
                        ctx.parse(ProfileContacts.class)
                        .onError(throwable -> ctx.getResponse().status(400).send("Invalid content, unable to parse 'contacts'"))
                        .then(profileContacts -> {
                            Result result = profileContactService.updateContacts(identity, profileContacts);
                            result.processResponse(ctx);
                        })
                )
        );
    }
}
