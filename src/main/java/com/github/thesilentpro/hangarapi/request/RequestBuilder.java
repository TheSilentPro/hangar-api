package com.github.thesilentpro.hangarapi.request;

import com.github.thesilentpro.hangarapi.client.Requester;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.CompletableFuture;

public interface RequestBuilder<T> {

    Requester getRequester();

    CompletableFuture<T> execute();

    static String encode(String value) {
        return URLEncoder.encode(value, StandardCharsets.UTF_8);
    }

}