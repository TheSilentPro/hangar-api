package com.github.thesilentpro.hangarapi.response.implementation.auth;

import com.github.thesilentpro.hangarapi.response.auth.AuthenticationResponse;
import com.github.thesilentpro.hangarapi.response.auth.DecodedToken;
import com.github.thesilentpro.hangarapi.response.implementation.ResponseImpl;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.net.http.HttpResponse;
import java.util.Base64;
import java.util.regex.Pattern;

public class AuthenticationResponseImpl extends ResponseImpl<String> implements AuthenticationResponse {

    private final String token;
    private final int expiresIn;
    private DecodedToken decodedToken;

    public AuthenticationResponseImpl(HttpResponse<String> response, String token, int expiresIn) {
        super(response);
        this.token = token;
        this.expiresIn = expiresIn;
    }

    @Override
    public DecodedToken decodedToken() {
        if (decodedToken == null) {
            this.decodedToken = DecodedTokenImpl.of(TokenDecoder.decode(this.token));
        }
        return this.decodedToken;
    }

    @Override
    public String getToken() {
        return this.token;
    }

    @Override
    public int getExpiresIn() {
        return this.expiresIn;
    }

    private static final class TokenDecoder {

        private static final Pattern PATTERN = Pattern.compile("\\.");

        public static JsonObject decode(String token) {
            String[] chunks = PATTERN.split(token);
            Base64.Decoder decoder = Base64.getUrlDecoder();

            //String header = new String(decoder.decode(chunks[0]));
            String payload = new String(decoder.decode(chunks[1]));
            return JsonParser.parseString(payload).getAsJsonObject();
        }

    }

}