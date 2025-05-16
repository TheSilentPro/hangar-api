package com.github.thesilentpro.hangarapi.response.key;

import com.github.thesilentpro.hangarapi.model.key.APIKey;
import com.github.thesilentpro.hangarapi.response.Response;

import java.util.List;

public interface APIKeyResponse extends Response<String> {

    List<APIKey> getKeys();

}