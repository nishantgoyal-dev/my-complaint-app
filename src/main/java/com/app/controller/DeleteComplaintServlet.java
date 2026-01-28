package com.app.controller;

import java.io.IOException;

import com.complaint.system.model.Complaint;
import com.complaint.system.model.ComplaintStatus;
import com.complaint.system.model.User;
import com.complaint.system.util.JPAUtil;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/delete_complaint")
public class DeleteComplaintServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        var id = req.getParameter("id");
        if (id != null) {
            try (var em = JPAUtil.getEntityManagerFactory().createEntityManager()) {
                var complaint = em.find(Complaint.class, Integer.parseInt(id));
                User loggedInUser = (User) req.getSession().getAttribute("user");
                em.getTransaction().begin();
                if (complaint != null && complaint.getUser().getId() == loggedInUser.getId()) {
                    complaint.setStatus(ComplaintStatus.WITHDRAWN);
                    em.getTransaction().commit();
                } else {
                    // Someone is trying to withdraw someone else's complaint!
                    em.getTransaction().rollback();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        resp.sendRedirect(req.getContextPath() + "/raise_complaint");
    }

}
