package com.gbook.profiles;

import ratpack.handling.Context;
import ratpack.handling.Handler;

import javax.inject.Singleton;

/**
 * Created with IntelliJ IDEA.
 * User: set321go
 * Date: 2016-01-03
 * Time: 14:02
 */
@Singleton
public class ProfileUpdateHandler implements Handler {
    @Override
    public void handle(Context ctx) throws Exception {
        ctx.next();
    }
}
