package com.gbook.profiles;

import ratpack.handling.Context;
import ratpack.handling.Handler;

import javax.inject.Singleton;

/**
 * Created with IntelliJ IDEA.
 * User: set321go
 * Date: 2016-01-17
 * Time: 12:50
 */
@Singleton
public class CreatedRenderer implements Handler {
    @Override
    public void handle(Context ctx) throws Exception {
        ctx.getResponse().status(201).send();
    }
}
