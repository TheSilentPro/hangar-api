package com.github.thesilentpro.hangarapi.model.implementation.project;

public enum Tag {

    ADDON("addon"),
    LIBRARY("library"),
    SUPPORTS_FOLIA("supportsFolia");

    public static final Tag[] VALUES = values();

    private final String name;

    Tag(String name) {
        this.name = name;
    }

    public String value() {
        return name;
    }

}