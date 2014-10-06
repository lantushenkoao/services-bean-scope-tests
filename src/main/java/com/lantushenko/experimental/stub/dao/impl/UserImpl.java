package com.lantushenko.experimental.stub.dao.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.joda.time.DateTime;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.lantushenko.experimental.stub.dao.Role;
import com.lantushenko.experimental.stub.dao.User;

public class UserImpl extends AbstractEntityImpl<UserImpl> implements User {

    private String login;
    private String fullName;
    private DateTime deletedAt;
    private String password;
    private Set<Role> roles;

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

    @Override
    public Set<Role> getRoles() {
        return roles;
    }

    @Override
    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (null == roles) {
            return new ArrayList<GrantedAuthority>();
        }
        Set<GrantedAuthority> setAuths = new HashSet<GrantedAuthority>();

        for (Role userRole : roles) {
            setAuths.add(new SimpleGrantedAuthority(userRole.getName()));
        }

        return new ArrayList<GrantedAuthority>(setAuths);
    }

    @Override
    public String getUsername() {
        return login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return getDeletedAt() == null;
    }

}
