package com.app.controller;

import java.io.IOException;
import java.util.List;

import com.app.service.ComplaintService;
import com.complaint.system.model.Complaint;
import com.complaint.system.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/raise_complaint")
public class ComplaintServlet extends HttpServlet {
    private ComplaintService complaintService = new ComplaintService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // req.setCharacterEncoding("UTF-8");
        var session = req.getSession();
        User user = (User) session.getAttribute("user");
        String title = req.getParameter("title");
        String description = req.getParameter("description");
        Complaint cmp = new Complaint(title, description, user);
        complaintService.raiseNewComplaint(cmp);

        resp.sendRedirect(req.getContextPath() + "/raise_complaint");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        int page = 1;
        String pageStr = req.getParameter("page");
        if (pageStr != null && !pageStr.isEmpty()) {
            page = Integer.parseInt(pageStr);
        }

        int pageSize = 10;
        List<Complaint> complaints = complaintService.getPaginatedClientComplaints(user.getId(), page, pageSize);
        long totalComplaints = complaintService.getTotalCountByUser(user.getId());
        int totalPages = (int) Math.ceil((double) totalComplaints / pageSize);

        req.setAttribute("complaints", complaints);
        req.setAttribute("currentPage", page);
        req.setAttribute("totalPages", totalPages);

        req.getRequestDispatcher("/client/view_my_complaints.jsp").forward(req, resp);
    }

}
