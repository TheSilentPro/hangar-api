package com.github.thesilentpro.hangarapi.auth;

import java.time.Instant;

public class JWTAuthProvider implements AuthProvider {

    private String apiKey;
    private String token;
    private Instant expiration;

    /**
     * An implementation of {@link AuthProvider} based on JWT Tokens.
     *
     * @param apiKey The api key.
     * @param token The jwt token.
     * @param expiration Expiration timestamp of the token.
     */
    public JWTAuthProvider(String apiKey, String token, Instant expiration) {
        this.apiKey = apiKey;
        this.token = token;
        this.expiration = expiration;
    }

    public JWTAuthProvider(String apiKey) {
        this.apiKey = apiKey;
    }

    @Override
    public JWTAuthProvider setApiKey(String apiKey) {
        this.apiKey = apiKey;
        return this;
    }

    @Override
    public JWTAuthProvider setToken(String token) {
        this.token = token;
        return this;
    }

    @Override
    public JWTAuthProvider setExpiration(Instant expiration) {
        this.expiration = expiration;
        return this;
    }

    @Override
    public String getApiKey() {
        return this.apiKey;
    }

    @Override
    public String getToken() {
        return this.token;
    }

    @Override
    public Instant getExpirationTime() {
        return this.expiration;
    }

}