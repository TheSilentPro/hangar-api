package com.github.thesilentpro.hangarapi.model.implementation.project;

import com.github.thesilentpro.hangarapi.model.project.*;

import java.time.Instant;
import java.util.Map;
import java.util.Set;

public class ProjectImpl implements Project {

    private final String avatarUrl;
    private final Category category;
    private final Instant createdAt;
    private final String description;
    private final int id;
    private final Instant lastUpdated;
    private final String mainPageContent;
    private final Set<String> memberNames;
    private final String name;
    private final Namespace namespace;
    private final Settings settings;
    private final Stats stats;
    private final Map<String, Set<String>> supportedPlatforms;
    private final UserActions userActions;
    private final Visibility visibility;

    public ProjectImpl(
            String avatarUrl,
            Category category,
            Instant createdAt,
            String description,
            int id,
            Instant lastUpdated,
            String mainPageContent,
            Set<String> memberNames,
            String name,
            Namespace namespace,
            Settings settings,
            Stats stats,
            Map<String, Set<String>> supportedPlatforms,
            UserActions userActions,
            Visibility visibility
    ) {
        this.avatarUrl = avatarUrl;
        this.category = category;
        this.createdAt = createdAt;
        this.description = description;
        this.id = id;
        this.lastUpdated = lastUpdated;
        this.mainPageContent = mainPageContent;
        this.memberNames = memberNames;
        this.name = name;
        this.namespace = namespace;
        this.settings = settings;
        this.stats = stats;
        this.supportedPlatforms = supportedPlatforms;
        this.userActions = userActions;
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
    public String getMainPageContent() {
        return mainPageContent;
    }

    @Override
    public Set<String> getMemberNames() {
        return memberNames;
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
    public Settings getSettings() {
        return settings;
    }

    @Override
    public Stats getStats() {
        return stats;
    }

    @Override
    public Map<String, Set<String>> getSupportedPlatforms() {
        return supportedPlatforms;
    }

    @Override
    public UserActions getUserActions() {
        return userActions;
    }

    @Override
    public Visibility getVisibility() {
        return visibility;
    }

}