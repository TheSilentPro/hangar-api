package com.github.thesilentpro.hangarapi.response.project;

import com.github.thesilentpro.hangarapi.model.project.Project;
import com.github.thesilentpro.hangarapi.response.PaginatedResponse;

import java.util.List;

public interface ProjectsResponse extends PaginatedResponse<String> {

    List<Project> getProjects();

}