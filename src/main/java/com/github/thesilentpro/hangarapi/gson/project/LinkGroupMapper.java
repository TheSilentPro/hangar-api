package com.github.thesilentpro.hangarapi.gson.project;

import com.github.thesilentpro.hangarapi.gson.JsonMapper;
import com.github.thesilentpro.hangarapi.model.implementation.project.LinkGroupImpl;
import com.github.thesilentpro.hangarapi.model.project.Link;
import com.github.thesilentpro.hangarapi.model.project.LinkGroup;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class LinkGroupMapper implements JsonMapper<LinkGroup> {

    @Override
    public LinkGroup deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject main = json.getAsJsonObject();
        List<Link> links = new ArrayList<>();
        for (JsonElement entry : main.get("links").getAsJsonArray()) {
            links.add(context.deserialize(entry, Link.class));
        }
        JsonElement id = main.get("id");
        JsonElement title = main.get("title");
        JsonElement type = main.get("type");
        return new LinkGroupImpl(
                !id.isJsonNull() ? id.getAsInt() : -1,
                links,
                !title.isJsonNull() ? title.getAsString() : null,
                !type.isJsonNull() ? type.getAsString() : null
        );
    }

}