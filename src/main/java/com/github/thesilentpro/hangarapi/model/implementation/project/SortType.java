package com.github.thesilentpro.hangarapi.model.implementation.project;

public enum SortType {

    VIEWS("views"),
    DOWNLOADS("downloads"),
    NEWEST("newest"),
    STARS("stars"),
    UPDATED("updated"),
    RECENT_DOWNLOADS("recent_downloads"),
    RECENT_VIEWS("recent_views"),
    SLUG("slug");

    public static final SortType[] VALUES = values();

    private final String name;

    SortType(String name) {
        this.name = name;
    }

    public String value() {
        return name;
    }

}
