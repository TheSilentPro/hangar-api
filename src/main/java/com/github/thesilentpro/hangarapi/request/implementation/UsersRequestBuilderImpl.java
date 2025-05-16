package com.github.thesilentpro.hangarapi.request.implementation;

import com.github.thesilentpro.hangarapi.client.Requester;
import com.github.thesilentpro.hangarapi.gson.GsonUtils;
import com.github.thesilentpro.hangarapi.model.implementation.user.UsersSortType;
import com.github.thesilentpro.hangarapi.request.RequestBuilder;
import com.github.thesilentpro.hangarapi.request.UsersRequestBuilder;
import com.github.thesilentpro.hangarapi.response.implementation.user.UsersResponseImpl;
import com.github.thesilentpro.hangarapi.response.user.UsersResponse;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

public class UsersRequestBuilderImpl extends AbstractPaginatedRequestBuilder<UsersResponse> implements UsersRequestBuilder {

    private final String path;
    private String query;
    private UsersSortType sortType;

    public UsersRequestBuilderImpl(Requester requester, String path) {
        super(requester);
        this.path = path;
    }

    @Override
    public UsersRequestBuilder query(String query) {
        this.query = query;
        return this;
    }

    @Override
    public UsersRequestBuilder sort(UsersSortType sortType) {
        this.sortType = sortType;
        return this;
    }

    @Override
    public CompletableFuture<UsersResponse> execute() {
        // Collect query parameters in a map
        Map<String, String> queryParams = new HashMap<>();
        if (getLimit() != -1) queryParams.put("limit", String.valueOf(getLimit()));
        if (getOffset() != -1) queryParams.put("offset", String.valueOf(getOffset()));
        if (query != null && !query.isBlank()) queryParams.put("query", query);
        if (sortType != null) queryParams.put("sort", sortType.value());

        // Build final endpoint with query string
        String endpoint = path + buildQueryString(queryParams);

        return getRequester().sendRequest(
                getRequester().newRequest(endpoint, false).build(),
                response -> {
                    JsonObject main = JsonParser.parseString(response.body()).getAsJsonObject();
                    JsonObject pagination = main.get("pagination").getAsJsonObject();
                    JsonArray array = main.get("result").getAsJsonArray();
                    return new UsersResponseImpl(
                            response,
                            pagination.get("count").getAsInt(),
                            pagination.get("limit").getAsInt(),
                            pagination.get("offset").getAsInt(),
                            GsonUtils.GSON.fromJson(array, GsonUtils.USERS_TYPE)
                    );
                }
        );
    }

    private String buildQueryString(Map<String, String> params) {
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

}
