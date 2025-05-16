package com.github.thesilentpro.hangarapi.model.project;

import org.jetbrains.annotations.Nullable;

public interface License {

    String getName();

    String getType();

    @Nullable
    String getUrl();

}