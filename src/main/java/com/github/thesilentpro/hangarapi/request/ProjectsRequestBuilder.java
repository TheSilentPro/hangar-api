package com.github.thesilentpro.hangarapi.request;

import com.github.thesilentpro.hangarapi.model.implementation.project.*;
import com.github.thesilentpro.hangarapi.response.project.ProjectsResponse;

public interface ProjectsRequestBuilder extends PaginatedRequestBuilder<ProjectsResponse> {

    ProjectsRequestBuilder query(String query);

    ProjectsRequestBuilder sort(SortType sort);

    ProjectsRequestBuilder category(Category category);

    ProjectsRequestBuilder platform(Platform platform);

    ProjectsRequestBuilder owner(String owner);

    ProjectsRequestBuilder license(LicenseType license);

    ProjectsRequestBuilder version(String version);

    ProjectsRequestBuilder tag(Tag... tags);

    ProjectsRequestBuilder member(String member);

}
