package com.github.thesilentpro.hangarapi.client.builder;

import com.github.thesilentpro.hangarapi.auth.AuthProvider;
import com.github.thesilentpro.hangarapi.client.HangarClient;

import java.net.http.HttpClient;

public interface HangarClientBuilder {

    /**
     * The {@link HttpClient} that should be used by the {@link HangarClient}.
     *
     * @param client The http client.
     * @return Builder
     */
    HangarClientBuilder client(HttpClient client);

    /**
     * The {@link AuthProvider} that should be used by the {@link HangarClient}.
     *
     * @param authProvider The auth provider.
     * @return Builder
     */
    HangarClientBuilder auth(AuthProvider authProvider);

    /**
     * The user-agent used in requests to the hangar api.
     * <bold>Please set a meaningful User-Agent!</bold>
     *
     * @param userAgent The user agent.
     * @return Builder
     */
    HangarClientBuilder userAgent(String userAgent);

    /**
     * Builds the client instance.
     *
     * @return The built client.
     */
    HangarClient build();

}