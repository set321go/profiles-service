package com.gbook.profiles;

import com.gbook.profiles.common.ProfileCommon;
import org.junit.Before;
import org.junit.Test;
import ratpack.http.Status;
import ratpack.jackson.JsonRender;
import ratpack.test.handling.HandlingResult;
import ratpack.test.handling.RequestFixture;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

/**
 * Created with IntelliJ IDEA.
 * User: set321go
 * Date: 2016-01-10
 * Time: 16:21
 */
public class ProfileRendererTest {

    ProfileRenderer renderer;

    @Before
    public void setup() {
        renderer = new ProfileRenderer();
    }

    @Test
    public void givenProfilePartsRenderResponse() throws Exception {
        ProfileCommon common = new ProfileCommon("testData");

        HandlingResult result = RequestFixture.handle(renderer, fixture -> {
            fixture.getRegistry().add(common);
            fixture.method("GET");
        });

        assertEquals(Status.OK, result.getStatus());
        assertEquals(common.asMap(), result.rendered(JsonRender.class).getObject());
    }

    @Test
    public void givenPatchNoRenderingResponse() throws Exception {
        ProfileCommon common = new ProfileCommon("testData");

        HandlingResult result = RequestFixture.handle(renderer, fixture -> {
            fixture.getRegistry().add(common);
            fixture.method("PATCH");
        });

        assertEquals(Status.of(204).getCode(), result.getStatus().getCode());
        assertTrue(result.getBodyText().isEmpty());
    }

}