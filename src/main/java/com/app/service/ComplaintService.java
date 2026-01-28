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
}