package com.github.thesilentpro.hangarapi.gson;

import com.github.thesilentpro.hangarapi.model.implementation.key.APIKeyImpl;
import com.github.thesilentpro.hangarapi.model.key.APIKey;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class APIKeyMapper implements JsonMapper<List<APIKey>> {

    public static final Type TYPE = new TypeToken<List<APIKey>>(){}.getType();

    @Override
    public List<APIKey> deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        List<APIKey> keys = new ArrayList<>();
        JsonArray main = json.getAsJsonArray();
        for (JsonElement entry : main) {
            JsonObject obj = entry.getAsJsonObject();
            keys.add(new APIKeyImpl(Instant.parse(obj.get("createdAt").getAsString()), Instant.parse(obj.get("lastUsed").getAsString()), obj.get("name").getAsString(), context.deserialize(obj.get("permissions"), PermissionSetMapper.TYPE), obj.get("tokenIdentifier").getAsString()));
        }
        return keys;
    }

}
