package com.github.thesilentpro.hangarapi.model.implementation.project;

import com.github.thesilentpro.hangarapi.model.project.License;
import com.github.thesilentpro.hangarapi.model.project.LinkGroup;
import com.github.thesilentpro.hangarapi.model.project.Settings;

import java.util.EnumSet;
import java.util.List;
import java.util.Set;

public class SettingsImpl implements Settings {

    private final Set<String> keywords;
    private final License license;
    private final List<LinkGroup> links;
    private final String sponsors;
    private final EnumSet<Tag> tags;

    public SettingsImpl(Set<String> keywords, License license, List<LinkGroup> links, String sponsors, EnumSet<Tag> tags) {
        this.keywords = keywords;
        this.license = license;
        this.links = links;
        this.sponsors = sponsors;
        this.tags = tags;
    }

    @Override public Set<String> getKeywords() { return keywords; }

    @Override public License getLicense() { return license; }

    @Override public List<LinkGroup> getLinks() { return links; }

    @Override public String getSponsors() { return sponsors; }

    @Override public EnumSet<Tag> getTags() { return tags; }

}
