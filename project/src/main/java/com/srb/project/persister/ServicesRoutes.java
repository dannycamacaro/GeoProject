package com.srb.project.persister;

import com.srb.project.model.RoutesEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@Service
public class ServicesRoutes {

    @Autowired
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

    public RoutesEntity findById(Integer idRoute) {
        RoutesEntity routesEntity;
        routesEntity = entityManager.find(RoutesEntity.class, idRoute);
        return routesEntity;
    }

}
