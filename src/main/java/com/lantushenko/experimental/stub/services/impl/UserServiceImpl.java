package com.lantushenko.experimental.stub.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.lantushenko.experimental.stub.dao.User;
import com.lantushenko.experimental.stub.dao.impl.UserImpl;
import com.lantushenko.experimental.stub.exceptions.UserNotFound;
import com.lantushenko.experimental.stub.repositories.UserRepository;
import com.lantushenko.experimental.stub.services.UserService;

@Component(value = "userService")
@Scope(proxyMode = ScopedProxyMode.INTERFACES, value = "singleton")
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService, UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional(readOnly = false)
    public User add(String login, String fullName, String password) {
        UserImpl user = new UserImpl();
        user.setLogin(login);
        user.setFullName(fullName);
        String encodedPassword = passwordEncoder.encode(password);
        user.setPassword(encodedPassword);

        userRepository.insert(user);

        return user;
    }

    @Override
    @Transactional(readOnly = false)
    public void delete(User user) {
        userRepository.delete(user);
    }

    @Override
    @Transactional(readOnly = false)
    public void save(User user) {
        Assert.isTrue(!user.isTransient());
        userRepository.update(user);
    }

    @Override
    @Transactional(readOnly = false)
    public void updateUserPassword(Long userId, String newPassword) {
        Assert.hasText(newPassword);
        User user = findUserById(userId);
        String encodedPassword = passwordEncoder.encode(newPassword);
        user.setPassword(encodedPassword);
        userRepository.update(user);
    }

    @Override
    public List<User> listUsers() {
        return userRepository.listUsers();
    }

    @Override
    public User findUserById(Long id) {
        User result = userRepository.findUserById(id);
        if (null == result) {
            throw new UserNotFound(Long.toString(id));
        }
        return result;
    }

    @Override
    public User loadUserByUsername(String login) throws UsernameNotFoundException {
        User user = null;
        try {
            user = userRepository.findUserByLogin(login);
        } catch (Exception e) {
            String details = e.getMessage();
        }
        return user;
    }
}
