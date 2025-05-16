package com.github.thesilentpro.hangarapi.model.project.member;

import com.github.thesilentpro.hangarapi.model.implementation.project.member.RoleColor;

public interface ProjectMemberRole {

    String getTitle();

    RoleColor getColor();

    int getRank();

    String getCategory();

}
