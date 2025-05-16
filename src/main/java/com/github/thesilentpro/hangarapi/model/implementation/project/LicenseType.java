package com.github.thesilentpro.hangarapi.model.implementation.project;

public enum LicenseType {

    UNSPECIFIED("Unspecified"),
    MIT("MIT"),
    APACHE_2("Apache 2.0"),
    GPL("GPL"),
    LGPL("LGPL"),
    AGPL("AGPL"),
    OTHER("Other");

    private final String name;

    LicenseType(String name) {
        this.name = name;
    }

    public String value() {
        return name;
    }

}
