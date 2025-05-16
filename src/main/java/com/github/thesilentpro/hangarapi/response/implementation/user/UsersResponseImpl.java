package com.github.thesilentpro.hangarapi.response.implementation.user;

import com.github.thesilentpro.hangarapi.model.user.User;
import com.github.thesilentpro.hangarapi.response.PaginatedResponseImpl;
import com.github.thesilentpro.hangarapi.response.user.UsersResponse;

import java.net.http.HttpResponse;
import java.util.List;

public class UsersResponseImpl extends PaginatedResponseImpl<String> implements UsersResponse {

    private final List<User> users;

    public UsersResponseImpl(HttpResponse<String> response, int count, int limit, int offset, List<User> users) {
        super(response, count, limit, offset);
        this.users = users;
    }

    @Override
    public List<User> getUsers() {
        return this.users;
    }

}