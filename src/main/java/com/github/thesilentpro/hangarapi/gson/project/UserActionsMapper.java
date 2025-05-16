package com.github.thesilentpro.hangarapi.gson.project;

import com.github.thesilentpro.hangarapi.gson.JsonMapper;
import com.github.thesilentpro.hangarapi.model.implementation.project.UserActionsImpl;
import com.github.thesilentpro.hangarapi.model.project.UserActions;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

public class UserActionsMapper implements JsonMapper<UserActions> {

    @Override
    public UserActions deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject main = json.getAsJsonObject();
        JsonElement flagged = main.get("flagged");
        JsonElement starred = main.get("starred");
        JsonElement watching = main.get("watching");
        return new UserActionsImpl(
                !flagged.isJsonNull() && flagged.getAsBoolean(),
                !starred.isJsonNull() && starred.getAsBoolean(),
                !watching.isJsonNull() && watching.getAsBoolean()
        );
    }

}
