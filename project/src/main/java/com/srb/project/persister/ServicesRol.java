package com.srb.project.persister;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Service
public class ServicesRol {

    @PersistenceContext
    EntityManager entityManager;

    @Transactional
    public void save(Object object) {
        entityManager.persist(object);
    }

    @Transactional
    public void update(Object object) {
        entityManager.merge(object);
    }

    @Transactional
    public void delete(Object object) {
        entityManager.remove(object);
    }


    public int existelRol(Object nameRole) {
        Query existRol = entityManager.createQuery("from RolesEntity where  namerole=:nameroles");
        existRol.setParameter("nameroles",nameRole);
        int value = existRol.getResultList().size();
        return value;
    }
}
