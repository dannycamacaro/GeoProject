package com.srb.project.controller;

import com.srb.project.model.UsersEntity;
import com.srb.project.persister.ServicesRol;
import com.srb.project.persister.ServicesUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.Collection;

@Controller
public class ControllerUser {

    @Autowired
    ServicesUser servicesUser;

    @Autowired
    ServicesRol servicesRol;

    public Collection<UsersEntity> findAllUsers() {
        Collection<UsersEntity> usersEntities = new ArrayList<>();
        usersEntities = servicesUser.findAllUsers();

        return usersEntities;
    }

    public UsersEntity save(UsersEntity usersEntity) {

        usersEntity.setStatedelete((byte) 1);
        servicesUser.save(usersEntity);

        return usersEntity;
    }

    public boolean deleteUser(UsersEntity user) {
        boolean deleteUser = false;
        UsersEntity usersEntityBd = null;

        try {
            usersEntityBd = servicesUser.findById(user.getIdusers());
            if (usersEntityBd != null) {
                usersEntityBd.setStatedelete((byte) 0);
                servicesUser.delete(usersEntityBd);
                deleteUser = true;
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return deleteUser;
    }

    public UsersEntity updateUser(UsersEntity usersEntity) {

        boolean updateUser = false;
        UsersEntity userEntityBd = null;

        try {
            userEntityBd = servicesUser.findById(usersEntity.getIdusers());
            if (userEntityBd != null) {
                if (!usersEntity.equals(userEntityBd)) {
                    servicesUser.update(usersEntity);
                    updateUser = true;
                }

            }

        } catch (Exception e) {

        }

        return usersEntity;
    }

    public ServicesUser getServicesUser() {
        return servicesUser;
    }

    public void setServicesUser(ServicesUser servicesUser) {
        this.servicesUser = servicesUser;
    }

    public ServicesRol getServicesRol() {
        return servicesRol;
    }

    public void setServicesRol(ServicesRol servicesRol) {
        this.servicesRol = servicesRol;
    }
}
