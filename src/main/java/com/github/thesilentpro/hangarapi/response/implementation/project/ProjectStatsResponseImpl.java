package com.github.thesilentpro.hangarapi.response.implementation.project;

import com.github.thesilentpro.hangarapi.model.project.stats.ProjectStats;
import com.github.thesilentpro.hangarapi.response.implementation.ResponseImpl;
import com.github.thesilentpro.hangarapi.response.project.ProjectStatsResponse;

import java.net.http.HttpResponse;
import java.util.Map;

public class ProjectStatsResponseImpl extends ResponseImpl<String> implements ProjectStatsResponse {

    private final Map<String, ProjectStats> map;

    public ProjectStatsResponseImpl(HttpResponse<String> response, Map<String, ProjectStats> map) {
        super(response);
        this.map = map;
    }

    @Override
    public Map<String, ProjectStats> getStats() {
        return this.map;
    }

}
