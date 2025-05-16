package com.github.thesilentpro.hangarapi.response.user;

import com.github.thesilentpro.hangarapi.model.user.UserProject;
import com.github.thesilentpro.hangarapi.response.PaginatedResponse;

import java.util.List;

public interface UserProjectsResponse extends PaginatedResponse<String> {

    List<UserProject> getProjects();

}
