package com.app.service;

import com.app.dao.UserDaoImpl;
import com.complaint.system.model.User;
import com.app.dao.UserDAO;


public class UserService {
    private UserDAO userDAO = new UserDaoImpl();
    public User authenticate(String username, String password) {
        return userDAO.findByUsername(username)
                .filter(u -> u.getPassword().equals(password))
                .orElse(null);
    }
}
