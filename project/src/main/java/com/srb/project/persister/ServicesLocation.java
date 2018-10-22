package com.srb.project.persister;

import com.srb.project.model.LocationEntity;
import com.srb.project.model.VehicleEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ericka on 13-10-2018.
 */
@Service
public class ServicesLocation {

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

    public List<LocationEntity> findByIdVehicle(Integer idVehicle) {
        List<LocationEntity> entityList = new ArrayList<>();
        Query queryExistLocation = entityManager.createQuery("from LocationEntity loc where loc.idvehicle=:idVehicle");
        queryExistLocation.setParameter("idVehicle", idVehicle);
        entityList = queryExistLocation.getResultList();
        return entityList;
    }


}
