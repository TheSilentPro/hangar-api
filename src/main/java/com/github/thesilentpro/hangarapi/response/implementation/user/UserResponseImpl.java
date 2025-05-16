package com.github.thesilentpro.hangarapi.response.implementation.user;

import com.github.thesilentpro.hangarapi.model.user.User;
import com.github.thesilentpro.hangarapi.response.implementation.ResponseImpl;
import com.github.thesilentpro.hangarapi.response.user.UserResponse;

import java.net.http.HttpResponse;

public class UserResponseImpl extends ResponseImpl<String> implements UserResponse {

    private final User user;

    public UserResponseImpl(HttpResponse<String> response, User user) {
        super(response);
        this.user = user;
    }

    @Override
    public User getUser() {
        return this.user;
    }

}
