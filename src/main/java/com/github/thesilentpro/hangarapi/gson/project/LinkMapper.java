package com.github.thesilentpro.hangarapi.gson.project;

import com.github.thesilentpro.hangarapi.gson.JsonMapper;
import com.github.thesilentpro.hangarapi.model.implementation.project.LinkImpl;
import com.github.thesilentpro.hangarapi.model.project.Link;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

public class LinkMapper implements JsonMapper<Link> {

    @Override
    public Link deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject main = json.getAsJsonObject();
        JsonElement id = main.get("id");
        JsonElement name = main.get("name");
        JsonElement url = main.get("url");
        return new LinkImpl(
                !id.isJsonNull() ? id.getAsInt() : -1,
                !name.isJsonNull() ? name.getAsString() : null,
                !url.isJsonNull() ? url.getAsString() : null
        );
    }

}
