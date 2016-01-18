package com.gbook.profiles.common;

import com.gbook.profiles.model.Result;
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
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.when;

/**
 * Created with IntelliJ IDEA.
 * User: set321go
 * Date: 2016-01-04
 * Time: 19:58
 */
@RunWith(MockitoJUnitRunner.class)
public class ProfileCommonHandlerTest {
    @Mock
    private CommonProfileDataService service;

    @InjectMocks
    private ProfileCommonHandler handler;

    @Test
    public void givenValidIdentityRetrieveCommonProfileData() throws Exception {
        Identity identity = new Identity(UUID.randomUUID());
        ProfileCommon common = new ProfileCommon();
        when(service.find(identity)).thenReturn(common);

        HandlingResult result = RequestFixture.handle(handler, fixture -> {
            fixture.getRegistry().add(identity);
            fixture.method("GET");
        });

        assertEquals(Status.OK, result.getStatus());
        assertEquals(common, result.getRegistry().get(ProfileCommon.class));
    }

    @Test
    public void givenValidIdentityUpdateCommonProfileData() throws Exception {
        Identity identity = new Identity(UUID.randomUUID());
        when(service.update(isA(Identity.class), isA(ProfileCommon.class))).thenReturn(Result.success());

        HandlingResult result = RequestFixture.handle(handler, fixture -> {
            fixture.getRegistry().add(identity);
            fixture.method("PATCH");
            fixture.body("{\"name\":\"testName\"}", "application/json");
        });

        assertEquals(Status.of(200).getCode(), result.getStatus().getCode());
        assertNull(result.getBodyText());
    }

    @Test
    public void givenValidIdentityWithUnparsableCommonProfileData() throws Exception {
        Identity identity = new Identity(UUID.randomUUID());
        when(service.update(isA(Identity.class), isA(ProfileCommon.class))).thenReturn(Result.withClientCause("bad data"));

        HandlingResult result = RequestFixture.handle(handler, fixture -> {
            fixture.getRegistry().add(identity);
            fixture.method("PATCH");
            fixture.body("{\"name\":\"\"}", "application/json");
        });

        assertEquals(Status.of(400).getCode(), result.getStatus().getCode());
        assertEquals("bad data", result.getBodyText());
    }
}