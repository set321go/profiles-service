package com.gbook.profiles.identity;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Optional;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

/**
 * Created with IntelliJ IDEA.
 * User: set321go
 * Date: 2016-01-03
 * Time: 21:53
 */
@RunWith(MockitoJUnitRunner.class)
public class IdentityServiceTest {
    public static final String IDENTITY_TOKEN = "anIdentityToken";
    public static final String BAD_IDENTITY_TOKEN = IDENTITY_TOKEN;

    @Mock
    IdentityLoader identityLoader;

    @InjectMocks
    IdentityService identityService;

    @Test
    public void givenValidIdentityTokenProvideIdentity() {
        Identity identity = new Identity(UUID.randomUUID());
        when(identityLoader.find(IDENTITY_TOKEN)).thenReturn(Optional.of(identity));

        Optional<Identity> result = identityService.isIdentifiable(IDENTITY_TOKEN);

        assertTrue(result.isPresent());
        assertEquals(identity, result.get());
    }

    @SuppressWarnings("unchecked")
    @Test
    public void givenInvalidIdentityTokenProvideEmpty() {
        when(identityLoader.find(BAD_IDENTITY_TOKEN)).thenThrow(IdentityNotFound.class);

        Optional<Identity> result = identityService.isIdentifiable(BAD_IDENTITY_TOKEN);

        assertFalse(result.isPresent());
    }

    @Test
    public void givenNullIdentityTokenProvideEmpty() {
        Optional<Identity> result = identityService.isIdentifiable(null);

        assertFalse(result.isPresent());
    }
}