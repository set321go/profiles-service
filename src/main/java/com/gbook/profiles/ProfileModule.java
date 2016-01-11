package com.gbook.profiles;

import com.gbook.profiles.common.CommonProfileDataLoader;
import com.gbook.profiles.common.CommonProfileDataService;
import com.gbook.profiles.common.ProfileCommonHandler;
import com.gbook.profiles.common.impl.DummyProfileDataLoader;
import com.gbook.profiles.contact.ProfileContactDataLoader;
import com.gbook.profiles.contact.ProfileContactHandler;
import com.gbook.profiles.contact.ProfileContactService;
import com.gbook.profiles.identity.IdentityHandler;
import com.gbook.profiles.identity.IdentityLoader;
import com.gbook.profiles.identity.IdentityService;
import com.gbook.profiles.identity.impl.DummyIdentityLoader;
import com.google.inject.AbstractModule;
import com.google.inject.multibindings.Multibinder;
import ratpack.handling.Handler;

public class ProfileModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(IdentityHandler.class);
        bind(IdentityService.class);
        bind(IdentityLoader.class).to(DummyIdentityLoader.class);

        bind(ProfileHandler.class);
        bind(ProfileRenderer.class);

        Multibinder<Handler> profileParts = Multibinder.newSetBinder(binder(), Handler.class);
        profileParts.addBinding().to(ProfileCommonHandler.class);
        profileParts.addBinding().to(ProfileContactHandler.class);

        bind(CommonProfileDataService.class);
        bind(CommonProfileDataLoader.class).to(DummyProfileDataLoader.class);

        bind(ProfileContactService.class);
        bind(ProfileContactDataLoader.class).to(com.gbook.profiles.contact.impl.DummyProfileDataLoader.class);
    }
}
