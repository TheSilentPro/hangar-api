package com.github.thesilentpro.hangarapi.request;

import com.github.thesilentpro.hangarapi.response.user.UserProjectsResponse;
import org.jetbrains.annotations.NotNull;

public interface UserProjectsRequestBuilder extends PaginatedRequestBuilder<UserProjectsResponse> {

    UserProjectsRequestBuilder user(@NotNull String user);

}