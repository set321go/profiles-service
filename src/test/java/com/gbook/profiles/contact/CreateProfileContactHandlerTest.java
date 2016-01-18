package com.gbook.profiles.contact;

import com.gbook.profiles.contact.model.ProfileContacts;
import com.gbook.profiles.identity.Identity;
import com.gbook.profiles.model.Result;
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
import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created with IntelliJ IDEA.
 * User: set321go
 * Date: 2016-01-17
 * Time: 17:26
 */
@RunWith(MockitoJUnitRunner.class)
public class CreateProfileContactHandlerTest {
    @Mock
    ProfileContactService contactService;

    @InjectMocks
    CreateProfileContactHandler handler;

    @Test
    public void createProfileContactDefault() throws Exception {
        Identity identity = new Identity(UUID.randomUUID());

        HandlingResult result = RequestFixture.handle(handler, fixture -> {
            fixture.getRegistry().add(identity);
        });

        assertEquals(Status.OK.getCode(), result.getStatus().getCode());
    }

    @Test
    public void createProfileContactWIthInput() throws Exception {
        Identity identity = new Identity(UUID.randomUUID());
        String json = "{\"contacts\": [{\"guid\":\"guid\",\"type\":\"email\",\"value\": {\"contact\": \"a@a.com\"},\"defaultContact\": true}]}";

        when(contactService.create(isA(Identity.class), isA(ProfileContacts.class))).thenReturn(Result.success());

        HandlingResult result = RequestFixture.handle(handler, fixture -> {
            fixture.getRegistry().add(identity);
            fixture.body(json, "application/json");
            fixture.method("patch");
        });

        assertEquals(Status.OK.getCode(), result.getStatus().getCode());
        verify(contactService).create(isA(Identity.class), isA(ProfileContacts.class));
    }
}