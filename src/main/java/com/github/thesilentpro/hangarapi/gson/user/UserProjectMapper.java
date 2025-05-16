package com.github.thesilentpro.hangarapi.gson.user;

import com.github.thesilentpro.hangarapi.gson.JsonMapper;
import com.github.thesilentpro.hangarapi.model.implementation.project.Category;
import com.github.thesilentpro.hangarapi.model.implementation.project.Visibility;
import com.github.thesilentpro.hangarapi.model.implementation.user.UserProjectImpl;
import com.github.thesilentpro.hangarapi.model.project.Namespace;
import com.github.thesilentpro.hangarapi.model.project.Stats;
import com.github.thesilentpro.hangarapi.model.user.UserProject;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.time.Instant;

public class UserProjectMapper implements JsonMapper<UserProject> {

    @Override
    public UserProject deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject main = json.getAsJsonObject();
        Category category = Category.fromString(main.get("category").getAsString());
        String avatarUrl = main.get("avatarUrl").getAsString();
        Instant createdAt = Instant.parse(main.get("createdAt").getAsString());
        String description = main.get("description").getAsString();
        int id = main.get("id").getAsInt();
        Instant lastUpdated = Instant.parse(main.get("lastUpdated").getAsString());
        String name = main.get("name").getAsString();
        Namespace namespace = context.deserialize(main.get("namespace"), Namespace.class);
        Stats stats = context.deserialize(main.get("stats"), Stats.class);
        Visibility visibility = Visibility.fromString(main.get("visibility").getAsString());
        return new UserProjectImpl(avatarUrl, category, createdAt, description, id, lastUpdated, name, namespace, stats, visibility);
    }

}
