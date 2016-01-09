package com.gbook.profiles;

import ratpack.handling.Context;
import ratpack.handling.Handler;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: set321go
 * Date: 2016-01-03
 * Time: 13:21
 */
@Singleton
public class ProfileReadHandler implements Handler {

    private Set<Handler> profileParts;

    @Inject
    public ProfileReadHandler(Set<Handler> aProfileParts) {
        profileParts = aProfileParts; //might want to actually enforce some ordering on this.
    }

    @Override
    public void handle(Context ctx) throws Exception {
        Handler [] handlers = profileParts.toArray(new Handler[profileParts.size() + 1]);
        handlers[handlers.length -1 ] = ctx.get(ProfileRenderer.class);
        ctx.insert(handlers);
    }
}
