package com.github.thesilentpro.hangarapi.request.implementation;

import com.github.thesilentpro.hangarapi.client.Requester;
import com.github.thesilentpro.hangarapi.request.HasPermissionsRequestBuilder;
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

public class HasPermissionsRequestBuilderImpl extends AbstractRequestBuilder<PermissionHasResponse> implements HasPermissionsRequestBuilder {

    private String project;
    private String org;
    private final EnumSet<Permission> permissions;

    public HasPermissionsRequestBuilderImpl(@NotNull Requester requester) {
        super(requester);
        this.permissions = EnumSet.noneOf(Permission.class);
    }

    @Override
    public HasPermissionsRequestBuilder fromProject(@NotNull String project) {
        this.project = project;
        return this;
    }

    @Override
    public HasPermissionsRequestBuilder fromOrganization(@NotNull String organization) {
        this.org = organization;
        return this;
    }

    @Override
    public HasPermissionsRequestBuilder withPermissions(@NotNull Permission... permissions) {
        this.permissions.addAll(Arrays.asList(permissions));
        return this;
    }

    @Override
    public CompletableFuture<PermissionHasResponse> execute() {
        if (this.project != null) {
            return getRequester().sendRequest(getRequester().newRequest("permissions/hasAll?project=" + project + "&" + getRequester().asQueryRepeating("permissions", permissions.toArray(new Permission[0])), false).build(), response -> {
                JsonObject main = JsonParser.parseString(response.body()).getAsJsonObject();
                return new PermissionHasResponseImpl(response, main.get("result").getAsBoolean(), PermissionType.valueOf(main.get("type").getAsString().toUpperCase()));
            });
        } else if (this.org != null) {
            return getRequester().sendRequest(getRequester().newRequest("permissions/hasAll?organization=" + org + "&" + getRequester().asQueryRepeating("permissions", permissions.toArray(new Permission[0])), false).build(), response -> {
                JsonObject main = JsonParser.parseString(response.body()).getAsJsonObject();
                return new PermissionHasResponseImpl(response, main.get("result").getAsBoolean(), PermissionType.valueOf(main.get("type").getAsString().toUpperCase()));
            });
        } else {
            return CompletableFuture.failedFuture(new IllegalArgumentException("Neither PROJECT nor ORGANIZATION were set!"));
        }
    }
}
