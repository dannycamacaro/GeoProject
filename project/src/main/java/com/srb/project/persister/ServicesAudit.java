package com.srb.project.persister;


import com.srb.project.model.AuditsEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by Ericka on 13-10-2018.
 */
@Service
public class ServicesAudit {

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
    @Transactional
    public List<AuditsEntity> findAllAudits() {
        Query query = entityManager.createQuery("from AuditsEntity device");
        List<AuditsEntity> auditsEntities = new ArrayList<>();
        auditsEntities = query.getResultList();

        return auditsEntities;
    }


}
