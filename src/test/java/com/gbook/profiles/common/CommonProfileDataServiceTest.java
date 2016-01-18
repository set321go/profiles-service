package com.gbook.profiles.common;

import com.gbook.profiles.model.Result;
import com.gbook.profiles.identity.Identity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Optional;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

/**
 * Created with IntelliJ IDEA.
 * User: set321go
 * Date: 2016-01-16
 * Time: 20:46
 */
@RunWith(MockitoJUnitRunner.class)
public class CommonProfileDataServiceTest {
    @Mock
    private CommonProfileDataLoader loader;

    @InjectMocks
    private CommonProfileDataService service;

    @Test
    public void findVaildIdentity() {
        Identity identity = new Identity(UUID.randomUUID());
        ProfileCommon common = new ProfileCommon("name");

        when(loader.findFor(identity)).thenReturn(Optional.of(common));

        ProfileCommon result = service.find(identity);

        assertEquals(common, result);
    }

    @Test
    public void noCommonFoundForIdentity() {
        Identity identity = new Identity(UUID.randomUUID());

        when(loader.findFor(identity)).thenReturn(Optional.empty());

        ProfileCommon result = service.find(identity);

        assertEquals(new ProfileCommon(""), result);
    }

    @Test
    public void updateVaildIdentity() {
        Identity identity = new Identity(UUID.randomUUID());
        ProfileCommon common = new ProfileCommon("name");

        Result result = service.update(identity, common);

        assertEquals(Result.success(), result);
    }

    @Test
    public void updateBadInput() {
        Identity identity = new Identity(UUID.randomUUID());
        ProfileCommon common = new ProfileCommon("");

        Result result = service.update(identity, common);

        assertEquals(Result.withClientCause("Unable to process 'name' must have a value"), result);
    }

    @Test
    public void updateLoaderError() {
        Identity identity = new Identity(UUID.randomUUID());
        ProfileCommon common = new ProfileCommon("name");

        doThrow(IllegalStateException.class).when(loader).updateFor(identity, common);

        Result result = service.update(identity, common);

        assertEquals(Result.withServerCause("There was an unexpected error processing your request."), result);
    }
}