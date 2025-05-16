package com.github.thesilentpro.hangarapi.response.implementation.project;

import com.github.thesilentpro.hangarapi.model.project.member.ProjectMember;
import com.github.thesilentpro.hangarapi.response.PaginatedResponseImpl;
import com.github.thesilentpro.hangarapi.response.project.ProjectMembersResponse;

import java.net.http.HttpResponse;
import java.util.List;

public class ProjectMembersResponseImpl extends PaginatedResponseImpl<String> implements ProjectMembersResponse {

    private final List<ProjectMember> members;

    public ProjectMembersResponseImpl(HttpResponse<String> response, int count, int limit, int offset, List<ProjectMember> members) {
        super(response, count, limit, offset);
        this.members = members;
    }

    @Override
    public List<ProjectMember> getMembers() {
        return this.members;
    }

}