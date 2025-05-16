package com.github.thesilentpro.hangarapi.response;

import java.net.http.HttpResponse;

public interface Response<T> {

    HttpResponse<T> httpResponse();

}
