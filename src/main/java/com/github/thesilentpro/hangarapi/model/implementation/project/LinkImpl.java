package com.github.thesilentpro.hangarapi.model.implementation.project;

import com.github.thesilentpro.hangarapi.model.project.Link;

public class LinkImpl implements Link {

    private final int id;
    private final String name;
    private final String url;

    public LinkImpl(int id, String name, String url) {
        this.id = id;
        this.name = name;
        this.url = url;
    }

    @Override public int getId() { return id; }

    @Override public String getName() { return name; }

    @Override public String getUrl() { return url; }

}
