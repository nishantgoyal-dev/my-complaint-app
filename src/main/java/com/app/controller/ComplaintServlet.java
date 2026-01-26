package com.app.controller;

import java.io.IOException;

import com.complaint.system.model.Complaint;
import com.complaint.system.model.User;
import com.complaint.system.util.JPAUtil;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/raise_complaint")
public class ComplaintServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        var session = req.getSession();
        User user = (User) session.getAttribute("user");
        String title = req.getParameter("title");
        String description = req.getParameter("description");

        try (var em = JPAUtil.getEntityManagerFactory().createEntityManager();) {

            User managedUser = em.merge(user);
            Complaint cmp = new Complaint(title, description, managedUser);
            em.getTransaction().begin();
            em.persist(cmp);
            em.getTransaction().commit();
            System.out.println("inside try");

        } catch (Exception e) {
            System.out.println("inside catch");
            System.out.println(e.getMessage());
            e.printStackTrace();

        }
        resp.sendRedirect(req.getContextPath() +"/views/user_dashboard.jsp?msg=complaint_raised");
    }

}
