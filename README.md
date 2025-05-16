# HangarClient Wiki

## Overview

`HangarClient` is a Java API client designed for interacting with the [Hangar](https://hangar.papermc.io/api-docs) platform. 
<br>
It provides a comprehensive and intuitive interface for retrieving and managing Hangar projects, users, pages, statistics, API keys, and permissions.

---

## Table of Contents

1. [Setup](#setup)
2. [Authentication](#authentication)
3. [Client Initialization](#client-initialization)
4. [Project Operations](#project-operations)
    * [Retrieve All Projects](#retrieve-all-projects)
    * [Retrieve Specific Project](#retrieve-specific-project)
    * [Retrieve Project Members](#retrieve-project-members)
    * [Retrieve Watchers & Stargazers](#retrieve-watchers--stargazers)
    * [Retrieve Project Stats](#retrieve-project-stats)
    * [Page Retrieval](#page-retrieval)
5. [User Operations](#user-operations)
    * [Retrieve All Users](#retrieve-all-users)
    * [Retrieve Specific User](#retrieve-specific-user)
    * [Retrieve User Starred & Watching](#retrieve-user-starred--watching)
6. [API Keys & Permissions](#api-keys--permissions)
    * [Retrieve API Keys](#retrieve-api-keys)
    * [Check Permissions](#check-permissions)
    * [Retrieve Permissions](#retrieve-permissions)
7. [Model Classes](#model-classes)
8. [Further Reading](#further-reading)

---

## Setup

### Dependency

Add the Hangar API dependency to your Maven project:

```xml
<dependency>
  <groupId>com.github.TheSilentPro</groupId>
  <artifactId>hangar-api</artifactId>
  <version>REPLACE_WITH_LATEST</version>
</dependency>
```

```xml
<repository>
    <id>jitpack.io</id>
    <url>https://jitpack.io</url>
</repository>
```

---

## Authentication
Many endpoints can be used without authentication, but it is recommended that you provide it anyway.
<br>
To force authentication, use `HangarClient#authenticate();`.
<br>
You only need to call it once, the client will automatically renew when it expires.

### JWT Authentication

Use `JWTAuthProvider` for authentication with your API key:

```java
JWTAuthProvider authProvider = new JWTAuthProvider("API_KEY");
```

---

## Client Initialization

Initialize the client:

```java
HangarClient client = HangarClient.builder()
    .auth(new JWTAuthProvider("<token>"))
    .build();
```

You can optionally use a custom HTTP client and set a custom User-Agent.

---

## Client Information

Methods will return either a `CompletableFuture<Response>` or `RequestBuilder`.

The `RequestBuilder` allows you to further customize the request before sending it.
<br>
> You need to use `#execute()` before getting the future.

The `CompletableFuture<Response>` will return the response directly. This is used when further customization is not applicable.

> Note that calling `#join()` on `CompletableFuture` will make a blocking call. 
> <br>
> For async use `#whenComplete`, `thenAccept`, etc...

---

## Project Operations

### Retrieve All Projects

```java
client.retrieveProjects()
      .execute()
      .join()
      .getProjects()
      .forEach(this::printProjectInfo);
```

### Retrieve Specific Project

```java
Project project = client.retrieveProject("ProjectName")
                        .join()
                        .getProject();
```

Displays detailed project information such as avatar URL, category, timestamps, content, statistics, and more.

### Retrieve Project Members

```java
client.retrieveMembers("ProjectName")
      .limit(10)
      .execute()
      .join()
      .getMembers()
      .forEach(member -> {
          String name = member.getUser();
          UUID id = member.getUserId();
          String role = member.getRoles().stream()
                              .map(ProjectMemberRole::getTitle)
                              .findAny()
                              .orElse("N/A");
      });
```

### Retrieve Watchers & Stargazers

```java
// Watchers
client.retrieveWatchers("ProjectName")
      .join()
      .getUsers();

// Stargazers
client.retrieveStargazers("ProjectName")
      .join()
      .getUsers();
```

### Retrieve Project Stats

```java
client.retrieveStats("ProjectName", Instant.EPOCH, Instant.now())
      .join()
      .getStats()
      .forEach((platform, stats) -> {
          System.out.printf("%s: %d downloads, %d views\n", platform, stats.getDownloads(), stats.getViews());
      });
```

### Page Retrieval

```java
// Main page content
String mainContent = client.retrievePage("ProjectName")
                           .join()
                           .getContent();

// Specific page
String pageContent = client.retrievePage("ProjectName", "PageSlug")
                            .join()
                            .getContent();
```

---

## User Operations

### Retrieve All Users

```java
UsersResponse users = client.retrieveUsers()
                             .execute()
                             .join();
users.getUsers().forEach(u -> System.out.println(u.getName()));
```

### Retrieve Specific User

```java
User user = client.retrieveUser("Username")
                  .join()
                  .getUser();
```

### Retrieve User Starred & Watching

```java
// Starred projects
client.retrieveUserStarred("Username")
      .execute()
      .join()
      .getProjects();

// Watching projects
client.retrieveUserWatching("Username")
      .execute()
      .join()
      .getProjects();
```

---

## API Keys & Permissions

### Retrieve API Keys

```java
client.retrieveKeys()
      .join()
      .getKeys()
      .forEach(key -> System.out.println(key.getName()));
```

### Check Permissions

```java
client.hasPermissions()
      .fromProject("ProjectName")
      .withPermissions(Permission.EDIT_API_KEYS, Permission.VIEW_PUBLIC_INFO)
      .execute()
      .thenAccept(response -> {
          System.out.println("Allowed: " + response.getResult());
      })
      .join();
```

### Retrieve Permissions

```java
client.retrievePermissions()
      .fromProject("ProjectName")
      .execute()
      .thenAccept(response -> {
          System.out.println("Binary String: " + response.getPermissionBinString());
          response.getPermissions().forEach(p -> System.out.println("Allowed: " + p.name()));
      })
      .join();
```

---

## Model Classes
*Models are entities from hangar wrapped in java classes for ease of use.*

* `APIKey` - Represents an API Key.
* `Project` - Represents a project.
* `User` - Represents a user.
* `UserProject` - Represents a user project. Unlike Project, this holds less information.
* `ProjectMember` - Represents a user with roles on a project.
* `NameHistory` - Represents name history data from a user.
---

## Further Reading

* [Hangar API GitHub Repository](https://github.com/TheSilentPro/hangar-api)
* [API Reference Documentation](https://hangar.papermc.io/api-docs)
