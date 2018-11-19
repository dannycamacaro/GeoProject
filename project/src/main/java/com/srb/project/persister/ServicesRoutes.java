package com.srb.project.persister;

import com.srb.project.model.RoutesEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.Collection;

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
        entityManager.merge(object);
    }
    
    

    public RoutesEntity findById(Integer idRoute) {
        RoutesEntity routesEntity;
        routesEntity = entityManager.find(RoutesEntity.class, idRoute);
        return routesEntity;
    }

    public Collection<RoutesEntity> findAllRoutes() {

        Query query = entityManager.createQuery("from RoutesEntity routes where statedelete=:state");
        query.setParameter("state", (byte)1);
        Collection <RoutesEntity> routesEntities = new ArrayList<>();
        routesEntities = query.getResultList();

        return routesEntities;
    }

    public Collection<RoutesEntity> findListById(Integer idRoute) {
        Collection <RoutesEntity> routesEntities = new ArrayList<>();
        RoutesEntity routesEntity;
        routesEntity = entityManager.find(RoutesEntity.class, idRoute);
        routesEntities.add(routesEntity);
        return routesEntities;
    }

    public int loadRouteByName(String nameroutes) {


        Query existRol = entityManager.createQuery("from RoutesEntity where  upper(nameroutes)=:nameroutes and  statedelete=:state");
        existRol.setParameter("nameroutes", nameroutes.toUpperCase());
        existRol.setParameter("state", (byte)1);
        int value = existRol.getResultList().size();
        return value;
    }
}
