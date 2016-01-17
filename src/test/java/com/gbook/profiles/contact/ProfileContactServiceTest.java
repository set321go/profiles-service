package com.gbook.profiles.contact;

import com.gbook.profiles.Result;
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

import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doThrow;

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
        ProfileContact contact = new ProfileContact("guid", "email", new DefaultContactData("a@a.com"), true);
        ProfileContacts contacts = new ProfileContacts(Lists.newArrayList(contact));

        Result result = service.updateContacts(identity, contacts);

        assertEquals(Result.success(), result);
    }

    @Test
    public void updateFailsValidation() {
        Identity identity = new Identity(UUID.randomUUID());
        ProfileContact contact = new ProfileContact("guid", "email", new DefaultContactData("a@"), true);
        ProfileContacts contacts = new ProfileContacts(Lists.newArrayList(contact));

        Result result = service.updateContacts(identity, contacts);

        assertEquals(Result.withClientCause("Invalid Contacts"), result);
    }

    @Test
    public void updateFailsInternalUpdate() throws Exception {
        Identity identity = new Identity(UUID.randomUUID());
        ProfileContact contact = new ProfileContact("guid", "email", new DefaultContactData("a@a.com"), true);
        ProfileContacts contacts = new ProfileContacts(Lists.newArrayList(contact));

        doThrow(Exception.class).when(loader).updateContactInfo(identity, contact);

        Result result = service.updateContacts(identity, contacts);

        assertEquals(Result.withServerCause(null), result);
    }

}