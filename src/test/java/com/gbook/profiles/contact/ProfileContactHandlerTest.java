package com.gbook.profiles.contact;

import com.gbook.profiles.model.Result;
import com.gbook.profiles.contact.model.DefaultContactData;
import com.gbook.profiles.contact.model.ProfileContact;
import com.gbook.profiles.contact.model.ProfileContacts;
import com.gbook.profiles.identity.Identity;
import com.google.common.collect.Lists;
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
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created with IntelliJ IDEA.
 * User: set321go
 * Date: 2016-01-10
 * Time: 10:59
 */
@RunWith(MockitoJUnitRunner.class)
public class ProfileContactHandlerTest {
    @Mock
    private ProfileContactService service;

    @InjectMocks
    private ProfileContactHandler handler;

    @Test
    public void givenValidIdentityRetrieveCommonProfileData() throws Exception {
        Identity identity = new Identity(UUID.randomUUID());
        ProfileContact contact = new ProfileContact("guid", "email", new DefaultContactData("a@a.com"), true);
        when(service.findAll(identity)).thenReturn(Lists.newArrayList(contact));

        HandlingResult result = RequestFixture.handle(handler, fixture -> {
            fixture.getRegistry().add(identity);
        });

        assertEquals(Status.OK, result.getStatus());
        assertNotNull(result.getRegistry().get(ProfileContacts.class));
    }

    @Test
    public void givenValidContactsUpdateContacts() throws Exception {
        Identity identity = new Identity(UUID.randomUUID());
        String json = "{\"contacts\": [{\"guid\":\"guid\",\"type\":\"email\",\"value\": {\"contact\": \"a@a.com\"},\"defaultContact\": true}]}";

        when(service.updateContacts(isA(Identity.class), isA(ProfileContacts.class))).thenReturn(Result.success());

        HandlingResult result = RequestFixture.handle(handler, fixture -> {
            fixture.getRegistry().add(identity);
            fixture.body(json, "application/json");
            fixture.method("patch");
        });

        assertEquals(Status.OK.getCode(), result.getStatus().getCode());
        verify(service).updateContacts(isA(Identity.class), isA(ProfileContacts.class));
    }

    @Test
    public void givenInValidContactsDoNotUpdateContacts() throws Exception {
        Identity identity = new Identity(UUID.randomUUID());
        String json = "{\"contacts\":[}";

        HandlingResult result = RequestFixture.handle(handler, fixture -> {
            fixture.getRegistry().add(identity);
            fixture.method("patch");
            fixture.body(json, "application/json");
        });

        assertEquals(Status.of(400).getCode(), result.getStatus().getCode());
    }
}