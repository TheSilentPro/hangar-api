package com.github.thesilentpro.hangarapi.response.implementation.auth;

import com.github.thesilentpro.hangarapi.response.auth.DecodedToken;
import com.google.gson.JsonObject;

import java.time.Instant;

public class DecodedTokenImpl implements DecodedToken {

    private final String issuer;
    private final Instant expirationTime;
    private final String subject;

    public DecodedTokenImpl(String issuer, Instant expirationTime, String subject) {
        this.issuer = issuer;
        this.expirationTime = expirationTime;
        this.subject = subject;
    }

    public static DecodedTokenImpl of(JsonObject obj) {
        return new DecodedTokenImpl(obj.get("iss").getAsString(), Instant.ofEpochSecond(obj.get("exp").getAsInt()), obj.get("sub").getAsString());
    }

    @Override
    public String getIssuer() {
        return this.issuer;
    }

    @Override
    public Instant getExpirationTime() {
        return this.expirationTime;
    }

    @Override
    public String getSubject() {
        return this.subject;
    }

}
