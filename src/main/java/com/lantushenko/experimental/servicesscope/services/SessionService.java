package com.lantushenko.experimental.servicesscope.services;

import com.lantushenko.experimental.servicesscope.exceptions.UserNotFound;

public interface SessionService {

    void authenticate(String login, String password) throws UserNotFound;

}