package com.github.thesilentpro.hangarapi.model.user;

import java.time.Instant;

public interface NameHistory {

    Instant getDate();

    String getNewName();

    String getOldName();

}
