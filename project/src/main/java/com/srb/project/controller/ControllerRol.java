package com.srb.project.controller;

import com.srb.project.model.RolesEntity;
import com.srb.project.persister.ServicesRol;
import com.srb.project.util.ValidationsString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@Controller
public class ControllerRol {

    @Autowired
    ServicesRol servicesRol;



    public RolesEntity save(RolesEntity rolEntity) {

        if (validateRol(rolEntity)) {
            rolEntity.setStatedelete((byte)1);
            servicesRol.save(rolEntity);

        }

        return rolEntity;
    }

    public boolean deleteRol(RolesEntity rolEntity) {

        boolean deleteRol = false;
        RolesEntity rolesEntityBd = null;

        try {
            rolesEntityBd = servicesRol.findByIdRol(rolEntity.getIdrol());
            if (rolesEntityBd != null) {
                rolesEntityBd.setStatedelete((byte) 0);
                servicesRol.delete(rolesEntityBd);
                deleteRol = true;
            }

        } catch (Exception e) {

        }

        return deleteRol;
    }


    public RolesEntity updateRol(RolesEntity rolesEntity) {

        boolean updateRol = false;
        RolesEntity rolesEntityBd = null;

        try {
            rolesEntityBd = servicesRol.findByIdRol(rolesEntity.getIdrol());
            if (rolesEntityBd != null) {
                if (!rolesEntity.equals(rolesEntityBd)) {
                    servicesRol.update(rolesEntity);
                    updateRol = true;
                }

            }

        } catch (Exception e) {

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
