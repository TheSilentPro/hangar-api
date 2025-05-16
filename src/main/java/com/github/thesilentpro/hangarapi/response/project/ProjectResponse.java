package com.github.thesilentpro.hangarapi.response.project;

import com.github.thesilentpro.hangarapi.model.project.Project;
import com.github.thesilentpro.hangarapi.response.Response;

public interface ProjectResponse extends Response<String> {

    Project getProject();

}