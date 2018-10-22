package com.srb.project.persister;

import com.srb.project.model.RolesEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Service
public class ServicesRol {

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


    public int loadRolByName(Object nameRole) {
        Query existRol = entityManager.createQuery("from RolesEntity where  namerole=:nameroles");
        existRol.setParameter("nameroles", nameRole);
        int value = existRol.getResultList().size();
        return value;
    }

    public boolean existRolByDescription(Object description) {
        boolean exist = false;

        Query query = entityManager.createQuery("from RolesEntity  where  descriptionrole=:description");
        query.setParameter("description", description);
        int value = query.getResultList().size();
        if (value > 0) {
            exist = true;
        }
        return exist;

    }

    public RolesEntity findRolByName(Object value) {
        RolesEntity rolesEntity = null;

        Query query = entityManager.createQuery("from RolesEntity where  namerole=:value");
        query.setParameter("value", value);
        if (query.getResultList().size() > 0) {
            rolesEntity = (RolesEntity) query.getResultList().get(0);
        }
        return rolesEntity;


    }

    public RolesEntity findByIdRol(int idRol) {
        RolesEntity rolesEntity = null;
        Query query = entityManager.createQuery("from RolesEntity rol where rol.idrol=:idRol");
        query.setParameter("idRol", idRol);
        if (query.getResultList().size() > 0) {
            rolesEntity = (RolesEntity) query.getResultList().get(0);
        }

        return rolesEntity;
    }
}
