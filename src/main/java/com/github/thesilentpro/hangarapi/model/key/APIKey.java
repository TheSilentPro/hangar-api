package com.github.thesilentpro.hangarapi.model.key;

import com.github.thesilentpro.hangarapi.response.implementation.permission.Permission;

import java.time.Instant;
import java.util.EnumSet;

public interface APIKey {

    Instant getCreatedAt();

    Instant getLastUsed();

    String getName();

    EnumSet<Permission> getPermissions();

    String getTokenIdentifier();

}
