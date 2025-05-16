package com.github.thesilentpro.hangarapi.response.implementation.permission;

import com.github.thesilentpro.hangarapi.response.implementation.ResponseImpl;
import com.github.thesilentpro.hangarapi.response.permission.PermissionHasResponse;

import java.net.http.HttpResponse;

public class PermissionHasResponseImpl extends ResponseImpl<String> implements PermissionHasResponse {

    private final boolean result;
    private final PermissionType type;

    public PermissionHasResponseImpl(HttpResponse<String> response, boolean result, PermissionType type) {
        super(response);
        this.result = result;
        this.type = type;
    }

    @Override
    public boolean getResult() {
        return this.result;
    }

    @Override
    public PermissionType getType() {
        return this.type;
    }

}