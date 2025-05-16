package com.github.thesilentpro.hangarapi.gson.project;

import com.github.thesilentpro.hangarapi.gson.JsonMapper;
import com.github.thesilentpro.hangarapi.model.implementation.project.Category;
import com.github.thesilentpro.hangarapi.model.implementation.project.ProjectImpl;
import com.github.thesilentpro.hangarapi.model.implementation.project.Visibility;
import com.github.thesilentpro.hangarapi.model.project.*;
import com.google.gson.*;

import java.lang.reflect.Type;
import java.time.Instant;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ProjectMapper implements JsonMapper<Project> {

    @Override
    public Project deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject main = json.getAsJsonObject();
        JsonElement mainPage = main.get("mainPageContent");
        JsonElement mainSettings = main.get("settings");

        Category category = Category.fromString(main.get("category").getAsString());

        String avatarUrl = main.get("avatarUrl").getAsString();
        Instant createdAt = Instant.parse(main.get("createdAt").getAsString());
        String description = main.get("description").getAsString();
        int id = main.get("id").getAsInt();
        Instant lastUpdated = Instant.parse(main.get("lastUpdated").getAsString());
        String mainPageContent = !mainPage.isJsonNull() ? mainPage.getAsString() : null;

        Set<String> memberNames = new HashSet<>();
        if (!main.get("memberNames").isJsonNull()) {
            JsonArray memberNamesArray = main.getAsJsonArray("memberNames");
            for (JsonElement memberElement : memberNamesArray) {
                memberNames.add(memberElement.getAsString());
            }
        }

        String name = main.get("name").getAsString();
        Namespace namespace = context.deserialize(main.get("namespace"), Namespace.class);
        Settings settings = !mainSettings.isJsonNull() ? context.deserialize(mainSettings, Settings.class) : null;
        Stats stats = context.deserialize(main.get("stats"), Stats.class);

        Map<String, Set<String>> supportedPlatforms = context.deserialize(main.get("supportedPlatforms"), Map.class);

        UserActions userActions = context.deserialize(main.get("userActions"), UserActions.class);
        Visibility visibility = Visibility.fromString(main.get("visibility").getAsString());

        return new ProjectImpl(avatarUrl, category, createdAt, description, id, lastUpdated, mainPageContent, memberNames, name, namespace, settings, stats, supportedPlatforms, userActions, visibility);
    }

}