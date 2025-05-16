package com.github.thesilentpro.hangarapi.response;

import com.github.thesilentpro.hangarapi.response.implementation.ResponseImpl;

import java.net.http.HttpResponse;

public class PaginatedResponseImpl<T> extends ResponseImpl<T> implements PaginatedResponse<T> {

    private final int count;
    private final int limit;
    private final int offset;

    public PaginatedResponseImpl(HttpResponse<T> response, int count, int limit, int offset) {
        super(response);
        this.count = count;
        this.limit = limit;
        this.offset = offset;
    }

    @Override
    public int getCount() {
        return this.count;
    }

    @Override
    public int getLimit() {
        return this.limit;
    }

    @Override
    public int getOffset() {
        return this.offset;
    }

}