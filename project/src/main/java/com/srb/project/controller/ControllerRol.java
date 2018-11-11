package com.srb.project.controller;

import com.srb.project.enumConstans.EnumOperation;
import com.srb.project.model.AuditsEntity;
import com.srb.project.model.RolesEntity;
import com.srb.project.persister.ServicesAudit;
import com.srb.project.persister.ServicesRol;
import com.srb.project.util.ValidationsString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@Controller
public class ControllerRol {

    @Autowired
    ServicesRol servicesRol;

    @Autowired
    ServicesAudit servicesAudit;

    @Autowired
    private ApplicationContext appContext;


    public RolesEntity save(RolesEntity rolEntity) {
        AuditsEntity  auditsEntity = new AuditsEntity();
        try {
            if (validateRol(rolEntity)) {
                 auditsEntity = ControllerAudit.loadInformationAudit(rolEntity.toString(), EnumOperation.ADD_ROL.getIdOperation(),"controllerLogin",appContext);
                rolEntity.setStatedelete((byte)1);
                servicesRol.save(rolEntity);
                auditsEntity.setStatusoperation(AuditsEntity.OPERATION_SUCCESSFUL);
                servicesAudit.save(auditsEntity);
            }
        } catch (Exception e) {
            e.printStackTrace();
            auditsEntity.setStatusoperation(AuditsEntity.OPERATION_NOT_SUCCESSFUL);
            servicesAudit.save(auditsEntity);
        }

        return rolEntity;
    }

    public boolean deleteRol(RolesEntity rolEntity) {
        AuditsEntity  auditsEntity = ControllerAudit.loadInformationAudit(rolEntity.toString(),EnumOperation.DELETE_ROL.getIdOperation(),"controllerLogin",appContext);
        boolean deleteRol = false;
        RolesEntity rolesEntityBd = null;

        try {
            rolesEntityBd = servicesRol.findByIdRol(rolEntity.getIdrol());
            if (rolesEntityBd != null) {
                rolesEntityBd.setStatedelete((byte) 0);
                servicesRol.delete(rolesEntityBd);
                auditsEntity.setStatusoperation(AuditsEntity.OPERATION_SUCCESSFUL);
                servicesAudit.save(auditsEntity);
                deleteRol = true;
            }

        } catch (Exception e) {
            auditsEntity.setStatusoperation(AuditsEntity.OPERATION_NOT_SUCCESSFUL);
            servicesAudit.save(auditsEntity);
        }

        return deleteRol;
    }


    public RolesEntity updateRol(RolesEntity rolesEntity) {

        RolesEntity rolesEntityBd = null;
        AuditsEntity  auditsEntity = ControllerAudit.loadInformationAudit(rolesEntity.toString(),EnumOperation.EDIT_ROL.getIdOperation(),"controllerLogin",appContext);


        try {
            rolesEntityBd = servicesRol.findByIdRol(rolesEntity.getIdrol());
            if (rolesEntityBd != null) {
                if (!rolesEntity.equals(rolesEntityBd)) {
                    servicesRol.update(rolesEntity);
                    auditsEntity.setStatusoperation(AuditsEntity.OPERATION_SUCCESSFUL);
                    servicesAudit.save(auditsEntity);
                }
            }

        } catch (Exception e) {
            auditsEntity.setStatusoperation(AuditsEntity.OPERATION_NOT_SUCCESSFUL);;
            servicesAudit.save(auditsEntity);

        }

        return rolesEntity;
    }

    private boolean validateRol(RolesEntity rolesEntity) {
        boolean expresion = false;
        if (!existRolByName(rolesEntity.getNamerole())) {
            expresion = true;
        }
        return expresion;
    }

    private boolean existRolByName(String nameRole) {
        boolean existe = false;
        if (servicesRol.loadRolByName(nameRole) > 0) {
            existe = true;
        }
        return existe;
    }

    private boolean existRolByDescription(String descriptionRol) throws Exception {
        boolean existe = false;

        existe = servicesRol.existRolByDescription(descriptionRol);

        return existe;
    }


    public Collection<RolesEntity> findAllRoles() {
        Collection<RolesEntity> rolesEntities = new ArrayList<>();
        rolesEntities = servicesRol.findAllRoles();

        return rolesEntities;
    }
}
