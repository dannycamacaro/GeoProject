package com.srb.project.persister;

import com.srb.project.model.VehicleEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.Collection;

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
        entityManager.merge(object);
    }

    @Autowired
    public ServicesVehicle() {

    }

    public VehicleEntity findById(Integer idVehicle) {
        VehicleEntity vehicleEntity;
        vehicleEntity = entityManager.find(VehicleEntity.class, idVehicle);
        return vehicleEntity;
    }

    public Collection<VehicleEntity> findAllVehicle() {
        Query query = entityManager.createQuery("from VehicleEntity user where statedelete=:state");
        query.setParameter("state", (byte)1);
        Collection <VehicleEntity> vehicleEntities = new ArrayList<>();
        vehicleEntities = query.getResultList();

        return vehicleEntities;
    }

    public int loadVehicleByLicensePlate(Object licensePlateValue) {
        Query existRol = entityManager.createQuery("from VehicleEntity where  upper(licenseplate)=:licensePlateValue and  statedelete=:state");
        String licensePlate = (String) licensePlateValue;
        existRol.setParameter("licensePlateValue", licensePlate.toUpperCase());
        existRol.setParameter("state", (byte)1);
        int value = existRol.getResultList().size();
        return value;
    }

}
