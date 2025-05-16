package com.github.thesilentpro.hangarapi.response.implementation.permission;

import com.github.thesilentpro.hangarapi.response.implementation.ResponseImpl;
import com.github.thesilentpro.hangarapi.response.permission.PermissionResponse;

import java.net.http.HttpResponse;
import java.util.EnumSet;

public class PermissionResponseImpl extends ResponseImpl<String> implements PermissionResponse {

    private final String permissionBinString;
    private final EnumSet<Permission> permissions;
    private final PermissionType permissionType;

    public PermissionResponseImpl(HttpResponse<String> response, String permissionBinString, EnumSet<Permission> permissions, PermissionType permissionType) {
        super(response);
        this.permissionBinString = permissionBinString;
        this.permissions = permissions;
        this.permissionType = permissionType;
    }

    @Override
    public String getPermissionBinString() {
        return this.permissionBinString;
    }

    @Override
    public EnumSet<Permission> getPermissions() {
        return this.permissions;
    }

    @Override
    public PermissionType getType() {
        return this.permissionType;
    }

}