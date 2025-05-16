package com.github.thesilentpro.hangarapi.response.user;

import com.github.thesilentpro.hangarapi.model.user.User;
import com.github.thesilentpro.hangarapi.response.Response;

public interface UserResponse extends Response<String> {

    User getUser();

}
