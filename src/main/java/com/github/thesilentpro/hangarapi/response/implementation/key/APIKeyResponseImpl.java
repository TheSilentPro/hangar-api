package com.github.thesilentpro.hangarapi.response.implementation.key;

import com.github.thesilentpro.hangarapi.model.key.APIKey;
import com.github.thesilentpro.hangarapi.response.implementation.ResponseImpl;
import com.github.thesilentpro.hangarapi.response.key.APIKeyResponse;

import java.net.http.HttpResponse;
import java.util.List;

public class APIKeyResponseImpl extends ResponseImpl<String> implements APIKeyResponse {

    private final List<APIKey> keys;

    public APIKeyResponseImpl(HttpResponse<String> response, List<APIKey> keys) {
        super(response);
        this.keys = keys;
    }

    @Override
    public List<APIKey> getKeys() {
        return this.keys;
    }

}