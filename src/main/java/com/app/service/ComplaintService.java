package com.app.service;

import com.app.dao.ComplaintDAO;
import com.app.dao.ComplaintDAOImpl;
import com.complaint.system.model.Complaint;
import com.complaint.system.model.ComplaintStatus;
import java.util.List;

public class ComplaintService {
    private ComplaintDAO complaintDAO = new ComplaintDAOImpl();

    public void raiseNewComplaint(Complaint c) {
        c.setStatus(ComplaintStatus.PENDING); // Business rule: All new are Pending
        complaintDAO.save(c);
    }

    public List<Complaint> getClientComplaints(int userId) {
        return complaintDAO.findByUserId(userId);
    }

    public void withdrawComplaint(int complaintId, int userId) {
        Complaint c = complaintDAO.findById(complaintId);
        // Business Rule: Safety check
        if (c != null && c.getUser().getId() == userId && c.getStatus() == ComplaintStatus.PENDING) {
            c.setStatus(ComplaintStatus.WITHDRAWN);
            complaintDAO.update(c);
        }
    }

    // In ComplaintService.java
    public List<Complaint> getAdminDashboardList(int page) {
        return complaintDAO.findPaginated(page, 10); // Show 10 per page
    }

    public long getTotalComplaintCount() {
        return complaintDAO.countAll();
    }

    public long getTotalComplaintCountByStatus(ComplaintStatus status) {
        return complaintDAO.countByStatus(status);
    }

    public Complaint getComplaintById(int id) {
        return complaintDAO.findById(id);
    }

    public void updateStatus(int complaintId, ComplaintStatus status, String remarks) {
        Complaint c = complaintDAO.findById(complaintId);
        if (c != null) {
            c.setStatus(status);
            c.setAdminRemarks(remarks); // Save the feedback
            complaintDAO.update(c);
        }
    }

    public long getTotalCountByUser(int userId) {
        // Calls the DAO to get a simple COUNT from the database
        return complaintDAO.countByUserId(userId);
    }

    public List<Complaint> getPaginatedClientComplaints(int userId, int page, int pageSize) {
        // Calls the DAO to get 10 complaints starting at the correct offset
        return complaintDAO.findByUserIdPaginated(userId, page, pageSize);
    }
}