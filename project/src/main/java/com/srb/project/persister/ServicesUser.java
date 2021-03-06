package com.srb.project.persister;

import com.srb.project.model.UsersEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.Collection;

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
        entityManager.merge(object);
    }

    public UsersEntity findById(Integer idUsers) {
        UsersEntity usersEntity;
        usersEntity = entityManager.find(UsersEntity.class, idUsers);
        return usersEntity;
    }

    public Collection<UsersEntity> findAllUsers() {
        Query query = entityManager.createQuery("from UsersEntity user where statedelete=:state");
        query.setParameter("state", (byte)1);
        Collection <UsersEntity> usersEntities = new ArrayList<>();
        usersEntities = query.getResultList();

        return usersEntities;
    }

    public int loadUserByUserName(String username) {
        Query existRol = entityManager.createQuery("from UsersEntity where  upper(username)=:username and  statedelete=:state");
        existRol.setParameter("username", username.toUpperCase());
        existRol.setParameter("state", (byte)1);
        int value = existRol.getResultList().size();
        return value;
    }

    public int loadUserByIdentityDocument(String identitydocument) {
        Query existRol = entityManager.createQuery("from UsersEntity where  upper(identitydocument)=:identitydocument and  statedelete=:state");
        existRol.setParameter("identitydocument", identitydocument.toUpperCase());
        existRol.setParameter("state", (byte)1);
        int value = existRol.getResultList().size();
        return value;

    }
}
