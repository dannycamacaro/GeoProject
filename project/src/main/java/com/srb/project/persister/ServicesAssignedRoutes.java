package com.srb.project.persister;


import com.srb.project.model.AssignedroutesEntity;
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
public class ServicesAssignedRoutes {

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
    public Collection<AssignedroutesEntity> findAllAssignedRoutes() {
        Query query = entityManager.createQuery("from AssignedroutesEntity asignnedVehicle");
        Collection <AssignedroutesEntity> assignedvehicleEntities = new ArrayList<>();
        assignedvehicleEntities = query.getResultList();

        return assignedvehicleEntities;
    }

    public AssignedroutesEntity findById(Integer idAssidnedRoutes) {
        AssignedroutesEntity assignedroutesEntity;
        assignedroutesEntity = entityManager.find(AssignedroutesEntity.class, idAssidnedRoutes);
        return assignedroutesEntity;
    }

    public Collection<AssignedroutesEntity> findByIdDevice(Integer idDevice) {
        Collection<AssignedroutesEntity> assignedroutesEntity = new ArrayList<>();
        Query query = entityManager.createQuery("from AssignedroutesEntity av where av.iddevice=:idDevice");
        query.setParameter("idDevice",idDevice);
        assignedroutesEntity = query.getResultList();
        return assignedroutesEntity;
    }

}
