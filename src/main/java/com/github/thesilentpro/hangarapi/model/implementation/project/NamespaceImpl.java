package com.github.thesilentpro.hangarapi.model.implementation.project;

import com.github.thesilentpro.hangarapi.model.project.Namespace;

public class NamespaceImpl implements Namespace {

    private final String owner;
    private final String slug;

    public NamespaceImpl(String owner, String slug) {
        this.owner = owner;
        this.slug = slug;
    }

    @Override public String getOwner() { return owner; }

    @Override public String getSlug() { return slug; }

}
