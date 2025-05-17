package com.github.thesilentpro.hangarapi.request.implementation;

import com.github.thesilentpro.hangarapi.client.Requester;
import com.github.thesilentpro.hangarapi.gson.GsonUtils;
import com.github.thesilentpro.hangarapi.model.implementation.user.UsersSortType;
import com.github.thesilentpro.hangarapi.request.UsersRequestBuilder;
import com.github.thesilentpro.hangarapi.response.implementation.user.UsersResponseImpl;
import com.github.thesilentpro.hangarapi.response.user.UsersResponse;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

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
        if (getLimit() != -1) {
            withParam("limit", String.valueOf(getLimit()));
        }
        if (getOffset() != -1) {
            withParam("offset", String.valueOf(getOffset()));
        }
        if (query != null && !query.isBlank()) {
            withParam("query", query);
        }
        if (sortType != null) {
            withParam("sort", sortType.value());
        }

        String endpoint = buildParams(path);

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

}
