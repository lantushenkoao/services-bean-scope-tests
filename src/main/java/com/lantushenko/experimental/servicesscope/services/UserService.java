package com.lantushenko.experimental.servicesscope.services;

import com.lantushenko.experimental.servicesscope.exceptions.UserNotFound;

public interface UserService {

    void load(String login, String plainTextPassword) throws UserNotFound;

    void save();

}