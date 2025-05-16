package com.github.thesilentpro.hangarapi.request.implementation;

import com.github.thesilentpro.hangarapi.client.Requester;
import com.github.thesilentpro.hangarapi.gson.GsonUtils;
import com.github.thesilentpro.hangarapi.request.RequestBuilder;
import com.github.thesilentpro.hangarapi.request.UserProjectsRequestBuilder;
import com.github.thesilentpro.hangarapi.response.implementation.user.UserProjectsResponseImpl;
import com.github.thesilentpro.hangarapi.response.user.UserProjectsResponse;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;

public class UserProjectsRequestBuilderImpl extends AbstractPaginatedRequestBuilder<UserProjectsResponse> implements UserProjectsRequestBuilder {

    private final String path;
    private String user;

    public UserProjectsRequestBuilderImpl(Requester requester, String path) {
        super(requester);
        this.path = path;
    }

    @Override
    public UserProjectsRequestBuilder user(@NotNull String user) {
        this.user = user;
        return this;
    }

    @Override
    public CompletableFuture<UserProjectsResponse> execute() {
        if (this.user == null) {
            return CompletableFuture.failedFuture(new IllegalArgumentException("User must not be null!"));
        }
        return getRequester().sendRequest(getRequester().newRequest("users/" + RequestBuilder.encode(this.user) + path, false).build(), response -> {
            JsonObject main = JsonParser.parseString(response.body()).getAsJsonObject();
            JsonObject pagination = main.getAsJsonObject("pagination");
            JsonArray array = main.getAsJsonArray("result");
            return new UserProjectsResponseImpl(
                    response,
                    pagination.get("count").getAsInt(),
                    pagination.get("limit").getAsInt(),
                    pagination.get("offset").getAsInt(),
                    GsonUtils.GSON.fromJson(array, GsonUtils.USER_PROJECTS_TYPE)
            );
        });
    }

}