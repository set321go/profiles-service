package com.gbook.profiles;

import com.gbook.profiles.identity.IdentityHandler;
import ratpack.guice.Guice;
import ratpack.handling.Chain;
import ratpack.server.BaseDir;
import ratpack.server.RatpackServer;

public class ProfilesApp {

  public static void main(String[] args) throws Exception {
    RatpackServer.start(s -> s
        .serverConfig(c -> c.baseDir(BaseDir.find()))
        .registry(Guice.registry(b -> b.module(MyModule.class)))
        .handlers(chain -> chain
            .prefix("profile", nested -> { // Set up a nested routing block, which is delegated to `nestedHandler`
              nested.all(IdentityHandler.class);
              nested.get(ProfileReadHandler.class);
              nested.put(ProfileUpdateHandler.class);
            })
            .prefix("static", nested -> nested.fileSystem("assets/images", Chain::files)) // Bind the /static app path to the src/ratpack/assets/images dir
            .all(ctx -> ctx.render("root handler!"))
        )
    );
  }
}