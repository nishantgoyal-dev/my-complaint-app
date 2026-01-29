package com.app.dao;

import java.util.List;

import com.complaint.system.model.Complaint;
import com.complaint.system.model.ComplaintStatus;
import com.complaint.system.model.User;
import com.complaint.system.util.JPAUtil;

import jakarta.persistence.EntityManager;

public class ComplaintDAOImpl implements ComplaintDAO {

    @Override
    public void save(Complaint complaint) {
        try (EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager()) {
            em.getTransaction().begin();

            // REATTACH the user to the current persistence context
            // This prevents "org.hibernate.PersistentObjectException: detached entity
            // passed to persist"
            if (complaint.getUser() != null) {
                User managedUser = em.merge(complaint.getUser());
                complaint.setUser(managedUser);
            }

            em.persist(complaint);
            em.getTransaction().commit();
        }
    }

    @Override
    public void update(Complaint complaint) {
        try (EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager()) {
            em.getTransaction().begin();
            em.merge(complaint); // merge updates an existing detached entity
            em.getTransaction().commit();
        }
    }

    @Override
    public Complaint findById(int id) {
        try (EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager()) {
            return em.find(Complaint.class, id);
        }
    }

    @Override
    public List<Complaint> findByUserId(int userId) {
        try (EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager()) {
            return em.createQuery(
                    "SELECT c FROM Complaint c WHERE c.user.id = :uid ORDER BY c.createdAt DESC",
                    Complaint.class)
                    .setParameter("uid", userId)
                    .getResultList();
        }
    }

    @Override
    public List<Complaint> findAll() {
        try (EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager()) {
            return em.createQuery("SELECT c FROM Complaint c ORDER BY c.createdAt DESC", Complaint.class)
                    .getResultList();
        }
    }

    // In ComplaintDAOImpl.java
    public List<Complaint> findPaginated(int pageNumber, int pageSize) {
        try (EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager()) {
            return em.createQuery("SELECT c FROM Complaint c ORDER BY c.createdAt DESC", Complaint.class)
                    .setFirstResult((pageNumber - 1) * pageSize) // Where to start
                    .setMaxResults(pageSize) // How many to take
                    .getResultList();
        }
    }

    public long countAll() {
        try (EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager()) {
            return em.createQuery("SELECT COUNT(c) FROM Complaint c", Long.class)
                    .getSingleResult();
        }

    }
    public long countByStatus(ComplaintStatus status) {
    try (EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager()) {
        return em.createQuery("SELECT COUNT(c) FROM Complaint c WHERE c.status = :status", Long.class)
                 .setParameter("status", status)
                 .getSingleResult();
    }
}

}
