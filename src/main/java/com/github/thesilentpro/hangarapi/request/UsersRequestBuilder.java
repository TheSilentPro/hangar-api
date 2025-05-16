package com.github.thesilentpro.hangarapi.request;

import com.github.thesilentpro.hangarapi.client.HangarClient;
import com.github.thesilentpro.hangarapi.model.implementation.user.UsersSortType;
import com.github.thesilentpro.hangarapi.response.user.UsersResponse;

public interface UsersRequestBuilder extends PaginatedRequestBuilder<UsersResponse> {

    UsersRequestBuilder query(String query);

    /**
     * Available types for {@link HangarClient#retrieveAuthors()} are: {@link UsersSortType#NAME},{@link UsersSortType#CREATED_AT},{@link UsersSortType#PROJECT_COUNT}
     * <br>
     * Available types for {@link HangarClient#retrieveStaff()} are: {@link UsersSortType#NAME},{@link UsersSortType#CREATED_AT},{@link UsersSortType#ROLES}
     * <br>
     * All types are available for {@link HangarClient#retrieveUsers()}.
     *
     * @param sortType The sorting type.
     * @return Builder.
     */
    UsersRequestBuilder sort(UsersSortType sortType);

}
