package com.github.thesilentpro.hangarapi.model.implementation.user;

import com.github.thesilentpro.hangarapi.model.user.NameHistory;
import com.github.thesilentpro.hangarapi.model.user.User;

import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class UserImpl implements User {

    private final String avatarUrl;
    private final Instant createdAt;
    private final int id;
    private final boolean isOrganization;
    private final boolean locked;
    private final String name;
    private final List<NameHistory> nameHistory;
    private final int projectCount;
    private final Set<Integer> roles;
    private final Map<String, String> socials;
    private final String tagline;

    public UserImpl(String avatarUrl, Instant createdAt, int id, boolean isOrganization, boolean locked,
                    String name, List<NameHistory> nameHistory, int projectCount,
                    Set<Integer> roles, Map<String, String> socials, String tagline) {
        this.avatarUrl = avatarUrl;
        this.createdAt = createdAt;
        this.id = id;
        this.isOrganization = isOrganization;
        this.locked = locked;
        this.name = name;
        this.nameHistory = nameHistory;
        this.projectCount = projectCount;
        this.roles = roles;
        this.socials = socials;
        this.tagline = tagline;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public int getId() {
        return id;
    }

    public boolean isOrganization() {
        return isOrganization;
    }

    public boolean isLocked() {
        return locked;
    }

    public String getName() {
        return name;
    }

    public List<NameHistory> getNameHistory() {
        return nameHistory;
    }

    public int getProjectCount() {
        return projectCount;
    }

    public Set<Integer> getRoles() {
        return roles;
    }

    public Map<String, String> getSocials() {
        return socials;
    }

    public String getTagline() {
        return tagline;
    }

}
