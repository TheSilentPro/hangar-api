package com.github.thesilentpro.hangarapi.model.project.member;

import java.util.List;

public interface ProjectMember {

    String getUser();

    int getUserId();

    List<ProjectMemberRole> getRoles();

}
