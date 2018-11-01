package com.srb.project.controller;

import com.srb.project.enumConstans.EnumOperation;
import com.srb.project.model.AuditsEntity;
import com.srb.project.model.UsersEntity;
import com.srb.project.persister.ServicesAudit;
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

    @Autowired
    ServicesAudit servicesAudit;

    public Collection<UsersEntity> findAllUsers() {

        Collection<UsersEntity> usersEntities = new ArrayList<>();
        usersEntities = servicesUser.findAllUsers();

        return usersEntities;
    }

    public UsersEntity save(UsersEntity usersEntity) {

        AuditsEntity auditsEntity = new AuditsEntity();
        auditsEntity.setContent(usersEntity.toString());
        auditsEntity.setIdusers(1);//TODO AJUSTARLO CON EL ID DEL USUARIO QUE INICIO SESION
        auditsEntity.setTypeoperation(EnumOperation.ADD_USER.getIdOperation());

        usersEntity.setStatedelete((byte) 1);
        try {
            servicesUser.save(usersEntity);
            auditsEntity.setStatusoperation(1);
            servicesAudit.save(auditsEntity);
        } catch (Exception e) {
            e.printStackTrace();
            auditsEntity.setStatusoperation(0);
            servicesAudit.save(auditsEntity);
        }

        return usersEntity;
    }

    public boolean deleteUser(UsersEntity user) {

        AuditsEntity auditsEntity = new AuditsEntity();
        auditsEntity.setContent(user.toString());
        auditsEntity.setIdusers(1);//TODO AJUSTARLO CON EL ID DEL USUARIO QUE INICIO SESION
        auditsEntity.setTypeoperation(EnumOperation.DELETE_USER.getIdOperation());
        boolean deleteUser = false;
        UsersEntity usersEntityBd = null;

        try {
            usersEntityBd = servicesUser.findById(user.getIdusers());
            if (usersEntityBd != null) {
                usersEntityBd.setStatedelete((byte) 0);
                servicesUser.delete(usersEntityBd);
                auditsEntity.setStatusoperation(1);
                servicesAudit.save(auditsEntity);
                deleteUser = true;
            }

        } catch (Exception e) {
            auditsEntity.setStatusoperation(0);
            servicesAudit.save(auditsEntity);
        }

        return deleteUser;
    }

    public UsersEntity updateUser(UsersEntity usersEntity) {

        boolean updateUser = false;
        UsersEntity userEntityBd = null;
        AuditsEntity auditsEntity = new AuditsEntity();
        auditsEntity.setContent(usersEntity.toString());
        auditsEntity.setIdusers(1);//TODO AJUSTARLO CON EL ID DEL USUARIO QUE INICIO SESION
        auditsEntity.setTypeoperation(EnumOperation.EDIT_USER.getIdOperation());

        try {
            userEntityBd = servicesUser.findById(usersEntity.getIdusers());
            if (userEntityBd != null) {
                if (!usersEntity.equals(userEntityBd)) {
                    servicesUser.update(usersEntity);

                    auditsEntity.setStatusoperation(1);
                    servicesAudit.save(auditsEntity);
                    updateUser = true;
                }

            }

        } catch (Exception e) {
            auditsEntity.setStatusoperation(0);
            servicesAudit.save(auditsEntity);

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
