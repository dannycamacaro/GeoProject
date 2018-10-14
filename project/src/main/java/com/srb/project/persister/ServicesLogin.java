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

    public Boolean findUser(String user, String password) {
        Boolean expression = false;
        List<UsersEntity> entityList = entityManager.createQuery(QUERY_USER, UsersEntity.class)
                .setParameter("userName", user).setParameter("password", password).getResultList();
        if (!entityList.isEmpty()) {
            expression = true;
        }else {
            expression = false;
        }
        return expression;
    }

    public boolean validateInfo(String user, String password) {
        Boolean expression = false;
        if (user != null && !user.isEmpty() && password != null && !password.isEmpty()) {
            if (findUser(user, password)) {
                expression = true;
            } else {
                expression = false;
            }
        } else {
            expression = false;
        }
        return expression;
    }
}
