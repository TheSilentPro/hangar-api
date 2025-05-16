package com.github.thesilentpro.hangarapi.request.implementation;

import com.github.thesilentpro.hangarapi.client.Requester;
import com.github.thesilentpro.hangarapi.request.HasAnyPermissionsRequestBuilder;
import com.github.thesilentpro.hangarapi.response.implementation.permission.Permission;
import com.github.thesilentpro.hangarapi.response.implementation.permission.PermissionHasResponseImpl;
import com.github.thesilentpro.hangarapi.response.implementation.permission.PermissionType;
import com.github.thesilentpro.hangarapi.response.permission.PermissionHasResponse;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.EnumSet;
import java.util.concurrent.CompletableFuture;

public class HasAnyPermissionsRequestBuilderImpl extends AbstractRequestBuilder<PermissionHasResponse> implements HasAnyPermissionsRequestBuilder {

    private String project;
    private String org;
    private final EnumSet<Permission> permissions;

    public HasAnyPermissionsRequestBuilderImpl(@NotNull Requester requester) {
        super(requester);
        this.permissions = EnumSet.noneOf(Permission.class);
    }

    @Override
    public HasAnyPermissionsRequestBuilder fromProject(@NotNull String project) {
        this.project = project;
        return this;
    }

    @Override
    public HasAnyPermissionsRequestBuilder fromOrganization(@NotNull String organization) {
        this.org = organization;
        return this;
    }

    @Override
    public HasAnyPermissionsRequestBuilder withPermissions(@NotNull Permission... permissions) {
        this.permissions.addAll(Arrays.asList(permissions));
        return this;
    }

    @Override
    public CompletableFuture<PermissionHasResponse> execute() {
        if (this.project != null) {
            return getRequester().sendRequest(getRequester().newRequest("permissions/hasAny?project=" + project + "&" + getRequester().asQueryRepeating("permissions", permissions.toArray(new Permission[0])), false).build(), response -> {
                JsonObject main = JsonParser.parseString(response.body()).getAsJsonObject();
                return new PermissionHasResponseImpl(response, main.get("result").getAsBoolean(), PermissionType.valueOf(main.get("type").getAsString().toUpperCase()));
            });
        } else if (this.org != null) {
            return getRequester().sendRequest(getRequester().newRequest("permissions/hasAny?organization=" + org + "&" + getRequester().asQueryRepeating("permissions", permissions.toArray(new Permission[0])), false).build(), response -> {
                JsonObject main = JsonParser.parseString(response.body()).getAsJsonObject();
                return new PermissionHasResponseImpl(response, main.get("result").getAsBoolean(), PermissionType.valueOf(main.get("type").getAsString().toUpperCase()));
            });
        } else {
            return CompletableFuture.failedFuture(new IllegalArgumentException("Neither PROJECT nor ORGANIZATION were set!"));
        }
    }
}