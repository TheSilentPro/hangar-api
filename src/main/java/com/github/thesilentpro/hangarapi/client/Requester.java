package com.github.thesilentpro.hangarapi.client;

import com.github.thesilentpro.hangarapi.auth.AuthProvider;
import com.github.thesilentpro.hangarapi.response.auth.AuthenticationResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;
import java.util.function.Function;

public class Requester {

    private static final Logger LOGGER = LoggerFactory.getLogger(Requester.class);

    private static final String BASE_URL = "https://hangar.papermc.io/api/v1/";

    private final HangarClient client;
    private final HttpClient httpClient;
    private final AuthProvider authProvider;
    private final String userAgent;

    public Requester(HangarClient client, HttpClient httpClient, AuthProvider authProvider, String userAgent) {
        this.client = client;
        this.httpClient = httpClient;
        this.authProvider = authProvider;
        this.userAgent = userAgent;
    }

    public HttpRequest.Builder newRequest(String endpoint, boolean validate) {
        HttpRequest.Builder builder = HttpRequest.newBuilder().uri(URI.create(BASE_URL + endpoint)).header("Accept", "application/json").header("User-Agent", this.userAgent);
        if (validate) {
            validateAuthentication();
        }

        if (authProvider != null && this.authProvider.getToken() != null) {
            LOGGER.trace("Using token to process request: {}", endpoint);
            builder.header("Authorization", "HangarAuth " + this.authProvider.getToken());
        }
        return builder;
    }

    public HttpRequest.Builder newRequest(String endpoint) {
        return newRequest(endpoint, true);
    }

    public <T> CompletableFuture<T> sendRequest(HttpRequest request, Function<HttpResponse<String>, T> parser) {
        return httpClient.sendAsync(request, HttpResponse.BodyHandlers.ofString()).thenApplyAsync(response -> {
            if (response.statusCode() < 200 || response.statusCode() >= 300) {
                throw new CompletionException(new RuntimeException("Abnormal response code: " + response.statusCode()));
            } else {
                LOGGER.trace("JSON Response: {}", response.body());
                return parser.apply(response);
            }
        }, httpClient.executor().orElseThrow());
    }

    private void validateAuthentication() {
        if (this.authProvider == null) {
            throw new NullPointerException("You have not set an auth provider!");
        }
        if (this.authProvider.getToken() == null || this.authProvider.getExpirationTime() == null || Instant.now().isAfter(this.authProvider.getExpirationTime())) {
            authenticate().join();
        } else {
            LOGGER.trace("JWT is valid, skipping...");
        }
    }

    protected CompletableFuture<AuthenticationResponse> authenticate() {
        return this.client.retrieveAuthentication().thenApplyAsync(response -> {
            this.authProvider.setToken(response.getToken());
            this.authProvider.setExpiration(response.decodedToken().getExpirationTime());
            LOGGER.debug("Generated new JWT token.");
            return response;
        }, httpClient.executor().orElseThrow());
    }

    public HangarClient getClient() {
        return client;
    }

    public HttpClient getHttpClient() {
        return httpClient;
    }

    public AuthProvider getAuthProvider() {
        return authProvider;
    }

    public String getUserAgent() {
        return userAgent;
    }

    @SuppressWarnings("unchecked")
    public <T> String asQueryRepeating(String prefix, T... t) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < t.length; i++) {
            if (i > 0) {
                builder.append("&");
            }
            builder.append(prefix).append("=").append(URLEncoder.encode(t[i].toString().toLowerCase(), StandardCharsets.UTF_8));
        }
        return builder.toString();
    }

}