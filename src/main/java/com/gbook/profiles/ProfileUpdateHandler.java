package com.gbook.profiles;

import ratpack.handling.Context;
import ratpack.handling.Handler;

import javax.inject.Singleton;

@Singleton
public class ProfileUpdateHandler implements Handler {
    @Override
    public void handle(Context ctx) throws Exception {
        ctx.next();
    }
}
