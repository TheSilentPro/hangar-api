package com.github.thesilentpro.hangarapi.gson.project;

import com.github.thesilentpro.hangarapi.gson.JsonMapper;
import com.github.thesilentpro.hangarapi.model.implementation.project.member.ProjectMemberImpl;
import com.github.thesilentpro.hangarapi.model.project.member.ProjectMember;
import com.github.thesilentpro.hangarapi.model.project.member.ProjectMemberRole;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ProjectMemberMapper implements JsonMapper<ProjectMember> {

    @Override
    public ProjectMember deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject main = json.getAsJsonObject();
        JsonElement rolesObj = main.get("roles");
        List<ProjectMemberRole> roles = new ArrayList<>();
        if (!rolesObj.isJsonNull()) {
            for (JsonElement entry : rolesObj.getAsJsonArray()) {
                JsonObject obj = entry.getAsJsonObject();
                roles.add(context.deserialize(obj, ProjectMemberRole.class));
            }
        }
        return new ProjectMemberImpl(main.get("user").getAsString(), main.get("userId").getAsInt(), roles);
    }

}