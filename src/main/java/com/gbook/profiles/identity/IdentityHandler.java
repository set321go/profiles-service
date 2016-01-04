package com.gbook.profiles.identity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ratpack.handling.Context;
import ratpack.handling.Handler;
import ratpack.http.Headers;
import ratpack.http.Request;
import ratpack.registry.Registry;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Optional;

/**
 * Created with IntelliJ IDEA.
 * User: set321go
 * Date: 2016-01-03
 * Time: 14:08
 */
@Singleton
public class IdentityHandler implements Handler {
    private final static Logger NO_IDENTITY_LOG = LoggerFactory.getLogger("no-identity-log");

    private IdentityService identityService;

    @Inject
    public IdentityHandler(IdentityService aIdentityService) {
        identityService = aIdentityService;
    }

    @Override
    public void handle(Context ctx) throws Exception {
        Headers headers = ctx.getRequest().getHeaders();
        String identityToken = headers.get("Client-Identity"); //Ideally there should be a method to get as byte/char
        Optional<Identity> identity = identityService.isIdentifiable(identityToken);

        if (identity.isPresent()) {
            ctx.next(Registry.single(identity.get()));
        } else {
            Request request = ctx.getRequest();
            NO_IDENTITY_LOG.info("{} {} {}", request.getRemoteAddress(), request.getMethod(), request.getPath());
            ctx.getResponse().status(401).send("Unknown Identity");
        }
    }
}
