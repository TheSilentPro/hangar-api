package com.github.thesilentpro.hangarapi.model.project;

import com.github.thesilentpro.hangarapi.model.implementation.project.Category;
import com.github.thesilentpro.hangarapi.model.implementation.project.Visibility;

import java.time.Instant;
import java.util.Map;
import java.util.Set;

/**
 * Represents a project from hangar. Typically retrieved via '/projects' endpoints.
 * Unlike {@link com.github.thesilentpro.hangarapi.model.user.UserProject}, this holds more information.
 */
public interface Project {

    String getAvatarUrl();

    Category getCategory();

    Instant getCreatedAt();

    String getDescription();

    int getId();

    Instant getLastUpdated();

    String getName();

    Namespace getNamespace();

    Stats getStats();

    Visibility getVisibility();

    String getMainPageContent();

    Set<String> getMemberNames();

    Settings getSettings();

    Map<String, Set<String>> getSupportedPlatforms();

    UserActions getUserActions();

}