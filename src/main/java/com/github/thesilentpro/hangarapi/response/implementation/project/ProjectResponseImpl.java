package com.github.thesilentpro.hangarapi.response.implementation.project;

import com.github.thesilentpro.hangarapi.model.project.Project;
import com.github.thesilentpro.hangarapi.response.implementation.ResponseImpl;
import com.github.thesilentpro.hangarapi.response.project.ProjectResponse;

import java.net.http.HttpResponse;

public class ProjectResponseImpl extends ResponseImpl<String> implements ProjectResponse {

    private final Project project;

    public ProjectResponseImpl(HttpResponse<String> response, Project project) {
        super(response);
        this.project = project;
    }

    @Override
    public Project getProject() {
        return this.project;
    }

}