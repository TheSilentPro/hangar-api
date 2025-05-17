package com.github.thesilentpro.hangarapi.request;

import com.github.thesilentpro.hangarapi.client.Requester;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

public interface RequestBuilder<T> {

    Requester getRequester();

    CompletableFuture<T> execute();

    Map<String, String> getParams();

    void withParam(String key, String value);

    String buildQueryString(Map<String, String> params);

    String buildParams(String base);

    static String encode(String value) {
        return URLEncoder.encode(value, StandardCharsets.UTF_8);
    }

}