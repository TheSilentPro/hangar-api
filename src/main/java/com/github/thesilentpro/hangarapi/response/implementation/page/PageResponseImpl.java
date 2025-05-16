package com.github.thesilentpro.hangarapi.response.implementation.page;

import com.github.thesilentpro.hangarapi.response.implementation.ResponseImpl;
import com.github.thesilentpro.hangarapi.response.page.PageResponse;

import java.net.http.HttpResponse;

public class PageResponseImpl extends ResponseImpl<String> implements PageResponse {

    private final String content;

    public PageResponseImpl(HttpResponse<String> response, String content) {
        super(response);
        this.content = content;
    }

    @Override
    public String getContent() {
        return this.content;
    }

}