package com.github.thesilentpro.hangarapi.request;

import com.github.thesilentpro.hangarapi.response.implementation.permission.Permission;
import com.github.thesilentpro.hangarapi.response.permission.PermissionHasResponse;

public interface HasAnyPermissionsRequestBuilder extends RequestBuilder<PermissionHasResponse> {

    HasAnyPermissionsRequestBuilder fromProject(String project);

    HasAnyPermissionsRequestBuilder fromOrganization(String organization);

    HasAnyPermissionsRequestBuilder withPermissions(Permission... permissions);

}