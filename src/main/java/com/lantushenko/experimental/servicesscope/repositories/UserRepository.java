package com.lantushenko.experimental.servicesscope.repositories;

import com.lantushenko.experimental.servicesscope.dao.User;

public interface UserRepository extends AbstractRepository {

    User findUserById(int userId);

    User findUserByLogin(String login);

    void insert(User user);

    /**
     * Update the existing user
     */
    void update(User user);
}
