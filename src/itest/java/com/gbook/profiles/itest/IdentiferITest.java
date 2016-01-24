package com.gbook.profiles.itest;

import com.gbook.profiles.ProfileModule;
import com.gbook.profiles.identity.IdentityHandler;
import org.junit.Test;
import ratpack.guice.Guice;
import ratpack.http.Status;
import ratpack.http.client.ReceivedResponse;
import ratpack.test.embed.EmbeddedApp;

import static org.junit.Assert.assertEquals;

/**
 * Created with IntelliJ IDEA.
 * User: set321go
 * Date: 2016-01-23
 * Time: 21:00
 */
public class IdentiferITest {

    @Test
    public void givenExpectedRequestWithHeaderNoErrorsOccur() throws Exception {
        setupIdentityHandlingApp()
        .test(aTestHttpClient -> {
            aTestHttpClient.requestSpec(aRequestSpec -> aRequestSpec.getHeaders().add("Client-Identity", "testToken"));
            ReceivedResponse receivedResponse = aTestHttpClient.get("/profile");
            assertEquals(Status.OK.getCode(), receivedResponse.getStatus().getCode());
        });
    }

    @Test
    public void givenInvalidIdenityTokenShouldRecieveUnauthorized() throws Exception {
        setupIdentityHandlingApp()
        .test(aTestHttpClient -> {
            aTestHttpClient.requestSpec(aRequestSpec -> aRequestSpec.getHeaders().add("Client-Identity", "badToken"));
            ReceivedResponse receivedResponse = aTestHttpClient.get("/profile");
            assertEquals(Status.of(401).getCode(), receivedResponse.getStatus().getCode());
            assertEquals("Unknown Identity", receivedResponse.getBody().getText());
        });
    }

    @Test
    public void givenIdenityTokenMissingShouldRecieveUnauthorized() throws Exception {
        setupIdentityHandlingApp()
        .test(aTestHttpClient -> {
            ReceivedResponse receivedResponse = aTestHttpClient.get("/profile");
            assertEquals(Status.of(401).getCode(), receivedResponse.getStatus().getCode());
            assertEquals("Unknown Identity", receivedResponse.getBody().getText());
        });
    }

    private EmbeddedApp setupIdentityHandlingApp() throws Exception {
        return EmbeddedApp.of(s -> s.registry(Guice.registry(aBindingsSpec -> aBindingsSpec.module(ProfileModule.class)))
            .handlers(aChain ->
                    aChain.all(IdentityHandler.class)
                    .all(ctx -> ctx.getResponse().send()) //The identity handler only completes a request in error.
            ));
    }
}
