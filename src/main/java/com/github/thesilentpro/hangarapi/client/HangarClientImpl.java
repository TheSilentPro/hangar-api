package com.github.thesilentpro.hangarapi.client;

import com.github.thesilentpro.hangarapi.auth.AuthProvider;
import com.github.thesilentpro.hangarapi.gson.APIKeyMapper;
import com.github.thesilentpro.hangarapi.gson.GsonUtils;
import com.github.thesilentpro.hangarapi.model.project.Project;
import com.github.thesilentpro.hangarapi.model.user.User;
import com.github.thesilentpro.hangarapi.request.*;
import com.github.thesilentpro.hangarapi.request.implementation.*;
import com.github.thesilentpro.hangarapi.response.auth.AuthenticationResponse;
import com.github.thesilentpro.hangarapi.response.implementation.auth.AuthenticationResponseImpl;
import com.github.thesilentpro.hangarapi.response.implementation.key.APIKeyResponseImpl;
import com.github.thesilentpro.hangarapi.response.implementation.page.PageResponseImpl;
import com.github.thesilentpro.hangarapi.response.implementation.project.ProjectResponseImpl;
import com.github.thesilentpro.hangarapi.response.implementation.project.ProjectStatsResponseImpl;
import com.github.thesilentpro.hangarapi.response.implementation.user.UserResponseImpl;
import com.github.thesilentpro.hangarapi.response.key.APIKeyResponse;
import com.github.thesilentpro.hangarapi.response.page.PageResponse;
import com.github.thesilentpro.hangarapi.response.project.ProjectResponse;
import com.github.thesilentpro.hangarapi.response.project.ProjectStatsResponse;
import com.github.thesilentpro.hangarapi.response.user.UserProjectsResponse;
import com.github.thesilentpro.hangarapi.response.user.UserResponse;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.concurrent.CompletableFuture;

public class HangarClientImpl implements HangarClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(HangarClientImpl.class);

    private final Requester requester;

    public HangarClientImpl(@NotNull HttpClient httpClient,  @Nullable AuthProvider authProvider, @Nullable String userAgent) {
        this.requester = new Requester(this, httpClient, authProvider, userAgent);
    }

    public HangarClientImpl(@NotNull Requester requester) {
        this.requester = requester;
    }

    // Authentication

    @Override
    public CompletableFuture<AuthenticationResponse> authenticate() {
        return this.requester.authenticate();
    }

    @Override
    public CompletableFuture<AuthenticationResponse> retrieveAuthentication() {
        LOGGER.debug("Authenticating: {}...", this.requester.getAuthProvider().getApiKey().substring(0, 8));
        if (this.requester.getAuthProvider() == null || this.requester.getAuthProvider().getApiKey() == null) {
            return CompletableFuture.failedFuture(new NullPointerException("AuthProvider/APIKey is not set!"));
        }
        return this.requester.sendRequest(this.requester.newRequest("authenticate?apiKey=" + this.requester.getAuthProvider().getApiKey(), false).POST(HttpRequest.BodyPublishers.noBody()).build(), response -> {
            JsonObject main = JsonParser.parseString(response.body()).getAsJsonObject();
            return new AuthenticationResponseImpl(response, main.get("token").getAsString(), main.get("expiresIn").getAsInt());
        });
    }

    // User

    @Override
    public UsersRequestBuilder retrieveAuthors() {
        return new UsersRequestBuilderImpl(this.requester, "authors");
    }

    @Override
    public UsersRequestBuilder retrieveStaff() {
        return new UsersRequestBuilderImpl(this.requester, "staff");
    }

    @Override
    public UsersRequestBuilder retrieveUsers() {
        return new UsersRequestBuilderImpl(this.requester, "users");
    }

    @Override
    public CompletableFuture<UserResponse> retrieveUser(@NotNull String user) {
        if (user.isEmpty()) {
            return CompletableFuture.failedFuture(new IllegalArgumentException("User must not be null!"));
        }
        return getRequester().sendRequest(getRequester().newRequest("users/" + encode(user), false).build(), response -> new UserResponseImpl(response, GsonUtils.GSON.fromJson(response.body(), User.class)));
    }

    @Override
    public CompletableFuture<UserProjectsResponse> retrieveUserPinned(@NotNull String user) {
        if (user.isEmpty()) {
            return CompletableFuture.failedFuture(new IllegalArgumentException("User must not be null!"));
        }
        return getRequester().sendRequest(getRequester().newRequest("users/" + encode(user) + "/pinned", false).build(), response -> GsonUtils.GSON.fromJson(response.body(), GsonUtils.PROJECTS_TYPE));
    }

    @Override
    public UserProjectsRequestBuilder retrieveUserStarred(@NotNull String user) {
        return new UserProjectsRequestBuilderImpl(this.requester, "/starred").user(user);
    }

    @Override
    public UserProjectsRequestBuilder retrieveUserWatching(@NotNull String user) {
        return new UserProjectsRequestBuilderImpl(this.requester, "/watching").user(user);
    }

    // API Keys

    @Override
    public CompletableFuture<APIKeyResponse> retrieveKeys() {
        return getRequester().sendRequest(getRequester().newRequest("keys").build(), response -> new APIKeyResponseImpl(response, GsonUtils.GSON.fromJson(response.body(), APIKeyMapper.TYPE)));
    }

    // Page

    @Override
    public CompletableFuture<PageResponse> retrievePage(@NotNull String project) {
        return this.requester.sendRequest(this.requester.newRequest("pages/main/" + encode(project), false).setHeader("Accept", "text/plain").build(), response -> new PageResponseImpl(response, response.body()));
    }

    @Override
    public CompletableFuture<PageResponse> retrievePage(@NotNull String project, @NotNull String page) {
        return this.requester.sendRequest(this.requester.newRequest("pages/page/" + encode(project) + "?path=" + encode(page), false).setHeader("Accept", "text/plain").build(), response -> new PageResponseImpl(response, response.body()));
    }

    // Permissions

    @Override
    public PermissionRequestBuilder retrievePermissions() {
        return new PermissionRequestBuilderImpl(this.requester);
    }

    @Override
    public HasPermissionsRequestBuilder hasPermissions() {
        return new HasPermissionsRequestBuilderImpl(this.requester);
    }

    @Override
    public HasAnyPermissionsRequestBuilder hasPermissionsAny() {
        return new HasAnyPermissionsRequestBuilderImpl(this.requester);
    }

    // Project

    @Override
    public ProjectsRequestBuilder retrieveProjects() {
        return new ProjectsRequestBuilderImpl(this.requester);
    }

    @Override
    public CompletableFuture<ProjectResponse> retrieveProject(@NotNull String project) {
        return this.requester.sendRequest(this.requester.newRequest("projects/" + encode(project), false).build(), response -> new ProjectResponseImpl(response, GsonUtils.GSON.fromJson(response.body(), Project.class)));
    }

    @Override
    public ProjectMembersRequestBuilder retrieveMembers(@NotNull String project) {
        return new ProjectMembersRequestBuilderImpl(this.requester).project(project);
    }

    @Override
    public ProjectUsersRequestBuilder retrieveStargazers(@NotNull String project) {
        return new ProjectUsersRequestBuilderImpl(this.requester, "/stargazers");
    }

    @Override
    public CompletableFuture<ProjectStatsResponse> retrieveStats(@NotNull String project, @NotNull Instant start, @NotNull Instant end) {
        return this.requester.sendRequest(this.requester.newRequest("projects/" + encode(project) + "/stats" + "?fromDate=" + encode(start.toString()) + "&toDate=" + encode(end.toString())).build(), response -> new ProjectStatsResponseImpl(response, GsonUtils.GSON.fromJson(response.body(), GsonUtils.PROJECT_STATS_TYPE)));
    }

    @Override
    public ProjectUsersRequestBuilder retrieveWatchers(@NotNull String project) {
        return new ProjectUsersRequestBuilderImpl(this.requester, "/watchers");
    }

    @Override
    public CompletableFuture<ProjectResponse> retrieveHash(@NotNull String hash) {
        return this.requester.sendRequest(this.requester.newRequest("versions/hash/" + encode(hash)).build(), response -> new ProjectResponseImpl(response, GsonUtils.GSON.fromJson(response.body(), Project.class)));
    }

    // Version

    @Override
    public CompletableFuture<String> retrieveLatestRelease(@NotNull String project) {
        return this.requester.sendRequest(this.requester.newRequest("projects/" + encode(project) + "/latestrelease", false).build(), HttpResponse::body);
    }


    // Network

    @Override
    public Requester getRequester() {
        return this.requester;
    }

    private static String encode(String param) {
        return URLEncoder.encode(param, StandardCharsets.UTF_8);
    }

}
