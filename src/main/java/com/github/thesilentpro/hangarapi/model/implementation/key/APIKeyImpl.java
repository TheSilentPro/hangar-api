package com.github.thesilentpro.hangarapi.model.implementation.key;

import com.github.thesilentpro.hangarapi.model.key.APIKey;
import com.github.thesilentpro.hangarapi.response.implementation.permission.Permission;

import java.time.Instant;
import java.util.EnumSet;

public class APIKeyImpl implements APIKey {

    private final Instant createdAt;
    private final Instant lastUsed;
    private final String name;
    private final EnumSet<Permission> permissions;
    private final String tokenIdentifier;

    public APIKeyImpl(Instant createdAt, Instant lastUsed, String name, EnumSet<Permission> permissions, String tokenIdentifier) {
        this.createdAt = createdAt;
        this.lastUsed = lastUsed;
        this.name = name;
        this.permissions = permissions;
        this.tokenIdentifier = tokenIdentifier;
    }

    @Override
    public Instant getCreatedAt() {
        return this.createdAt;
    }

    @Override
    public Instant getLastUsed() {
        return this.lastUsed;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public EnumSet<Permission> getPermissions() {
        return this.permissions;
    }

    @Override
    public String getTokenIdentifier() {
        return this.tokenIdentifier;
    }
}
