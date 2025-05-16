package com.github.thesilentpro.hangarapi.gson;

import com.google.gson.JsonDeserializer;

public interface JsonMapper<T> extends JsonDeserializer<T> {}