package com.github.thesilentpro.hangarapi.model.implementation.user;

import com.github.thesilentpro.hangarapi.model.implementation.project.Category;
import com.github.thesilentpro.hangarapi.model.implementation.project.Visibility;
import com.github.thesilentpro.hangarapi.model.project.Namespace;
import com.github.thesilentpro.hangarapi.model.project.Stats;
import com.github.thesilentpro.hangarapi.model.user.UserProject;

import java.time.Instant;

public class UserProjectImpl implements UserProject {

    private final String avatarUrl;
    private final Category category;
    private final Instant createdAt;
    private final String description;
    private final int id;
    private final Instant lastUpdated;
    private final String name;
    private final Namespace namespace;
    private final Stats stats;
    private final Visibility visibility;

    public UserProjectImpl(
            String avatarUrl,
            Category category,
            Instant createdAt,
            String description,
            int id,
            Instant lastUpdated,
            String name,
            Namespace namespace,
            Stats stats,
            Visibility visibility
    ) {
        this.avatarUrl = avatarUrl;
        this.category = category;
        this.createdAt = createdAt;
        this.description = description;
        this.id = id;
        this.lastUpdated = lastUpdated;
        this.name = name;
        this.namespace = namespace;
        this.stats = stats;
        this.visibility = visibility;
    }

    @Override
    public String getAvatarUrl() {
        return avatarUrl;
    }

    @Override
    public Category getCategory() {
        return category;
    }

    @Override
    public Instant getCreatedAt() {
        return createdAt;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public Instant getLastUpdated() {
        return lastUpdated;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Namespace getNamespace() {
        return namespace;
    }

    @Override
    public Stats getStats() {
        return stats;
    }

    @Override
    public Visibility getVisibility() {
        return visibility;
    }

}