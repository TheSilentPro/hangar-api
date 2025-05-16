package com.github.thesilentpro.hangarapi.request.implementation;

import com.github.thesilentpro.hangarapi.client.Requester;
import com.github.thesilentpro.hangarapi.gson.GsonUtils;
import com.github.thesilentpro.hangarapi.request.ProjectUsersRequestBuilder;
import com.github.thesilentpro.hangarapi.request.RequestBuilder;
import com.github.thesilentpro.hangarapi.response.implementation.user.UsersResponseImpl;
import com.github.thesilentpro.hangarapi.response.user.UsersResponse;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;

public class ProjectUsersRequestBuilderImpl extends AbstractPaginatedRequestBuilder<UsersResponse> implements ProjectUsersRequestBuilder {

    private final String path;
    private String project;

    public ProjectUsersRequestBuilderImpl(Requester requester, String path) {
        super(requester);
        this.path = path;
    }

    @Override
    public ProjectUsersRequestBuilder project(@NotNull String project) {
        this.project = project;
        return this;
    }

    @Override
    public CompletableFuture<UsersResponse> execute() {
        return getRequester().sendRequest(getRequester().newRequest("projects/" + RequestBuilder.encode(project) + path, false).build(), response -> {
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
        });
    }

}