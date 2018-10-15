package com.srb.project.persister;

import com.srb.project.model.DeviceEntity;
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
public class ServicesDevice {

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
        entityManager.merge(object);
    }

    public DeviceEntity findById(Integer idDevice) {
        DeviceEntity deviceEntity;
        deviceEntity = entityManager.find(DeviceEntity.class, idDevice);
        return deviceEntity;
    }





}
