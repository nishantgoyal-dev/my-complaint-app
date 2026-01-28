package com.app.dao;

import java.util.Optional;

import com.complaint.system.model.User;
import com.complaint.system.util.JPAUtil;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;

public class UserDaoImpl implements UserDAO {

    @Override
    public void save(User user) {
        try (var em = JPAUtil.getEntityManagerFactory().createEntityManager()) {
            em.getTransaction().begin();
            em.persist(user);
            em.getTransaction().commit();
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    @Override
    public Optional<User> findByUsername(String username) {
        try (var em = JPAUtil.getEntityManagerFactory().createEntityManager()) {
            User user = em.createQuery("SELECT u FROM User u WHERE u.username = :user", User.class)
                    .setParameter("user", username)
                    .getSingleResult();
            return Optional.of(user);

        } catch (NoResultException e) {
            return Optional.empty();
        }
    }

    @Override
    public User findById(int id) {
        try (EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager()) {
            return em.find(User.class, id);
        }
    }

}
