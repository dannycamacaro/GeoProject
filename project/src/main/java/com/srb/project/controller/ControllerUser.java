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

       AuditsEntity  auditsEntity = loadAudit(usersEntity,EnumOperation.ADD_USER.getIdOperation());
        try {
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

//        AuditsEntity auditsEntity = new AuditsEntity();
//        auditsEntity.setContent(user.toString());
//        auditsEntity.setIdusers(1);//TODO AJUSTARLO CON EL ID DEL USUARIO QUE INICIO SESION
//        auditsEntity.setTypeoperation(EnumOperation.DELETE_USER.getIdOperation());
        AuditsEntity  auditsEntity = loadAudit(user,EnumOperation.DELETE_USER.getIdOperation());
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

        boolean updateUser = false;
        UsersEntity userEntityBd = null;
       /* AuditsEntity auditsEntity = new AuditsEntity();
        auditsEntity.setContent(usersEntity.toString());
        auditsEntity.setIdusers(1);//TODO AJUSTARLO CON EL ID DEL USUARIO QUE INICIO SESION
        auditsEntity.setTypeoperation(EnumOperation.EDIT_USER.getIdOperation());*/

        AuditsEntity  auditsEntity = loadAudit(usersEntity,EnumOperation.EDIT_USER.getIdOperation());
        try {
            userEntityBd = servicesUser.findById(usersEntity.getIdusers());
            if (userEntityBd != null) {
                if (!usersEntity.equals(userEntityBd)) {
                    servicesUser.update(usersEntity);

                    auditsEntity.setStatusoperation(AuditsEntity.OPERATION_SUCCESSFUL);
                    servicesAudit.save(auditsEntity);
                    updateUser = true;
                }

            }

        } catch (Exception e) {
            auditsEntity.setStatusoperation(AuditsEntity.OPERATION_NOT_SUCCESSFUL);;
            servicesAudit.save(auditsEntity);

        }

        return usersEntity;
    }

    private AuditsEntity loadAudit(UsersEntity usersEntity, String operation){
        AuditsEntity auditsEntity = new AuditsEntity(Page.getCurrent().getWebBrowser().getAddress());
        ControllerLogin controller = (ControllerLogin) appContext.getBean("controllerLogin");
        auditsEntity.setContent(usersEntity.toString());
        if(controller != null && controller.getUsersEntity() != null){
            auditsEntity.setIdusers(controller.getUsersEntity().getIdusers());
        }

        auditsEntity.setTypeoperation(operation);

        usersEntity.setStatedelete((byte) 1);
        return auditsEntity;

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
