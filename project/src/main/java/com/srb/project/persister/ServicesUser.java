package com.srb.project.persister;

import com.srb.project.model.UsersEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@Service
public class ServicesUser {

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

    public UsersEntity findById(Integer idUsers) {
        UsersEntity usersEntity;
        usersEntity = entityManager.find(UsersEntity.class, idUsers);
        return usersEntity;
    }
}
