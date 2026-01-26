package com.complaint.system;

import com.complaint.system.model.User;
import com.complaint.system.util.JPAUtil;

public class TestDB {
    public static void main(String[] args) {
        // Try-with-resources ensures the EntityManager closes automatically
        try (var em = JPAUtil.getEntityManagerFactory().createEntityManager()) {
            em.getTransaction().begin();

            // Create a dummy user to test persistence
            User testUser = new User("admin_test", "password123", "ADMIN");
            
            em.persist(testUser);
            em.getTransaction().commit();

            System.out.println("Success! User saved with ID: " + testUser.getId());
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Database connection or persistence failed!");
        } finally {
            JPAUtil.shutdown();
        }
    }
}