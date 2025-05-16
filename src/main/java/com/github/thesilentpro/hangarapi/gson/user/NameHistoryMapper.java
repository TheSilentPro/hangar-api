package com.github.thesilentpro.hangarapi.gson.user;

import com.github.thesilentpro.hangarapi.gson.JsonMapper;
import com.github.thesilentpro.hangarapi.model.implementation.user.NameHistoryImpl;
import com.github.thesilentpro.hangarapi.model.user.NameHistory;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.time.Instant;

public class NameHistoryMapper implements JsonMapper<NameHistory> {

    @Override
    public NameHistory deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject main = json.getAsJsonObject();
        return new NameHistoryImpl(Instant.parse(main.get("date").getAsString()), main.get("newName").getAsString(), main.get("oldName").getAsString());
    }

}