package com.github.thesilentpro.hangarapi.request;

import com.github.thesilentpro.hangarapi.response.user.UsersResponse;
import org.jetbrains.annotations.NotNull;

public interface ProjectUsersRequestBuilder extends PaginatedRequestBuilder<UsersResponse> {

    ProjectUsersRequestBuilder project(@NotNull String project);

}
