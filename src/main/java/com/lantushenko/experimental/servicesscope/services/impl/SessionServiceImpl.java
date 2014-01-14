package com.lantushenko.experimental.servicesscope.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import com.lantushenko.experimental.servicesscope.exceptions.UserNotFound;
import com.lantushenko.experimental.servicesscope.services.SessionService;
import com.lantushenko.experimental.servicesscope.services.UserService;

@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.INTERFACES)
public class SessionServiceImpl implements SessionService {

    @Autowired
    private UserService authenticatedUser;

    @Override
    public void authenticate(String login, String password) throws UserNotFound {
        authenticatedUser.load(login, password);
    }
}
