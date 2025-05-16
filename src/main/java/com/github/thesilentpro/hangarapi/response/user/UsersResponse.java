package com.github.thesilentpro.hangarapi.response.user;

import com.github.thesilentpro.hangarapi.model.user.User;
import com.github.thesilentpro.hangarapi.response.PaginatedResponse;

import java.util.List;

public interface UsersResponse extends PaginatedResponse<String> {

    List<User> getUsers();

}