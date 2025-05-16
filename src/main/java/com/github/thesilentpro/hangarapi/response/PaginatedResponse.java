package com.github.thesilentpro.hangarapi.response;

public interface PaginatedResponse<T> extends Response<T> {

    int getCount();

    int getLimit();

    int getOffset();

}