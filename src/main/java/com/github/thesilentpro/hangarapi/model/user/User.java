package com.github.thesilentpro.hangarapi.model.user;

import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.Set;

public interface User {

    String getAvatarUrl();

    Instant getCreatedAt();

    int getId();

    boolean isOrganization();

    boolean isLocked();

    String getName();

    List<NameHistory> getNameHistory();

    int getProjectCount();

    Set<Integer> getRoles();

    Map<String, String> getSocials();

    String getTagline();

}