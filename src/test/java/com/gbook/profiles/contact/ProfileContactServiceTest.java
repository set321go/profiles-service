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
import rx.Observable;

import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

/**
 * Created with IntelliJ IDEA.
 * User: set321go
 * Date: 2016-01-17
 * Time: 08:54
 */
@RunWith(MockitoJUnitRunner.class)
public class ProfileContactServiceTest {
    @Mock
    private ProfileContactDataLoader loader;

    @InjectMocks
    private ProfileContactService service;

    @Test
    public void updateWithValidInput() {
        Identity identity = new Identity(UUID.randomUUID());
        ProfileContact contact = new ProfileContact(1, "email", new DefaultContactData("a@a.com"), true);
        ProfileContacts contacts = new ProfileContacts(Lists.newArrayList(contact));

        when(loader.updateContactInfo(identity, contact)).thenReturn(Observable.just(Boolean.TRUE));

        Observable<Result> result = service.updateContacts(identity, contacts);

        assertEquals(Result.success(), result.toBlocking().first());
    }

    @Test
    public void updateFailsValidation() {
        Identity identity = new Identity(UUID.randomUUID());
        ProfileContact contact = new ProfileContact(1, "email", new DefaultContactData("a@"), true);
        ProfileContacts contacts = new ProfileContacts(Lists.newArrayList(contact));

        Observable<Result> result = service.updateContacts(identity, contacts);

        assertEquals(Result.withClientCause("Invalid Contacts"), result.toBlocking().first());
    }

    @Test
    public void updateFailsInternalUpdate() throws Exception {
        Identity identity = new Identity(UUID.randomUUID());
        ProfileContact contact = new ProfileContact(1, "email", new DefaultContactData("a@a.com"), true);
        ProfileContacts contacts = new ProfileContacts(Lists.newArrayList(contact));

        when(loader.updateContactInfo(identity, contact)).thenReturn(Observable.just(Boolean.FALSE));

        Observable<Result> result = service.updateContacts(identity, contacts);

        assertEquals(Result.withServerCause("There was an unexpected error processing your request."), result.toBlocking().first());
    }

    @Test
    public void createWithValidInput() {
        Identity identity = new Identity(UUID.randomUUID());
        ProfileContact contact = new ProfileContact(1, "email", new DefaultContactData("a@a.com"), true);
        ProfileContacts contacts = new ProfileContacts(Lists.newArrayList(contact));

        when(loader.create(identity, contact)).thenReturn(Observable.just(Boolean.TRUE));

        Observable<Result> result = service.create(identity, contacts);

        assertEquals(Result.success(), result.toBlocking().first());
    }

    @Test
    public void createFailsValidation() {
        Identity identity = new Identity(UUID.randomUUID());
        ProfileContact contact = new ProfileContact(1, "email", new DefaultContactData("a@"), true);
        ProfileContacts contacts = new ProfileContacts(Lists.newArrayList(contact));

        Observable<Result> result = service.create(identity, contacts);

        assertEquals(Result.withClientCause("Invalid Contacts"), result.toBlocking().first());
    }

    @Test
    public void createFailsInternalUpdate() throws Exception {
        Identity identity = new Identity(UUID.randomUUID());
        ProfileContact contact = new ProfileContact(1, "email", new DefaultContactData("a@a.com"), true);
        ProfileContacts contacts = new ProfileContacts(Lists.newArrayList(contact));

        when(loader.create(identity, contact)).thenReturn(Observable.just(Boolean.FALSE));

        Observable<Result> result = service.create(identity, contacts);

        assertEquals(Result.withServerCause("There was an unexpected error processing your request."), result.toBlocking().first());
    }
}