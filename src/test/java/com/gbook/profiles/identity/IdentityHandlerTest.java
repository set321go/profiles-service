package com.gbook.profiles.identity;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import ratpack.http.Status;
import ratpack.test.handling.HandlingResult;
import ratpack.test.handling.RequestFixture;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.when;

/**
 * Created with IntelliJ IDEA.
 * User: set321go
 * Date: 2016-01-03
 * Time: 18:35
 */
@RunWith(MockitoJUnitRunner.class)
public class IdentityHandlerTest {
    public static final String IDENTITY_TOKEN = "anIdentityToken";
    public static final String BAD_IDENTITY_TOKEN = IDENTITY_TOKEN;
    public static final String ANY_URI = "/any/uri";
    public static final int UNAUTHORIZED_STATUS = 401;

    @Mock
    IdentityService identityService;

    @InjectMocks
    IdentityHandler handler;

    @Test
    public void givenValidClientIdentityHeaderAValidIdentityIsAvailableInTheRegistry() throws Exception {
        Identity identity = new Identity();
        when(identityService.isIdentifiable(IDENTITY_TOKEN)).thenReturn(Optional.of(identity));

        HandlingResult result = RequestFixture.handle(handler, fixture -> {
            fixture.header("Client-Identity", IDENTITY_TOKEN).uri(ANY_URI);
        });

        assertEquals(Status.OK, result.getStatus());
        assertEquals(identity, result.getRegistry().get(Identity.class));
    }

    @Test
    public void givenInvalidClientIdentityHeaderUnautherizedResponse() throws Exception {
        when(identityService.isIdentifiable(BAD_IDENTITY_TOKEN)).thenReturn(Optional.empty());

        HandlingResult result = RequestFixture.handle(handler, fixture -> {
            fixture.header("Client-Identity", BAD_IDENTITY_TOKEN).uri(ANY_URI);
        });

        assertEquals(UNAUTHORIZED_STATUS, result.getStatus().getCode());
        assertFalse(result.getRegistry().maybeGet(Identity.class).isPresent());
    }

    @Test
    public void givenNoClientIdentityHeaderUnautherizedResponse() throws Exception {
        when(identityService.isIdentifiable(null)).thenReturn(Optional.empty());

        HandlingResult result = RequestFixture.handle(handler, fixture -> {
            fixture.uri(ANY_URI);
        });

        assertEquals(UNAUTHORIZED_STATUS, result.getStatus().getCode());
        assertFalse(result.getRegistry().maybeGet(Identity.class).isPresent());
    }
}