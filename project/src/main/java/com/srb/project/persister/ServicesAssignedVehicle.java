package com.srb.project.persister;


import com.srb.project.model.AssignedvehicleEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.Collection;


/**
 * Created by Ericka on 13-10-2018.
 */
@Service
public class ServicesAssignedVehicle {

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
        Object obj = entityManager.merge(object);
        entityManager.remove(obj);
        entityManager.flush();

    }

    public Collection<AssignedvehicleEntity> findAllAssignedVehicle() {
        Query query = entityManager.createQuery("from AssignedvehicleEntity asignnedVehicle");
        Collection <AssignedvehicleEntity> assignedvehicleEntities = new ArrayList<>();
        assignedvehicleEntities = query.getResultList();

        return assignedvehicleEntities;
    }

    public AssignedvehicleEntity findById(Integer idVehicle) {
        AssignedvehicleEntity assignedvehicleEntity;
        assignedvehicleEntity = entityManager.find(AssignedvehicleEntity.class, idVehicle);
        return assignedvehicleEntity;
    }

}
