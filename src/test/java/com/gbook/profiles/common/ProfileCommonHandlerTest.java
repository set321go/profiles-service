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

import static org.junit.Assert.assertEquals;
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
        Identity identity = new Identity();
        ProfileCommon common = new ProfileCommon();
        when(service.find(identity)).thenReturn(common);

        HandlingResult result = RequestFixture.handle(handler, fixture -> {
            fixture.getRegistry().add(identity);
        });

        assertEquals(Status.OK, result.getStatus());
        assertEquals(common, result.getRegistry().get(ProfileCommon.class));
    }
}