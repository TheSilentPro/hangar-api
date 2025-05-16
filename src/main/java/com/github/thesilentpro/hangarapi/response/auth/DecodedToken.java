package com.github.thesilentpro.hangarapi.response.auth;

import java.time.Instant;

public interface DecodedToken {

    String getIssuer();

    Instant getExpirationTime();

    String getSubject();

}
