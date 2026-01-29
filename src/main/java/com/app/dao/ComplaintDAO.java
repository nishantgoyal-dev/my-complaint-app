package com.app.dao;

import com.complaint.system.model.Complaint;
import com.complaint.system.model.ComplaintStatus;

import java.util.List;

public interface ComplaintDAO {
    void save(Complaint complaint);
    void update(Complaint complaint);
    Complaint findById(int id);
    List<Complaint> findByUserId(int userId);
    List<Complaint> findAll(); // For the Admin side
    List<Complaint> findPaginated(int page, int i);
    long countAll();
    public long countByStatus(ComplaintStatus status);
    long countByUserId(int userId);
    List<Complaint> findByUserIdPaginated(int userId, int page, int pageSize);
}