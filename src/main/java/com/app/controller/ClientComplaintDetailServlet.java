package com.app.controller;

import com.app.service.ComplaintService;
import com.complaint.system.model.Complaint;
import com.complaint.system.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/client/view_details")
public class ClientComplaintDetailServlet extends HttpServlet {
    private ComplaintService complaintService = new ComplaintService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        String idStr = req.getParameter("id");

        if (idStr != null && user != null) {
            int complaintId = Integer.parseInt(idStr);
            Complaint complaint = complaintService.getComplaintById(complaintId);

            // SECURITY CHECK: Ensure the complaint belongs to the logged-in user
            if (complaint != null && complaint.getUser().getId() == user.getId()) {
                req.setAttribute("complaint", complaint);
                req.getRequestDispatcher("/client/view_details.jsp").forward(req, resp);
                return;
            }
        }
        
        // If unauthorized or not found, send back to dashboard
        resp.sendRedirect(req.getContextPath() + "/raise_complaint?error=not_found");
    }
}