package com.lantushenko.experimental.servicesscope.dao.impl;

import org.joda.time.DateTime;

import com.lantushenko.experimental.servicesscope.dao.User;

public class UserImpl extends AbstractEntityImpl<UserImpl> implements User {

    private String login;
    private String fullName;
    private DateTime deletedAt;
    private String password;

    public UserImpl() {
        super(UserImpl.class);
    }

    @Override
    public String getLogin() {
        return login;
    }

    @Override
    public void setLogin(String login) {
        this.login = login;
    }

    @Override
    public String getFullName() {
        return fullName;
    }

    @Override
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    @Override
    public DateTime getDeletedAt() {
        return deletedAt;
    }

    @Override
    public void setDeletedAt(DateTime deletedAt) {
        this.deletedAt = deletedAt;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public void setPassword(String password) {
        this.password = password;
    }

}
