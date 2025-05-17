package com.github.thesilentpro.hangarapi.request.implementation;

import com.github.thesilentpro.hangarapi.client.Requester;
import com.github.thesilentpro.hangarapi.request.RequestBuilder;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractRequestBuilder<T> implements RequestBuilder<T> {

    private final Requester requester;
    private Map<String, String> params;

    public AbstractRequestBuilder(Requester requester) {
        this.requester = requester;
    }

    @Override
    public void withParam(String key, String value) {
        if (this.params == null) {
            this.params = new HashMap<>();
        }
        this.params.put(key, value);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }

    @Override
    public String buildQueryString(Map<String, String> params) {
        if (params == null) {
            return "";
        }
        if (params.isEmpty()) {
            return "";
        }

        StringBuilder sb = new StringBuilder("?");
        boolean first = true;

        for (Map.Entry<String, String> entry : params.entrySet()) {
            if (!first) {
                sb.append("&");
            } else {
                first = false;
            }
            sb.append(entry.getKey()).append("=").append(RequestBuilder.encode(entry.getValue()));
        }

        return sb.toString();
    }

    @Override
    public String buildParams(String base) {
        if (base != null) {
            return base + buildQueryString(getParams());
        } else {
            return buildQueryString(getParams());
        }
    }

    @Override
    public Requester getRequester() {
        return this.requester;
    }

}
