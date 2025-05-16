package com.github.thesilentpro.hangarapi.model.implementation.project;

import com.github.thesilentpro.hangarapi.model.project.Link;
import com.github.thesilentpro.hangarapi.model.project.LinkGroup;

import java.util.List;

public class LinkGroupImpl implements LinkGroup {

    private final int id;
    private final List<Link> links;
    private final String title;
    private final String type;

    public LinkGroupImpl(int id, List<Link> links, String title, String type) {
        this.id = id;
        this.links = links;
        this.title = title;
        this.type = type;
    }

    @Override public int getId() { return id; }

    @Override public List<Link> getLinks() { return links; }

    @Override public String getTitle() { return title; }

    @Override public String getType() { return type; }

}
