package com.app.controller;

import java.io.IOException;

import com.app.service.ComplaintService;
import com.complaint.system.model.Complaint;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/admin/view_complaint")
public class AdminComplaintDetailServlet extends HttpServlet {

    ComplaintService complaintService = new ComplaintService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {

            int id = Integer.parseInt(req.getParameter("id"));
            Complaint cmp = complaintService.getComplaintById(id);
            req.setAttribute("complaint", cmp);
            req.getRequestDispatcher("/admin/view_detail.jsp").forward(req, resp);
        } catch (Exception e) {

            resp.sendRedirect("admin_dashboard?error=not_found");
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

    }

}
