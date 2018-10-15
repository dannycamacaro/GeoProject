package com.srb.project.persister;

import com.srb.project.model.VehicleEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;

@Service
public class ServicesVehicle {
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

    public VehicleEntity findById(Integer idVehicle) {
        VehicleEntity vehicleEntity;
        vehicleEntity = entityManager.find(VehicleEntity.class, idVehicle);
        return vehicleEntity;
    }

}
