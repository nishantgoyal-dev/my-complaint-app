package com.complaint.system.util;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JPAUtil {
    public static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("ComplaintPU");

    public static EntityManagerFactory getEntityManagerFactory(){
        return emf;
    }
    public static void shutdown() {
        if (emf != null) {
            emf.close();
        }
    }
    
}
