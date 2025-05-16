package com.github.thesilentpro.hangarapi.request;

import com.github.thesilentpro.hangarapi.response.permission.PermissionResponse;

public interface PermissionRequestBuilder extends RequestBuilder<PermissionResponse> {

    PermissionRequestBuilder fromProject(String project);

    PermissionRequestBuilder fromOrganization(String organization);

}
