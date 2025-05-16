package com.github.thesilentpro.hangarapi.auth;

import java.time.Instant;

/**
 * Represents an authentication provider for a {@link com.github.thesilentpro.hangarapi.client.HangarClient}.
 *
 * @see JWTAuthProvider
 */
public interface AuthProvider {

    AuthProvider setApiKey(String key);

    AuthProvider setToken(String token);

    AuthProvider setExpiration(Instant time);

    String getApiKey();

    String getToken();

    Instant getExpirationTime();

}