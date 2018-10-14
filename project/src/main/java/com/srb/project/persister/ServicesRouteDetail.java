package com.srb.project.persister;

import com.srb.project.model.RoutedetailEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Service
public class ServicesRouteDetail {

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

    public List<RoutedetailEntity> findAll(){
        List<RoutedetailEntity> entityList =  entityManager.createQuery("from RoutedetailEntity",RoutedetailEntity.class).getResultList();
        return entityList;
    }
}
