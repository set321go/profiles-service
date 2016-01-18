package com.gbook.profiles;

import com.gbook.profiles.model.ApplicationConfig;
import org.apache.commons.lang3.StringUtils;
import ratpack.handling.Context;
import ratpack.handling.Handler;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;
import java.util.Optional;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: set321go
 * Date: 2016-01-17
 * Time: 10:17
 */
@Singleton
public class NewProfileHandler implements Handler {
    private Set<Handler> profileConstructors;

    @Inject
    public NewProfileHandler(@Named("profileConstructors") Set<Handler> aProfileConstructors) {
        profileConstructors = aProfileConstructors;
    }

    @Override
    public void handle(Context ctx) throws Exception {
        ctx.getResponse().beforeSend(aResponse -> {
            if (aResponse.getStatus().getCode() < 299 && aResponse.getStatus().getCode() > 199) {
                Optional<ApplicationConfig> applicationConfig = ctx.maybeGet(ApplicationConfig.class);
                String locationString = "http://" + ctx.getRequest().getLocalAddress();
                if (applicationConfig.isPresent() && StringUtils.isNotBlank(applicationConfig.get().getLocation())) {
                    locationString = applicationConfig.get().getLocation();
                }
                aResponse.getHeaders().add("Location", locationString + "/profile");
            }
        });
        Handler [] handlers = profileConstructors.toArray(new Handler[profileConstructors.size() + 1]);
        handlers[handlers.length -1 ] = ctx.get(CreatedRenderer.class);
        ctx.insert(handlers);
    }
}
