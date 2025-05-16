package com.github.thesilentpro.hangarapi.model.project;

import java.util.List;

public interface LinkGroup {

    int getId();

    List<Link> getLinks();

    String getTitle();

    String getType();

}