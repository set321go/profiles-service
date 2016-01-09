package com.gbook.profiles;

import com.google.common.collect.Maps;
import ratpack.handling.Context;
import ratpack.handling.Handler;

import javax.inject.Singleton;

import java.util.Map;

import static ratpack.jackson.Jackson.json;

@Singleton
public class ProfileRenderer implements Handler {
    @Override
    public void handle(Context ctx) throws Exception {
        Map<String, Object> profile = Maps.newHashMap();
        Iterable<? extends ProfileData> allDataParts = ctx.getAll(ProfileData.class);
        allDataParts.forEach(data -> profile.putAll(data.asMap()));
        ctx.render(json(profile));
    }
}
