package com.github.thesilentpro.hangarapi.model.project;

import com.github.thesilentpro.hangarapi.model.implementation.project.Tag;

import java.util.EnumSet;
import java.util.List;
import java.util.Set;

public interface Settings {

    Set<String> getKeywords();

    License getLicense();

    List<LinkGroup> getLinks();

    String getSponsors();

    EnumSet<Tag> getTags();
}