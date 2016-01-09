package com.gbook.profiles;

import com.gbook.profiles.identity.IdentityHandler;
import ratpack.guice.Guice;
import ratpack.handling.Chain;
import ratpack.server.BaseDir;
import ratpack.server.RatpackServer;

public class ProfilesApp {

  public static void main(String[] args) throws Exception {
    RatpackServer.start(s -> s
        .serverConfig(c -> c
                .baseDir(BaseDir.find())
                .yaml("application.yml")
        )
        .registry(Guice.registry(b -> b.module(ProfileModule.class)))
        .handlers(chain -> chain
            .all(IdentityHandler.class)
            .get("profile", ProfileReadHandler.class)
            .prefix("static", nested -> nested.fileSystem("assets/images", Chain::files)) // Bind the /static app path to the src/ratpack/assets/images dir
        )
    );
  }
}