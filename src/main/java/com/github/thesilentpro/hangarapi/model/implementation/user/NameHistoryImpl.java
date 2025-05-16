package com.github.thesilentpro.hangarapi.model.implementation.user;

import com.github.thesilentpro.hangarapi.model.user.NameHistory;

import java.time.Instant;

public class NameHistoryImpl implements NameHistory {

    private final Instant date;
    private final String newName;
    private final String oldName;

    public NameHistoryImpl(Instant date, String newName, String oldName) {
        this.date = date;
        this.newName = newName;
        this.oldName = oldName;
    }

    public Instant getDate() {
        return date;
    }

    public String getNewName() {
        return newName;
    }

    public String getOldName() {
        return oldName;
    }


}
