package com.github.thesilentpro.hangarapi.model.implementation.project;

import org.jetbrains.annotations.Nullable;

public enum Visibility {

    PUBLIC,
    NEW,
    NEEDS_CHANGES,
    NEEDS_APPROVAL,
    SOFT_DELETE;

    public static final Visibility[] VALUES = Visibility.values();

    @Nullable
    public static Visibility fromString(String raw) {
        for (Visibility value : VALUES) {
            if (value.name().equalsIgnoreCase(raw)) {
                return value;
            }
        }
        return null;
    }

}