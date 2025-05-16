package com.github.thesilentpro.hangarapi.model.implementation.project.member;

import com.github.thesilentpro.hangarapi.model.project.member.ProjectMember;
import com.github.thesilentpro.hangarapi.model.project.member.ProjectMemberRole;

import java.util.List;

public class ProjectMemberImpl implements ProjectMember {

    private final String user;
    private final int userId;
    private final List<ProjectMemberRole> roles;

    public ProjectMemberImpl(String user, int userId, List<ProjectMemberRole> roles) {
        this.user = user;
        this.userId = userId;
        this.roles = roles;
    }

    public String getUser() {
        return this.user;
    }

    public int getUserId() {
        return this.userId;
    }

    public List<ProjectMemberRole> getRoles() {
        return this.roles;
    }

}