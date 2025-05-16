package com.github.thesilentpro.hangarapi.client;

import com.github.thesilentpro.hangarapi.client.builder.HangarClientBuilder;
import com.github.thesilentpro.hangarapi.client.builder.HangarClientBuilderImpl;
import com.github.thesilentpro.hangarapi.request.*;
import com.github.thesilentpro.hangarapi.response.auth.AuthenticationResponse;
import com.github.thesilentpro.hangarapi.response.key.APIKeyResponse;
import com.github.thesilentpro.hangarapi.response.page.PageResponse;
import com.github.thesilentpro.hangarapi.response.project.ProjectResponse;
import com.github.thesilentpro.hangarapi.response.project.ProjectStatsResponse;
import com.github.thesilentpro.hangarapi.response.user.UserProjectsResponse;
import com.github.thesilentpro.hangarapi.response.user.UserResponse;
import org.jetbrains.annotations.NotNull;

import java.time.Instant;
import java.util.concurrent.CompletableFuture;

// TODO: Better javadocs
/**
 * The HangarClient is the main interface for interacting with the Hangar API.
 */
public interface HangarClient {

    /**
     * Initializes a new {@link HangarClientBuilder} to configure and build a {@link HangarClient} instance.
     *
     * @return a new instance of {@link HangarClientBuilder}
     */
    static HangarClientBuilder builder() {
        return new HangarClientBuilderImpl();
    }

    // Auth

    /**
     * Authenticates the client using the provided {@link com.github.thesilentpro.hangarapi.auth.AuthProvider}.
     *
     * @return a {@link CompletableFuture} that resolves to an {@link AuthenticationResponse} upon successful authentication
     */
    CompletableFuture<AuthenticationResponse> authenticate();

    /**
     * Manual authentication.
     *
     * @see #authenticate()
     * @return a {@link CompletableFuture} that resolves to an {@link AuthenticationResponse} upon successful authentication
     */
    CompletableFuture<AuthenticationResponse> retrieveAuthentication();

    // User

    /**
     * Retrieves a builder for querying staff member data.
     *
     * @return an instance of {@link UsersRequestBuilder} scoped to staff
     */
    UsersRequestBuilder retrieveStaff();

    /**
     * Retrieves a builder for querying author-specific user data.
     *
     * @return an instance of {@link UsersRequestBuilder} scoped to authors
     */
    UsersRequestBuilder retrieveAuthors();

    /**
     * Retrieves a builder for querying user-related data.
     *
     * @return an instance of {@link UsersRequestBuilder}
     */
    UsersRequestBuilder retrieveUsers();

    /**
     * Retrieves information for a specific user.
     *
     * @param user the username of the user to retrieve
     * @return a {@link CompletableFuture} that resolves to a {@link UserResponse}
     */
    CompletableFuture<UserResponse> retrieveUser(@NotNull String user);

    CompletableFuture<UserProjectsResponse> retrieveUserPinned(@NotNull String user);

    UserProjectsRequestBuilder retrieveUserStarred(@NotNull String user);

    UserProjectsRequestBuilder retrieveUserWatching(@NotNull String user);

    // API Keys

    /**
     * Retrieves the current API key information for the authenticated user.
     *
     * @return a {@link CompletableFuture} that resolves to an {@link APIKeyResponse}
     */
    CompletableFuture<APIKeyResponse> retrieveKeys();

    // Page

    /**
     * Retrieves the default page of the specified project.
     *
     * @param project the slug or ID of the project
     * @return a {@link CompletableFuture} that resolves to a {@link PageResponse}
     */
    CompletableFuture<PageResponse> retrievePage(@NotNull String project);

    /**
     * Retrieves a specific page by name for the given project.
     *
     * @param project the slug or ID of the project
     * @param page    the name or slug of the page
     * @return a {@link CompletableFuture} that resolves to a {@link PageResponse}
     */
    CompletableFuture<PageResponse> retrievePage(@NotNull String project, @NotNull String page);

    // Permission

    /**
     * Retrieves the permissions associated with the current user or context.
     *
     * @return a {@link PermissionRequestBuilder} to configure the permission request
     */
    PermissionRequestBuilder retrievePermissions();

    /**
     * Checks whether the current user has specific permissions.
     *
     * @return a {@link HasPermissionsRequestBuilder} to build the permission check
     */
    HasPermissionsRequestBuilder hasPermissions();

    /**
     * Checks whether the current user has any of a set of permissions.
     *
     * @return a {@link HasAnyPermissionsRequestBuilder} for building the permission check
     */
    HasAnyPermissionsRequestBuilder hasPermissionsAny();

    // Projects

    ProjectsRequestBuilder retrieveProjects();

    /**
     * Retrieves detailed information about a project.
     *
     * @param project the slug or ID of the project
     * @return a {@link CompletableFuture} that resolves to a {@link ProjectResponse}
     */
    CompletableFuture<ProjectResponse> retrieveProject(@NotNull String project);

    ProjectMembersRequestBuilder retrieveMembers(@NotNull String project);

    ProjectUsersRequestBuilder retrieveStargazers(@NotNull String project);

    /**
     * Retrieve all stats for a specific project in the given time range.
     * Requires {@link com.github.thesilentpro.hangarapi.response.implementation.permission.Permission#IS_SUBJECT_MEMBER}.
     *
     * @param project The project to check stats for.
     * @param start The epoch starting time.
     * @param end The epoch end time.
     * @return Response
     */
    CompletableFuture<ProjectStatsResponse> retrieveStats(@NotNull String project, @NotNull Instant start, @NotNull Instant end);

    ProjectUsersRequestBuilder retrieveWatchers(@NotNull String project);

    /**
     * Retrieves project of the first version that matches the given file hash
     * <a href="https://hangar.papermc.io/api-docs#get-/api/v1/versions/hash/-hash-">Docs.</a>
     *
     * @param hash The file hash.
     * @return Response
     */
    CompletableFuture<ProjectResponse> retrieveHash(@NotNull String hash);

    // Version

    CompletableFuture<String> retrieveLatestRelease(@NotNull String project);

    // Network

    /**
     * Returns the internal {@link Requester} used to perform API requests.
     *
     * @return the {@link Requester} instance
     */
    Requester getRequester();

}