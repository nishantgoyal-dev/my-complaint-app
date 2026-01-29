package com.app.controller;

import java.io.IOException;

import com.app.service.ComplaintService;
import com.complaint.system.model.ComplaintStatus;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/admin/update_status")
public class UpdateStatusServlet extends HttpServlet {
    private ComplaintService complaintService = new ComplaintService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idStr = req.getParameter("complaintId");
        String statusStr = req.getParameter("newStatus");
        String remarks = req.getParameter("remarks");

        if (idStr != null && statusStr != null) {
            
            int id = Integer.parseInt(idStr);
            ComplaintStatus newStatus = ComplaintStatus.valueOf(statusStr); // Converts "RESOLVED" to Enum

            
            complaintService.updateStatus(id, newStatus,remarks);
        }
        resp.sendRedirect(req.getContextPath() + "/admin/admin_dashboard");
    }

}
