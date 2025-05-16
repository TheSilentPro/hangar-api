package com.github.thesilentpro.hangarapi.response.page;

import com.github.thesilentpro.hangarapi.response.Response;

public interface PageResponse extends Response<String> {

    String getContent();

}