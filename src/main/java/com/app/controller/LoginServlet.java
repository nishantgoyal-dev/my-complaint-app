package com.app.controller;

import java.io.IOException;
import java.io.PrintWriter;

import com.complaint.system.model.User;
import com.complaint.system.model.UserRole;
import com.complaint.system.util.JPAUtil;

import jakarta.persistence.NoResultException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        String username = req.getParameter("username");
        String pass = req.getParameter("password");
        try (var em = JPAUtil.getEntityManagerFactory().createEntityManager();) {
            User user = em
                    .createQuery("SELECT u FROM User u WHERE u.username = :username AND u.password = :password",
                            User.class)
                    .setParameter("username", username).setParameter("password", pass).getSingleResult();
            var session = req.getSession();
            session.setAttribute("user", user);
            UserRole role = user.getRole();
            if (user.getRole() == UserRole.ADMIN) {
                resp.sendRedirect(req.getContextPath() + "/admin_dashboard");
            } else {
                // This now works for any standard user (Citizen, Student, Employee, etc.)
                resp.sendRedirect(req.getContextPath() + "/views/user_dashboard.jsp");
            }
        } catch (NoResultException e) {
            out.print("id or password wrong");
        } catch (Exception e) {
            out.print(e);
        }

    }

}
