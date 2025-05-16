package com.github.thesilentpro.hangarapi.response.implementation.project;

import com.github.thesilentpro.hangarapi.model.project.Project;
import com.github.thesilentpro.hangarapi.response.PaginatedResponseImpl;
import com.github.thesilentpro.hangarapi.response.project.ProjectsResponse;

import java.net.http.HttpResponse;
import java.util.List;

public class ProjectsResponseImpl extends PaginatedResponseImpl<String> implements ProjectsResponse {

    private final List<Project> projects;

    public ProjectsResponseImpl(HttpResponse<String> response, int count, int limit, int offset, List<Project> projects) {
        super(response, count, limit, offset);
        this.projects = projects;
    }

    @Override
    public List<Project> getProjects() {
        return this.projects;
    }

}
