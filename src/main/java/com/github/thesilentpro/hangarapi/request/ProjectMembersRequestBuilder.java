package com.github.thesilentpro.hangarapi.request;

import com.github.thesilentpro.hangarapi.response.project.ProjectMembersResponse;
import org.jetbrains.annotations.NotNull;

public interface ProjectMembersRequestBuilder extends PaginatedRequestBuilder<ProjectMembersResponse> {

    ProjectMembersRequestBuilder project(@NotNull String project);

}