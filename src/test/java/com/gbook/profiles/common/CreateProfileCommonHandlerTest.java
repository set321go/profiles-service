package com.gbook.profiles.common;

import com.gbook.profiles.identity.Identity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import ratpack.http.Status;
import ratpack.test.handling.HandlingResult;
import ratpack.test.handling.RequestFixture;

import java.util.UUID;

import static org.junit.Assert.assertEquals;

/**
 * Created with IntelliJ IDEA.
 * User: set321go
 * Date: 2016-01-17
 * Time: 17:34
 */
@RunWith(MockitoJUnitRunner.class)
public class CreateProfileCommonHandlerTest {
    @Mock
    CommonProfileDataService service;

    @InjectMocks
    CreateProfileCommonHandler handler;

    @Test
    public void createCommonProfileWithDefaults() throws Exception {
        Identity identity = new Identity(UUID.randomUUID());

        HandlingResult result = RequestFixture.handle(handler, fixture -> {
            fixture.getRegistry().add(identity);
            fixture.method("POST");
        });

        assertEquals(Status.OK, result.getStatus());
    }

    @Test
    public void createCommonProfileWithInput() throws Exception {
        Identity identity = new Identity(UUID.randomUUID());

        HandlingResult result = RequestFixture.handle(handler, fixture -> {
            fixture.getRegistry().add(identity);
            fixture.method("POST");
            fixture.body("{\"name\":\"testName\"}", "application/json");
        });

        assertEquals(Status.OK, result.getStatus());
    }
}