package com.github.thesilentpro.hangarapi.model.implementation.user;

public enum UsersSortType {

    NAME("name"),
    CREATED_AT("createdAt"),
    PROJECT_COUNT("projectCount"),
    LOCKED("locked"),
    ORG("org"),
    ROLES("roles");

    private static final UsersSortType[] VALUES = values();

    private final String name;

    UsersSortType(String name) {
        this.name = name;
    }

    public String value() {
        return name;
    }

}