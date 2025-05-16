package com.github.thesilentpro.hangarapi.gson.project;

import com.github.thesilentpro.hangarapi.gson.JsonMapper;
import com.github.thesilentpro.hangarapi.model.implementation.project.NamespaceImpl;
import com.github.thesilentpro.hangarapi.model.project.Namespace;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

public class NamespaceMapper implements JsonMapper<Namespace> {

    @Override
    public Namespace deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject main = json.getAsJsonObject();
        JsonElement owner = main.get("owner");
        JsonElement slug = main.get("slug");
        return new NamespaceImpl(
                !owner.isJsonNull() ? owner.getAsString() : null,
                !slug.isJsonNull() ? slug.getAsString() : null
        );
    }

}