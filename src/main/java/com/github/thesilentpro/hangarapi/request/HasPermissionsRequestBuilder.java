package com.github.thesilentpro.hangarapi.request;

import com.github.thesilentpro.hangarapi.response.implementation.permission.Permission;
import com.github.thesilentpro.hangarapi.response.permission.PermissionHasResponse;

public interface HasPermissionsRequestBuilder extends RequestBuilder<PermissionHasResponse> {

    HasPermissionsRequestBuilder fromProject(String project);

    HasPermissionsRequestBuilder fromOrganization(String organization);

    HasPermissionsRequestBuilder withPermissions(Permission... permissions);

}