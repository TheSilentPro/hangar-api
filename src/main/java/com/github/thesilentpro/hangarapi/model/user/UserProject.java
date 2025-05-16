package com.github.thesilentpro.hangarapi.model.user;

import com.github.thesilentpro.hangarapi.model.implementation.project.Category;
import com.github.thesilentpro.hangarapi.model.implementation.project.Visibility;
import com.github.thesilentpro.hangarapi.model.project.Namespace;
import com.github.thesilentpro.hangarapi.model.project.Stats;

import java.time.Instant;

/**
 * Represents a user project on hangar.
 * Unlike {@link com.github.thesilentpro.hangarapi.model.project.Project}, this holds less information.
 */
public interface UserProject {

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

}