package com.github.thesilentpro.hangarapi.request.implementation;

import com.github.thesilentpro.hangarapi.client.Requester;
import com.github.thesilentpro.hangarapi.request.PaginatedRequestBuilder;

public abstract class AbstractPaginatedRequestBuilder<T> extends AbstractRequestBuilder<T> implements PaginatedRequestBuilder<T> {

    private int limit;
    private int offset;

    public AbstractPaginatedRequestBuilder(Requester requester) {
        super(requester);
        this.limit = -1;
        this.offset = -1;
    }

    @Override
    public PaginatedRequestBuilder<T> limit(int limit) {
        this.limit = limit;
        return this;
    }

    @Override
    public PaginatedRequestBuilder<T> offset(int offset) {
        this.offset = offset;
        return this;
    }

    @Override
    public int getLimit() {
        return limit;
    }

    @Override
    public int getOffset() {
        return offset;
    }

}