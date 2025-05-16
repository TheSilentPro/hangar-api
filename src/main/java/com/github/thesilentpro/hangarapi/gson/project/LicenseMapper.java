package com.github.thesilentpro.hangarapi.gson.project;

import com.github.thesilentpro.hangarapi.gson.JsonMapper;
import com.github.thesilentpro.hangarapi.model.implementation.project.LicenseImpl;
import com.github.thesilentpro.hangarapi.model.project.License;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

public class LicenseMapper implements JsonMapper<License> {

    @Override
    public License deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject obj = json.getAsJsonObject();
        JsonElement name = obj.get("name");
        JsonElement type = obj.get("type");
        JsonElement url = obj.get("url");
        return new LicenseImpl(!name.isJsonNull() ? name.getAsString() : null, !type.isJsonNull() ? type.getAsString() : null, !url.isJsonNull() ? url.getAsString() : null);
    }

}