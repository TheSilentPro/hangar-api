package com.github.thesilentpro.hangarapi.request.implementation;

import com.github.thesilentpro.hangarapi.client.Requester;
import com.github.thesilentpro.hangarapi.gson.GsonUtils;
import com.github.thesilentpro.hangarapi.model.implementation.project.*;
import com.github.thesilentpro.hangarapi.request.ProjectsRequestBuilder;
import com.github.thesilentpro.hangarapi.request.RequestBuilder;
import com.github.thesilentpro.hangarapi.response.implementation.project.ProjectsResponseImpl;
import com.github.thesilentpro.hangarapi.response.project.ProjectsResponse;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.Collections;
import java.util.EnumSet;
import java.util.concurrent.CompletableFuture;

public class ProjectsRequestBuilderImpl extends AbstractPaginatedRequestBuilder<ProjectsResponse> implements ProjectsRequestBuilder {

    private String query;
    private SortType sortType;
    private Category category;
    private Platform platform;
    private String owner;
    private LicenseType licenseType;
    private String version;
    private EnumSet<Tag> tags;
    private String member;

    public ProjectsRequestBuilderImpl(Requester requester) {
        super(requester);
    }

    @Override
    public ProjectsRequestBuilder query(String query) {
        this.query = query;
        return this;
    }

    @Override
    public ProjectsRequestBuilder sort(SortType sort) {
        this.sortType = sort;
        return this;
    }

    @Override
    public ProjectsRequestBuilder category(Category category) {
        this.category = category;
        return this;
    }

    @Override
    public ProjectsRequestBuilder platform(Platform platform) {
        this.platform = platform;
        return this;
    }

    @Override
    public ProjectsRequestBuilder owner(String owner) {
        this.owner = owner;
        return this;
    }

    @Override
    public ProjectsRequestBuilder license(LicenseType license) {
        this.licenseType = license;
        return this;
    }

    @Override
    public ProjectsRequestBuilder version(String version) {
        this.version = version;
        return this;
    }

    @Override
    public ProjectsRequestBuilder tag(Tag... tags) {
        switch (tags.length) {
            case 0:
                this.tags = EnumSet.noneOf(Tag.class);
                break;
            case 1:
                this.tags = EnumSet.of(tags[0]);
                break;
            default:
                this.tags = EnumSet.noneOf(Tag.class);
                Collections.addAll(this.tags, tags);
                break;
        }
        return this;
    }

    @Override
    public ProjectsRequestBuilder member(String member) {
        this.member = member;
        return this;
    }

    @Override
    public CompletableFuture<ProjectsResponse> execute() {
        String baseEndpoint = "projects";
        StringBuilder queryBuilder = new StringBuilder();

        boolean first = true;
        if (query != null && !query.isBlank()) {
            appendParam(queryBuilder, "query", query, first);
            first = false;
        }
        if (sortType != null) {
            appendParam(queryBuilder, "sort", sortType.value(), first);
            first = false;
        }
        if (category != null) {
            appendParam(queryBuilder, "category", category.value(), first);
            first = false;
        }
        if (platform != null) {
            appendParam(queryBuilder, "platform", platform.name(), first);
            first = false;
        }
        if (owner != null && !owner.isBlank()) {
            appendParam(queryBuilder, "owner", owner, first);
            first = false;
        }
        if (licenseType != null) {
            appendParam(queryBuilder, "license", licenseType.value(), first);
            first = false;
        }
        if (version != null && !version.isBlank()) {
            appendParam(queryBuilder, "version", version, first);
            first = false;
        }
        if (tags != null && !tags.isEmpty()) {
            StringBuilder tagBuilder = new StringBuilder();
            for (Tag tag : tags) {
                if (!tagBuilder.isEmpty()) {
                    tagBuilder.append(',');
                }
                tagBuilder.append(tag.value());
            }
            appendParam(queryBuilder, "tags", tagBuilder.toString(), first);
            first = false;
        }
        if (member != null && !member.isBlank()) {
            appendParam(queryBuilder, "member", member, first);
        }

        String endpoint = baseEndpoint + (!queryBuilder.isEmpty() ? queryBuilder.toString() : "");

        return getRequester().sendRequest(
                getRequester().newRequest(endpoint, false).build(),
                response -> {
                    JsonObject main = JsonParser.parseString(response.body()).getAsJsonObject();
                    JsonObject pagination = main.getAsJsonObject("pagination");
                    JsonArray array = main.getAsJsonArray("result");
                    return new ProjectsResponseImpl(
                            response,
                            pagination.get("count").getAsInt(),
                            pagination.get("limit").getAsInt(),
                            pagination.get("offset").getAsInt(),
                            GsonUtils.GSON.fromJson(array, GsonUtils.PROJECTS_TYPE)
                    );
                }
        );
    }

    private void appendParam(StringBuilder builder, String key, String value, boolean first) {
        builder.append(first ? '?' : '&')
                .append(key)
                .append('=')
                .append(RequestBuilder.encode(value));
    }

}