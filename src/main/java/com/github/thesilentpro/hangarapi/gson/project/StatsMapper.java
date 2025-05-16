package com.github.thesilentpro.hangarapi.gson.project;

import com.github.thesilentpro.hangarapi.gson.JsonMapper;
import com.github.thesilentpro.hangarapi.model.implementation.project.StatsImpl;
import com.github.thesilentpro.hangarapi.model.project.Stats;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

public class StatsMapper implements JsonMapper<Stats> {

    @Override
    public Stats deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject main = json.getAsJsonObject();
        JsonElement downloads = main.get("downloads");
        JsonElement recentDownloads = main.get("recentDownloads");
        JsonElement recentViews = main.get("recentViews");
        JsonElement stars = main.get("stars");
        JsonElement views = main.get("views");
        JsonElement watchers = main.get("watchers");
        return new StatsImpl(
                !downloads.isJsonNull() ? downloads.getAsInt() : -1,
                !recentDownloads.isJsonNull() ? recentDownloads.getAsInt() : -1,
                !recentViews.isJsonNull() ? recentViews.getAsInt() : -1,
                !stars.isJsonNull() ? stars.getAsInt() : -1,
                !views.isJsonNull() ? views.getAsInt() : -1,
                !watchers.isJsonNull() ? watchers.getAsInt() : -1
        );
    }

}