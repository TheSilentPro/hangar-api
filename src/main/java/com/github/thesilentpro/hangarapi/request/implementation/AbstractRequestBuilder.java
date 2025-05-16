package com.github.thesilentpro.hangarapi.request.implementation;

import com.github.thesilentpro.hangarapi.client.Requester;
import com.github.thesilentpro.hangarapi.request.RequestBuilder;

public abstract class AbstractRequestBuilder<T> implements RequestBuilder<T> {

    private final Requester requester;

    public AbstractRequestBuilder(Requester requester) {
        this.requester = requester;
    }

    @Override
    public Requester getRequester() {
        return this.requester;
    }

}
