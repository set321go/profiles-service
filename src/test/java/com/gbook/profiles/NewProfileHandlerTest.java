package com.gbook.profiles;

import com.gbook.profiles.model.ApplicationConfig;
import com.google.common.collect.Sets;
import com.google.common.net.HostAndPort;
import org.junit.Before;
import org.junit.Test;
import ratpack.handling.Handler;
import ratpack.http.Status;
import ratpack.test.handling.HandlingResult;
import ratpack.test.handling.RequestFixture;

import java.util.Set;

import static org.junit.Assert.assertEquals;

/**
 * Created with IntelliJ IDEA.
 * User: set321go
 * Date: 2016-01-17
 * Time: 13:07
 */
public class NewProfileHandlerTest {
    private Set<Handler> handlers = Sets.newHashSet();

    private NewProfileHandler newProfileHandler;

    @Before
    public void setup() {
        newProfileHandler = new NewProfileHandler(handlers);
    }

    @Test
    public void createNewProfileDefaultLocationHandler() throws Exception {
        HandlingResult result = RequestFixture.handle(newProfileHandler, aRequestFixture -> {
            aRequestFixture.method("post");
            aRequestFixture.localAddress(HostAndPort.fromParts("localhost", 8080));
            aRequestFixture.getRegistry().add(new CreatedRenderer());
        });

        assertEquals(Status.of(201).getCode(), result.getStatus().getCode());
        assertEquals("http://localhost:8080/profile", result.getHeaders().get("Location"));
    }

    @Test
    public void createNewProfileConfiguredLocationHandler() throws Exception {
        HandlingResult result = RequestFixture.handle(newProfileHandler, aRequestFixture -> {
            aRequestFixture.method("post");
            aRequestFixture.localAddress(HostAndPort.fromParts("localhost", 8080));
            aRequestFixture.getRegistry().add(new CreatedRenderer());
            ApplicationConfig applicationConfig = new ApplicationConfig();
            applicationConfig.setLocation("http://mydomain");
            aRequestFixture.getRegistry().add(applicationConfig);
        });

        assertEquals(Status.of(201).getCode(), result.getStatus().getCode());
        assertEquals("http://mydomain/profile", result.getHeaders().get("Location"));
    }
}