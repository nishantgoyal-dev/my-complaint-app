package com.app.controller;

import java.io.IOException;
import java.util.List;

import com.complaint.system.model.User;
import com.complaint.system.util.JPAUtil;

import jakarta.persistence.TypedQuery;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String pass = req.getParameter("password");
        String role = req.getParameter("role");

        try (var em = JPAUtil.getEntityManagerFactory().createEntityManager();) {
            TypedQuery<User> query = em.createQuery("SELECT u FROM User u WHERE u.username = :username", User.class);
            query.setParameter("username", username);

            List<User> temp = query.getResultList();
            if (temp.isEmpty()) {
                em.getTransaction().begin();
                User user = new User(username, pass, role);
                em.persist(user);
                em.getTransaction().commit();
            } else {
                throw new Exception("Username already exist");
            }
            resp.sendRedirect(req.getContextPath() + "/views/login.jsp?msg=success");
        } catch (Exception e) {
            e.printStackTrace();
            resp.sendRedirect(req.getContextPath() + "/views/register.jsp?error=failed");
        }

    }

}
