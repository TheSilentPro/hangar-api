package com.github.thesilentpro.hangarapi.response.project;

import com.github.thesilentpro.hangarapi.model.project.stats.ProjectStats;
import com.github.thesilentpro.hangarapi.response.Response;

import java.util.Map;

public interface ProjectStatsResponse extends Response<String> {

    /**
     * Project stats. The map key is the date.
     * Example Date: 2025-01-20
     *
     * @return Project stats
     */
    Map<String, ProjectStats> getStats();

}
