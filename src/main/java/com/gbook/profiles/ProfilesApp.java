package com.gbook.profiles;

import com.gbook.profiles.identity.IdentityHandler;
import com.gbook.profiles.model.ApplicationConfig;
import ratpack.guice.Guice;
import ratpack.server.BaseDir;
import ratpack.server.RatpackServer;

public class ProfilesApp {

  public static void main(String[] args) throws Exception {
    RatpackServer.start(s -> s
        .serverConfig(c -> c
                .baseDir(BaseDir.find())
                .yaml("application.yml")
                .require("/application", ApplicationConfig.class)
        )
        .registry(Guice.registry(b -> b.module(ProfileModule.class)))
        .handlers(chain -> chain
            .all(IdentityHandler.class)
            .get("profile", ProfileHandler.class)
            .post("new", NewProfileHandler.class)
        )
    );
  }
}