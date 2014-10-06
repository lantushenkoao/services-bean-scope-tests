package com.lantushenko.experimental.stub.dao.impl;

import com.lantushenko.experimental.stub.dao.Role;

public class RoleImpl extends AbstractEntityImpl<RoleImpl> implements Role {

    private String name;

    public RoleImpl() {
        super(RoleImpl.class);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

}
