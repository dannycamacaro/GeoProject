package com.srb.project.persister;

import com.srb.project.model.LocationEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
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

    public List<LocationEntity> findByVehicleAndDate(Integer idVehicle, LocalDate date) {
        List<LocationEntity> entityList = new ArrayList<>();
        Query queryExistLocation = entityManager.createQuery("from LocationEntity loc where loc.idvehicle=:idVehicle and locationdate LIKE :date");
        queryExistLocation.setParameter("idVehicle", idVehicle);
        queryExistLocation.setParameter("date", java.sql.Date.valueOf(date));
        entityList = queryExistLocation.getResultList();
        return entityList;
    }


}
