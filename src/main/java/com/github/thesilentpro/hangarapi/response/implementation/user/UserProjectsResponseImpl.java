package com.github.thesilentpro.hangarapi.response.implementation.user;

import com.github.thesilentpro.hangarapi.model.user.UserProject;
import com.github.thesilentpro.hangarapi.response.PaginatedResponseImpl;
import com.github.thesilentpro.hangarapi.response.user.UserProjectsResponse;

import java.net.http.HttpResponse;
import java.util.List;

public class UserProjectsResponseImpl extends PaginatedResponseImpl<String> implements UserProjectsResponse {

    private final List<UserProject> projects;

    public UserProjectsResponseImpl(HttpResponse<String> response, int count, int limit, int offset, List<UserProject> projects) {
        super(response, count, limit, offset);
        this.projects = projects;
    }

    @Override
    public List<UserProject> getProjects() {
        return this.projects;
    }

}