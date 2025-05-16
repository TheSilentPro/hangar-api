package com.github.thesilentpro.hangarapi.model.implementation.project.stats;

import com.github.thesilentpro.hangarapi.model.project.stats.ProjectStats;

public class ProjectStatsImpl implements ProjectStats {

    private final int downloads;
    private final int views;

    public ProjectStatsImpl(int downloads, int views) {
        this.downloads = downloads;
        this.views = views;
    }

    @Override
    public int getDownloads() {
        return this.downloads;
    }

    @Override
    public int getViews() {
        return this.views;
    }

}