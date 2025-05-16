package com.github.thesilentpro.hangarapi.gson.project;

import com.github.thesilentpro.hangarapi.gson.GsonUtils;
import com.github.thesilentpro.hangarapi.gson.JsonMapper;
import com.github.thesilentpro.hangarapi.model.implementation.project.SettingsImpl;
import com.github.thesilentpro.hangarapi.model.implementation.project.Tag;
import com.github.thesilentpro.hangarapi.model.project.License;
import com.github.thesilentpro.hangarapi.model.project.LinkGroup;
import com.github.thesilentpro.hangarapi.model.project.Settings;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

public class SettingsMapper implements JsonMapper<Settings> {

    @Override
    public Settings deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject main = json.getAsJsonObject();
        JsonElement links = main.get("links");
        JsonElement keywords = main.get("keywords");
        JsonElement license = main.get("license");
        JsonElement sponsors = main.get("sponsors");

        List<LinkGroup> linkGroups = new ArrayList<>();
        if (!links.isJsonNull()) {
            for (JsonElement linkGroupEntry : links.getAsJsonArray()) {
                if (!linkGroupEntry.isJsonNull()) {
                    linkGroups.add(context.deserialize(linkGroupEntry, LinkGroup.class));
                }
            }
        }
        EnumSet<Tag> tags = EnumSet.noneOf(Tag.class);
        for (JsonElement tagEntry : main.get("tags").getAsJsonArray()) {
            for (Tag value : Tag.VALUES) {
                if (value.name().equalsIgnoreCase(tagEntry.getAsString())) {
                    tags.add(value);
                }
            }
        }
        return new SettingsImpl(
                !keywords.isJsonNull() ? context.deserialize(keywords.getAsJsonArray(), GsonUtils.SET_STRING_TYPE) : null,
                !license.isJsonNull() ? context.deserialize(license.getAsJsonObject(), License.class) : null,
                linkGroups,
                !sponsors.isJsonNull() ? sponsors.getAsString() : null,
                tags
        );
    }

}