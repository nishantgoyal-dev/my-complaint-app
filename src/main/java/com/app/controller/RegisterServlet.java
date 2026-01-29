package com.app.controller;

import java.io.IOException;

import com.app.service.UserService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    private UserService userService = new UserService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String pass = req.getParameter("password");

        // The Service handles the role enforcement and existence check
        boolean success = userService.registerUser(username, pass);

        if (success) {
            resp.sendRedirect(req.getContextPath() + "/auth/login.jsp?msg=registered");
        } else {
            // Redirect back with a specific error message
            resp.sendRedirect(req.getContextPath() + "/auth/register.jsp?error=exists");
        }
    }
}