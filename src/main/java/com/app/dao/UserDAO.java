package com.app.dao;

import java.util.Optional;

import com.complaint.system.model.User;

public interface UserDAO {
    void save(User user);
    Optional<User> findByUsername(String username);
    User findById(int id);
}
