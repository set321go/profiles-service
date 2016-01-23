package com.gbook.profiles.common;

import com.gbook.profiles.model.Result;
import com.gbook.profiles.identity.Identity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import rx.Observable;

import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.isA;
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

        when(loader.findFor(identity)).thenReturn(Observable.just(common));

        Observable<ProfileCommon> result = service.find(identity);

        assertEquals(common, result.toBlocking().first());
    }

    @Test
    public void noCommonFoundForIdentity() {
        Identity identity = new Identity(UUID.randomUUID());

        when(loader.findFor(identity)).thenReturn(Observable.empty());

        Observable<ProfileCommon> result = service.find(identity);

        assertEquals(new ProfileCommon(""), result.toBlocking().first());
    }

    @Test
    public void updateVaildIdentity() {
        Identity identity = new Identity(UUID.randomUUID());
        ProfileCommon common = new ProfileCommon("name");

        when(loader.updateFor(isA(Identity.class), isA(ProfileCommon.class))).thenReturn(Observable.just(Boolean.TRUE));

        Observable<Result> result = service.update(identity, common);

        assertEquals(Result.success(), result.toBlocking().first());
    }

    @Test
    public void updateBadInput() {
        Identity identity = new Identity(UUID.randomUUID());
        ProfileCommon common = new ProfileCommon("");

        Observable<Result> result = service.update(identity, common);

        assertEquals(Result.withClientCause("Unable to process 'name' must have a value"), result.toBlocking().first());
    }

    @Test
    public void updateLoaderError() {
        Identity identity = new Identity(UUID.randomUUID());
        ProfileCommon common = new ProfileCommon("name");

        when(loader.updateFor(isA(Identity.class), isA(ProfileCommon.class))).thenReturn(Observable.error(new IllegalStateException()));

        Observable<Result> result = service.update(identity, common);

        assertEquals(Result.withServerCause("There was an unexpected error processing your request."), result.toBlocking().first());
    }
}