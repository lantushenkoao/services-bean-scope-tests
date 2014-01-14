package com.lantushenko.experimental.servicesscope.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.lantushenko.experimental.servicesscope.dao.User;
import com.lantushenko.experimental.servicesscope.exceptions.UserNotFound;
import com.lantushenko.experimental.servicesscope.repositories.UserRepository;
import com.lantushenko.experimental.servicesscope.services.PasswordHashService;
import com.lantushenko.experimental.servicesscope.services.UserService;

@Component
@Scope(proxyMode = ScopedProxyMode.INTERFACES, value = "request")
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

    private User user;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordHashService passwordHashService;

    @Override
    public void load(String login, String plainTextPassword) throws UserNotFound {
        User user = userRepository.findUserByLogin(login);
        String actualPasswordHash = passwordHashService.generatePasswordHash(plainTextPassword);
        if (!StringUtils.endsWithIgnoreCase(actualPasswordHash, user.getPassword())) {
            throw new UserNotFound(login);
        }
        this.user = user;
    }

    @Override
    public void save() {
        if (user.isTransient()) {
            userRepository.insert(user);
        } else {
            userRepository.update(user);
        }
    }
}
