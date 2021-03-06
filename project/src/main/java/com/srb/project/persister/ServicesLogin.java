package com.srb.project.persister;


import com.srb.project.model.UsersEntity;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;
import java.util.List;

@Service
public class ServicesLogin implements Serializable {
    @PersistenceContext
    EntityManager entityManager;

    public static final String QUERY_USER = "from UsersEntity where username=:userName AND password=:password";

    public Boolean findUser(UsersEntity user) {
        Boolean expression = false;
        List<UsersEntity> entityList = entityManager.createQuery(QUERY_USER, UsersEntity.class)
                .setParameter("userName", user.getUsername()).setParameter("password", user.getPassword()).getResultList();

        if (!entityList.isEmpty()) {
            expression = true;
        }else {
            expression = false;
        }
        return expression;
    }

    public UsersEntity findUser(String user, String password) {
        Boolean expression = false;
        UsersEntity usersEntity = null;
        List<UsersEntity> entityList = entityManager.createQuery(QUERY_USER, UsersEntity.class)
                .setParameter("userName", user).setParameter("password", password).getResultList();
        if (!entityList.isEmpty()) {
            usersEntity = (UsersEntity)entityList.get(0);
            expression = true;
        }else {
            expression = false;
        }
        return usersEntity;
    }

    public UsersEntity validateInfo(String user, String password) {
        Boolean expression = false;
        UsersEntity entity = null;
        if (user != null && !user.isEmpty() && password != null && !password.isEmpty()) {
             entity = findUser(user, password);
            if ( entity != null) {
                expression = true;
            } else {
                expression = false;
            }
        } else {
            expression = false;
        }
        return entity;
    }
}
