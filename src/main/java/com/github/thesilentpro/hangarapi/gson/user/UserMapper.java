package com.github.thesilentpro.hangarapi.gson.user;

import com.github.thesilentpro.hangarapi.gson.GsonUtils;
import com.github.thesilentpro.hangarapi.gson.JsonMapper;
import com.github.thesilentpro.hangarapi.model.implementation.user.UserImpl;
import com.github.thesilentpro.hangarapi.model.user.NameHistory;
import com.github.thesilentpro.hangarapi.model.user.User;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class UserMapper implements JsonMapper<User> {

    @Override
    public User deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject main = json.getAsJsonObject();
        JsonElement nameHistory = main.get("nameHistory");
        JsonElement tagline = main.get("tagline");
        JsonElement roles = main.get("roles");
        JsonElement socials = main.get("socials");
        List<NameHistory> nameHistories = new ArrayList<>();
        if (!nameHistory.isJsonNull()) {
            for (JsonElement entry : nameHistory.getAsJsonArray()) {
                JsonObject obj = entry.getAsJsonObject();
                nameHistories.add(context.deserialize(obj, GsonUtils.NAME_HISTORY_TYPE));
            }
        }
        return new UserImpl(
                main.get("avatarUrl").getAsString(),
                Instant.parse(main.get("createdAt").getAsString()),
                main.get("id").getAsInt(),
                main.get("isOrganization").getAsBoolean(),
                main.get("locked").getAsBoolean(),
                main.get("name").getAsString(),
                nameHistories,
                main.get("projectCount").getAsInt(),
                !roles.isJsonNull() ? context.deserialize(roles, GsonUtils.SET_TYPE) : null,
                !socials.isJsonNull() ? context.deserialize(socials, GsonUtils.MAP_TYPE) : null,
                !tagline.isJsonNull() ? tagline.getAsString() : null
        );
    }

}
