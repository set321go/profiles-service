package com.gbook.profiles;

import ratpack.handling.Context;
import ratpack.handling.Handler;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Set;

@Singleton
public class ProfileHandler implements Handler {

    private Set<Handler> profileParts;

    @Inject
    public ProfileHandler(Set<Handler> aProfileParts) {
        profileParts = aProfileParts; //might want to actually enforce some ordering on this.
    }

    @Override
    public void handle(Context ctx) throws Exception {
        Handler [] handlers = profileParts.toArray(new Handler[profileParts.size() + 1]);
        handlers[handlers.length -1 ] = ctx.get(ProfileRenderer.class);
        ctx.insert(handlers);
    }
}
