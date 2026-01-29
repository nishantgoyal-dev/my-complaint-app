package com.app.controller;

import java.io.IOException;

import com.app.service.ComplaintService;
import com.complaint.system.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/delete_complaint")
public class DeleteComplaintServlet extends HttpServlet {
    private ComplaintService complaintService = new ComplaintService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idStr = req.getParameter("id");
        User user = (User) req.getSession().getAttribute("user");

        if (idStr != null && user != null) {
            int complaintId = Integer.parseInt(idStr);
            
            complaintService.withdrawComplaint(complaintId, user.getId());
        }
        
        resp.sendRedirect(req.getContextPath() + "/raise_complaint");
    }
}
