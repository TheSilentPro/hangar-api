package com.github.thesilentpro.hangarapi.response.permission;

import com.github.thesilentpro.hangarapi.response.Response;
import com.github.thesilentpro.hangarapi.response.implementation.permission.Permission;
import com.github.thesilentpro.hangarapi.response.implementation.permission.PermissionType;

import java.util.EnumSet;

public interface PermissionResponse extends Response<String> {

    String getPermissionBinString();

    EnumSet<Permission> getPermissions();

    PermissionType getType();

}
