package com.app.dao;

import com.complaint.system.model.Complaint;
import java.util.List;

public interface ComplaintDAO {
    void save(Complaint complaint);
    void update(Complaint complaint);
    Complaint findById(int id);
    List<Complaint> findByUserId(int userId);
    List<Complaint> findAll(); // For the Admin side
}