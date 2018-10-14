package com.srb.project.controller;

import com.srb.project.model.RolesEntity;
import com.srb.project.persister.ServicesRol;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;


@Controller
public class ControllerRol {
    @Autowired
    ServicesRol servicesRol;

    public Boolean saveRol (RolesEntity rolEntity){
        Boolean validRol=false;
        try {
            if (ValidateRol(rolEntity)){
                servicesRol.save(rolEntity);
                validRol = true;
            }
        }catch (Exception e){
            validRol = false;
        }
        return validRol;
    }

    private boolean ValidateRol(RolesEntity rolesEntity) {
        boolean expresion = false;
        if(!ExisteRol(rolesEntity.getNamerole())){
            expresion = true;
        }
        return expresion;
    }

    private boolean ExisteRol(String nameRole) {
        boolean existe= false;
        if (servicesRol.existelRol(nameRole)>0){
            existe = true;
        }
        return existe;
    }
}
