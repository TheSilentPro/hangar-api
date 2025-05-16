package com.github.thesilentpro.hangarapi.response.auth;

import com.github.thesilentpro.hangarapi.response.Response;

public interface AuthenticationResponse extends Response<String> {

    DecodedToken decodedToken();

    String getToken();

    int getExpiresIn();

}