package com.github.thesilentpro.hangarapi.gson;

import com.github.thesilentpro.hangarapi.gson.project.*;
import com.github.thesilentpro.hangarapi.gson.user.NameHistoryMapper;
import com.github.thesilentpro.hangarapi.gson.user.UserMapper;
import com.github.thesilentpro.hangarapi.gson.user.UserProjectMapper;
import com.github.thesilentpro.hangarapi.model.project.*;
import com.github.thesilentpro.hangarapi.model.project.member.ProjectMember;
import com.github.thesilentpro.hangarapi.model.project.member.ProjectMemberRole;
import com.github.thesilentpro.hangarapi.model.project.stats.ProjectStats;
import com.github.thesilentpro.hangarapi.model.user.NameHistory;
import com.github.thesilentpro.hangarapi.model.user.User;
import com.github.thesilentpro.hangarapi.model.user.UserProject;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class GsonUtils {

    public static final Type SET_STRING_TYPE = new TypeToken<Set<String>>(){}.getType();
    public static final Type SET_TYPE = new TypeToken<Set<Integer>>(){}.getType();
    public static final Type MAP_TYPE = new TypeToken<Map<String, String>>(){}.getType();
    public static final Type PROJECT_STATS_TYPE = new TypeToken<Map<String, ProjectStats>>(){}.getType();
    public static final Type USERS_TYPE = new TypeToken<List<User>>(){}.getType();
    public static final Type PROJECTS_TYPE = new TypeToken<List<Project>>(){}.getType();
    public static final Type USER_PROJECTS_TYPE = new TypeToken<List<UserProject>>(){}.getType();
    public static final Type PROJECT_MEMBERS_TYPE = new TypeToken<List<ProjectMember>>(){}.getType();
    public static final Type NAME_HISTORY_TYPE = new TypeToken<Set<NameHistory>>(){}.getType();

    public static final Gson GSON = new GsonBuilder()
            .registerTypeAdapter(PermissionSetMapper.TYPE, new PermissionSetMapper())
            .registerTypeAdapter(APIKeyMapper.TYPE, new APIKeyMapper())
            .registerTypeAdapter(NameHistory.class, new NameHistoryMapper())
            .registerTypeAdapter(ProjectStats.class, new ProjectStatsMapper())
            .registerTypeAdapter(ProjectMember.class, new ProjectMemberMapper())
            .registerTypeAdapter(ProjectMemberRole.class, new ProjectMemberRoleMapper())
            .registerTypeAdapter(User.class, new UserMapper())
            .registerTypeAdapter(UserProject.class, new UserProjectMapper())
            .registerTypeAdapter(Namespace.class, new NamespaceMapper())
            .registerTypeAdapter(License.class, new LicenseMapper())
            .registerTypeAdapter(UserActions.class, new UserActionsMapper())
            .registerTypeAdapter(Stats.class, new StatsMapper())
            .registerTypeAdapter(Link.class, new LinkMapper())
            .registerTypeAdapter(LinkGroup.class, new LinkGroupMapper())
            .registerTypeAdapter(Settings.class, new SettingsMapper())
            .registerTypeAdapter(Project.class, new ProjectMapper())
            .serializeNulls()
            .create();

}
