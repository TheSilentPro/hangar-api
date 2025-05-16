package com.github.thesilentpro.hangarapi.gson.project;

import com.github.thesilentpro.hangarapi.gson.JsonMapper;
import com.github.thesilentpro.hangarapi.model.implementation.project.member.ProjectMemberRoleImpl;
import com.github.thesilentpro.hangarapi.model.implementation.project.member.RoleColor;
import com.github.thesilentpro.hangarapi.model.project.member.ProjectMemberRole;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

public class ProjectMemberRoleMapper implements JsonMapper<ProjectMemberRole> {

    @Override
    public ProjectMemberRole deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject main = json.getAsJsonObject();
        return new ProjectMemberRoleImpl(main.get("title").getAsString(), RoleColor.fromString(main.get("color").getAsString()), main.get("rank").getAsInt(), main.get("category").getAsString());
    }

}