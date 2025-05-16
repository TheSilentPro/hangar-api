package com.github.thesilentpro.hangarapi.response.permission;

import com.github.thesilentpro.hangarapi.response.Response;
import com.github.thesilentpro.hangarapi.response.implementation.permission.PermissionType;

public interface PermissionHasResponse extends Response<String> {

    boolean getResult();

    PermissionType getType();

}
