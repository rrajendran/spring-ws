package com.capella.users.repository;

import com.capella.users.model.User;

public interface UserRepository{
    User save(User user);
    User findByUsername(String username);

    void deleteAll();
}
