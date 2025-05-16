package com.github.thesilentpro.hangarapi.gson.project;

import com.github.thesilentpro.hangarapi.gson.JsonMapper;
import com.github.thesilentpro.hangarapi.model.implementation.project.stats.ProjectStatsImpl;
import com.github.thesilentpro.hangarapi.model.project.stats.ProjectStats;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

public class ProjectStatsMapper implements JsonMapper<ProjectStats> {

    @Override
    public ProjectStats deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject main = json.getAsJsonObject();
        return new ProjectStatsImpl(main.get("downloads").getAsInt(), main.get("views").getAsInt());
    }

}