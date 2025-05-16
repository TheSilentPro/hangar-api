package com.github.thesilentpro.hangarapi.request;

public interface PaginatedRequestBuilder<T> extends RequestBuilder<T> {

    PaginatedRequestBuilder<T> limit(int limit);

    PaginatedRequestBuilder<T> offset(int offset);

    int getLimit();

    int getOffset();

}