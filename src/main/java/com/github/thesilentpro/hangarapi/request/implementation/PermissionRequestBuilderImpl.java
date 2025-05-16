package com.github.thesilentpro.hangarapi.request.implementation;

import com.github.thesilentpro.hangarapi.client.Requester;
import com.github.thesilentpro.hangarapi.gson.GsonUtils;
import com.github.thesilentpro.hangarapi.gson.PermissionSetMapper;
import com.github.thesilentpro.hangarapi.request.PermissionRequestBuilder;
import com.github.thesilentpro.hangarapi.response.implementation.permission.PermissionResponseImpl;
import com.github.thesilentpro.hangarapi.response.implementation.permission.PermissionType;
import com.github.thesilentpro.hangarapi.response.permission.PermissionResponse;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;

public class PermissionRequestBuilderImpl extends AbstractRequestBuilder<PermissionResponse> implements PermissionRequestBuilder {

    private String project;
    private String org;

    public PermissionRequestBuilderImpl(@NotNull Requester requester) {
        super(requester);
    }

    @Override
    public PermissionRequestBuilder fromProject(@NotNull String project) {
        this.project = project;
        return this;
    }

    @Override
    public PermissionRequestBuilder fromOrganization(@NotNull String organization) {
        this.org = organization;
        return this;
    }

    @Override
    public CompletableFuture<PermissionResponse> execute() {
        if (project != null) {
            return getRequester().sendRequest(getRequester().newRequest("permissions?project=" + project).build(), response -> {
                JsonObject main = JsonParser.parseString(response.body()).getAsJsonObject();
                return new PermissionResponseImpl(response, main.get("permissionBinString").getAsString(), GsonUtils.GSON.fromJson(main.get("permissions").getAsJsonArray(), PermissionSetMapper.TYPE), PermissionType.valueOf(main.get("type").getAsString().toUpperCase()));
            });
        } else if (org != null) {
            return getRequester().sendRequest(getRequester().newRequest("permissions?organization=" + org).build(), response -> {
                JsonObject main = JsonParser.parseString(response.body()).getAsJsonObject();
                return new PermissionResponseImpl(response, main.get("permissionBinString").getAsString(), GsonUtils.GSON.fromJson(main.get("permissions").getAsJsonArray(), PermissionSetMapper.TYPE), PermissionType.valueOf(main.get("type").getAsString().toUpperCase()));
            });
        } else {
            return CompletableFuture.failedFuture(new IllegalArgumentException("Neither PROJECT nor ORGANIZATION were set!"));
        }
    }

}