package com.github.thesilentpro.hangarapi.request.implementation;

import com.github.thesilentpro.hangarapi.client.Requester;
import com.github.thesilentpro.hangarapi.gson.GsonUtils;
import com.github.thesilentpro.hangarapi.request.ProjectMembersRequestBuilder;
import com.github.thesilentpro.hangarapi.request.RequestBuilder;
import com.github.thesilentpro.hangarapi.response.implementation.project.ProjectMembersResponseImpl;
import com.github.thesilentpro.hangarapi.response.project.ProjectMembersResponse;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;

public class ProjectMembersRequestBuilderImpl extends AbstractPaginatedRequestBuilder<ProjectMembersResponse> implements ProjectMembersRequestBuilder {

    private String project;

    public ProjectMembersRequestBuilderImpl(Requester requester) {
        super(requester);
    }

    @Override
    public ProjectMembersRequestBuilder project(@NotNull String project) {
        this.project = project;
        return this;
    }

    @Override
    public CompletableFuture<ProjectMembersResponse> execute() {
        if (getLimit() != -1) {
            withParam("limit", String.valueOf(getLimit()));
        }
        if (getOffset() != -1) {
            withParam("offset", String.valueOf(getOffset()));
        }

        return getRequester().sendRequest(getRequester().newRequest("projects/" + RequestBuilder.encode(project) + "/members" + buildParams(null), false).build(), response -> {
            JsonObject main = JsonParser.parseString(response.body()).getAsJsonObject();
            JsonObject pagination = main.get("pagination").getAsJsonObject();
            JsonArray array = main.get("result").getAsJsonArray();
            return new ProjectMembersResponseImpl(
                    response,
                    pagination.get("count").getAsInt(),
                    pagination.get("limit").getAsInt(),
                    pagination.get("offset").getAsInt(),
                    GsonUtils.GSON.fromJson(array, GsonUtils.PROJECT_MEMBERS_TYPE)
            );
        });
    }

}