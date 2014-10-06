package com.lantushenko.experimental.stub.services.impl;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import com.lantushenko.experimental.stub.services.WebUtils;

@Component
public class WebUtilsImpl implements WebUtils {

    @Override
    public HttpHeaders makeJsonHeader() {

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        return headers;
    }

}
