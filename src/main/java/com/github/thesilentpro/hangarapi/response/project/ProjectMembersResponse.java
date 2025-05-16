package com.github.thesilentpro.hangarapi.response.project;

import com.github.thesilentpro.hangarapi.model.project.member.ProjectMember;
import com.github.thesilentpro.hangarapi.response.PaginatedResponse;

import java.util.List;

public interface ProjectMembersResponse extends PaginatedResponse<String> {

    List<ProjectMember> getMembers();

}