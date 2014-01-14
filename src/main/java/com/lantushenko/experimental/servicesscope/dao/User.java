package com.lantushenko.experimental.servicesscope.dao;

import org.joda.time.DateTime;

public interface User extends AbstractEntity{

    String getLogin();

    void setLogin(String login);

    String getFullName();

    void setFullName(String fullName);

    DateTime getDeletedAt();

    void setDeletedAt(DateTime deletedAt);

    String getPassword();

    void setPassword(String password);

}