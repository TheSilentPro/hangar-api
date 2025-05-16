package com.github.thesilentpro.hangarapi.gson;

import com.github.thesilentpro.hangarapi.response.implementation.permission.Permission;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.EnumSet;

public class PermissionSetMapper implements JsonMapper<EnumSet<Permission>> {

    public static final Type TYPE = new TypeToken<EnumSet<Permission>>(){}.getType();

    @Override
    public EnumSet<Permission> deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        EnumSet<Permission> permissions = EnumSet.noneOf(Permission.class);
        for (JsonElement entry : jsonElement.getAsJsonArray()) {
            permissions.add(Permission.valueOf(entry.getAsString().toUpperCase()));
        }
        return permissions;
    }

}