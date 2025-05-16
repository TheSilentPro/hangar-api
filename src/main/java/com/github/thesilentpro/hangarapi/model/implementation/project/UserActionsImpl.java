package com.github.thesilentpro.hangarapi.model.implementation.project;

import com.github.thesilentpro.hangarapi.model.project.UserActions;

public class UserActionsImpl implements UserActions {

    private final boolean flagged;
    private final boolean starred;
    private final boolean watching;

    public UserActionsImpl(boolean flagged, boolean starred, boolean watching) {
        this.flagged = flagged;
        this.starred = starred;
        this.watching = watching;
    }

    @Override public boolean isFlagged() { return flagged; }

    @Override public boolean isStarred() { return starred; }

    @Override public boolean isWatching() { return watching; }

}