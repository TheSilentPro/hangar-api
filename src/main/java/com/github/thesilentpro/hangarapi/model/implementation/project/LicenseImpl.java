package com.github.thesilentpro.hangarapi.model.implementation.project;

import com.github.thesilentpro.hangarapi.model.project.License;
import org.jetbrains.annotations.Nullable;

public class LicenseImpl implements License {

    private final String name;
    private final String type;
    private final String url;

    public LicenseImpl(String name, String type, String url) {
        this.name = name;
        this.type = type;
        this.url = url;
    }

    @Override public String getName() { return name; }

    @Override
    public String getType() { return type; }

    @Override
    @Nullable
    public String getUrl() { return url; }

}
