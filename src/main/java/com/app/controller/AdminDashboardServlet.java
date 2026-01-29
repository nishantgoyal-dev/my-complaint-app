package com.app.controller;
import java.io.IOException;
import java.util.List;

import com.app.service.ComplaintService;
import com.complaint.system.model.Complaint;
import com.complaint.system.model.ComplaintStatus;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/admin/admin_dashboard")
public class AdminDashboardServlet extends HttpServlet {
    private ComplaintService complaintService = new ComplaintService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1. Get current page from request, default to 1
        int page = 1;
        String pageStr = req.getParameter("page");
        if (pageStr != null && !pageStr.isEmpty()) {
            page = Integer.parseInt(pageStr);
        }

        // 2. Fetch data using optimized Service methods
        List<Complaint> complaints = complaintService.getAdminDashboardList(page);
        long totalComplaints = complaintService.getTotalComplaintCount();
        long pendingCount = complaintService.getTotalComplaintCountByStatus(ComplaintStatus.PENDING);

        // 3. Calculate total pages (assuming 10 per page)
        int totalPages = (int) Math.ceil((double) totalComplaints / 10);

        // 4. Set attributes for JSP
        req.setAttribute("complaints", complaints);
        req.setAttribute("currentPage", page);
        req.setAttribute("totalPages", totalPages);
        req.setAttribute("totalCount", totalComplaints);
        req.setAttribute("pendingCount", pendingCount);

        req.getRequestDispatcher("/admin/dashboard.jsp").forward(req, resp);
    }
}