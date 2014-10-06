package com.lantushenko.experimental.stub.services;

import java.util.List;

import com.lantushenko.experimental.stub.dao.User;

public interface UserService {

    void save(User user);

    List<User> listUsers();

    User add(String login, String fullName, String password);

    User findUserById(Long id);

    void updateUserPassword(Long userId, String newPassword);

    void delete(User user);

}