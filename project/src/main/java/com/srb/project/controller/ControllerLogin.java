package com.srb.project.controller;

import com.srb.project.model.UsersEntity;
import com.srb.project.persister.ServicesLogin;
import com.vaadin.ui.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class ControllerLogin {

    @Autowired
    ServicesLogin loginServices;

    private UsersEntity usersEntity;

    public boolean validateLogin(String txtUser, String txtPassword) {
        UsersEntity entity = loginServices.validateInfo(txtUser, txtPassword);
        if (entity != null) {
            setUsersEntity(entity);
            return true;
        } else {
            return false;
        }
    }

    public UsersEntity getUsersEntity() {
        return usersEntity;
    }

    public void setUsersEntity(UsersEntity usersEntity) {
        this.usersEntity = usersEntity;
    }
}
