package com.app.service;

import com.app.dao.UserDaoImpl;
import com.complaint.system.model.User;
import com.complaint.system.model.UserRole;
import com.app.dao.UserDAO;


public class UserService {
    private UserDAO userDAO = new UserDaoImpl();
    public User authenticate(String username, String password) {
        return userDAO.findByUsername(username)
                .filter(u -> u.getPassword().equals(password))
                .orElse(null);
    }
    public boolean registerUser(String username, String password) {
    // 1. Use DAO to check if user exists
    if (userDAO.findByUsername(username).isPresent()) {
        return false; // Registration failed: user exists
    }

    // 2. Create new User and ENFORCE the CLIENT role
    User newUser = new User();
    newUser.setUsername(username);
    newUser.setPassword(password); // Later we will hash this!
    newUser.setRole(UserRole.CLIENT); 

    // 3. Save via DAO
    userDAO.save(newUser);
    return true;
}
}
