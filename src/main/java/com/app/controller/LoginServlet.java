package com.app.controller;

import java.io.IOException;

import com.app.service.UserService;
import com.complaint.system.model.User;
import com.complaint.system.model.UserRole;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private UserService userService = new UserService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // PrintWriter out = resp.getWriter();
        String username = req.getParameter("username");
        String pass = req.getParameter("password");
        User user = userService.authenticate(username, pass);
        if (user != null) {
            var session = req.getSession();
            session.setAttribute("user", user);
            if (user.getRole() == UserRole.ADMIN) {
                resp.sendRedirect(req.getContextPath() + "/admin/admin_dashboard");
            } else {
                resp.sendRedirect(req.getContextPath() + "/client/user_dashboard.jsp");
            }
        } else {
            resp.sendRedirect(req.getContextPath() + "/auth/login.jsp?error=invalid");
        }

    }

}
