package com.github.thesilentpro.hangarapi.model.implementation.project;

import com.github.thesilentpro.hangarapi.model.project.Stats;

public class StatsImpl implements Stats {

    private final int downloads;
    private final int recentDownloads;
    private final int recentViews;
    private final int stars;
    private final int views;
    private final int watchers;

    public StatsImpl(int downloads, int recentDownloads, int recentViews, int stars, int views, int watchers) {
        this.downloads = downloads;
        this.recentDownloads = recentDownloads;
        this.recentViews = recentViews;
        this.stars = stars;
        this.views = views;
        this.watchers = watchers;
    }

    @Override public int getDownloads() { return downloads; }

    @Override public int getRecentDownloads() { return recentDownloads; }

    @Override public int getRecentViews() { return recentViews; }

    @Override public int getStars() { return stars; }

    @Override public int getViews() { return views; }

    @Override public int getWatchers() { return watchers; }

}
