package com.srb.project.controller;

import com.srb.project.enumConstans.EnumOperation;
import com.srb.project.model.AuditsEntity;
import com.srb.project.model.UsersEntity;
import com.srb.project.persister.ServicesAudit;
import com.srb.project.persister.ServicesRol;
import com.srb.project.persister.ServicesUser;
import com.vaadin.server.Page;
import com.vaadin.ui.UI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
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

    @Autowired
    private ApplicationContext appContext;

    public Collection<UsersEntity> findAllUsers() {

        Collection<UsersEntity> usersEntities = new ArrayList<>();
        usersEntities = servicesUser.findAllUsers();

        return usersEntities;
    }

    public UsersEntity save(UsersEntity usersEntity) {
        AuditsEntity  auditsEntity = new AuditsEntity();
        try {
            auditsEntity = ControllerAudit.loadInformationAudit(usersEntity.toString(),EnumOperation.ADD_USER.getIdOperation(),"controllerLogin",appContext);
            usersEntity.setStatedelete((byte) 1);
            servicesUser.save(usersEntity);
            auditsEntity.setStatusoperation(AuditsEntity.OPERATION_SUCCESSFUL);
            servicesAudit.save(auditsEntity);
        } catch (Exception e) {
            e.printStackTrace();
            auditsEntity.setStatusoperation(AuditsEntity.OPERATION_NOT_SUCCESSFUL);
            servicesAudit.save(auditsEntity);
        }

        return usersEntity;
    }

    public boolean deleteUser(UsersEntity user) {

        AuditsEntity  auditsEntity = ControllerAudit.loadInformationAudit(user.toString(),EnumOperation.DELETE_USER.getIdOperation(),"controllerLogin",appContext);
        boolean deleteUser = false;
        UsersEntity usersEntityBd = null;

        try {
            usersEntityBd = servicesUser.findById(user.getIdusers());
            if (usersEntityBd != null) {
                usersEntityBd.setStatedelete((byte) 0);
                servicesUser.delete(usersEntityBd);
                auditsEntity.setStatusoperation(AuditsEntity.OPERATION_SUCCESSFUL);
                servicesAudit.save(auditsEntity);
                deleteUser = true;
            }

        } catch (Exception e) {
            auditsEntity.setStatusoperation(AuditsEntity.OPERATION_NOT_SUCCESSFUL);
            servicesAudit.save(auditsEntity);
        }

        return deleteUser;
    }

    public UsersEntity updateUser(UsersEntity usersEntity) {

        UsersEntity userEntityBd = null;

        AuditsEntity  auditsEntity = ControllerAudit.loadInformationAudit(usersEntity.toString(),EnumOperation.EDIT_USER.getIdOperation(),"controllerLogin",appContext);
        try {
            userEntityBd = servicesUser.findById(usersEntity.getIdusers());
            if (userEntityBd != null) {
                if (!usersEntity.equals(userEntityBd)) {
         /*           userEntityBd.setNameRol(usersEntity.getNameRol());
                    userEntityBd.setIdrol(usersEntity.getIdrol());
                    userEntityBd.setPassword(usersEntity.getPassword());
                    userEntityBd.setAge(usersEntity.getAge());
                    userEntityBd.setEmail(usersEntity.getEmail());
                    userEntityBd.setPhonenumber(usersEntity.getPhonenumber());
                    userEntityBd.setIdentitydocument(usersEntity.getIdentitydocument());
                    userEntityBd.setRolesByIdrol(usersEntity.getRolesByIdrol());
                    userEntityBd.setListRoles(usersEntity.getListRoles());*/
                    servicesUser.update(usersEntity);
                    auditsEntity.setStatusoperation(AuditsEntity.OPERATION_SUCCESSFUL);
                    servicesAudit.save(auditsEntity);
                }

            }

        } catch (Exception e) {
            auditsEntity.setStatusoperation(AuditsEntity.OPERATION_NOT_SUCCESSFUL);;
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
