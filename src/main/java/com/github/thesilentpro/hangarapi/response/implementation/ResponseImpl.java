package com.github.thesilentpro.hangarapi.response.implementation;

import com.github.thesilentpro.hangarapi.response.Response;

import java.net.http.HttpResponse;

public class ResponseImpl<T> implements Response<T> {

    private final HttpResponse<T> response;

    public ResponseImpl(HttpResponse<T> response) {
        this.response = response;
    }

    @Override
    public HttpResponse<T> httpResponse() {
        return this.response;
    }

}
