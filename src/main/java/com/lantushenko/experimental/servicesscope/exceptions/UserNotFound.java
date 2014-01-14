package com.lantushenko.experimental.servicesscope.exceptions;

public class UserNotFound extends Exception {

    public UserNotFound(String login) {
        super(String.format("Failed to find user %s. Please ensure that login and password are correct", login));
    }
}
