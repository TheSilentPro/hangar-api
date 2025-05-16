package com.github.thesilentpro.hangarapi.model.implementation.project.member;

import com.github.thesilentpro.hangarapi.model.project.member.ProjectMemberRole;

public class ProjectMemberRoleImpl implements ProjectMemberRole {

    private final String title;
    private final RoleColor color;
    private final int rank;
    private final String category;

    public ProjectMemberRoleImpl(String title, RoleColor color, int rank, String category) {
        this.title = title;
        this.color = color;
        this.rank = rank;
        this.category = category;
    }

    @Override
    public String getTitle() {
        return this.title;
    }

    @Override
    public RoleColor getColor() {
        return this.color;
    }

    @Override
    public int getRank() {
        return this.rank;
    }

    @Override
    public String getCategory() {
        return this.category;
    }

}
