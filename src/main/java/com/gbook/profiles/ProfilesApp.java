package com.gbook.profiles;

import com.gbook.profiles.identity.IdentityHandler;
import com.gbook.profiles.model.ApplicationConfig;
import com.gbook.profiles.model.DatabaseConfig;
import ratpack.guice.Guice;
import ratpack.rx.RxRatpack;
import ratpack.server.BaseDir;
import ratpack.server.RatpackServer;

public class ProfilesApp {

  public static void main(String[] args) throws Exception {
    RxRatpack.initialize();
    RatpackServer.start(s -> s
        .serverConfig(c -> c
                .baseDir(BaseDir.find())
                .yaml("application.yml")
                .require("/application", ApplicationConfig.class)
                .require("/database", DatabaseConfig.class)
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