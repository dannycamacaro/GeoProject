package com.srb.project.controller;

import com.srb.project.model.RolesEntity;
import com.srb.project.persister.ServicesRol;
import com.srb.project.util.ValidationsString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;


@Controller
public class ControllerRol {
    @Autowired
    ServicesRol servicesRol;

    public Boolean saveRol(RolesEntity rolEntity) {
        Boolean validRol = false;
        try {
            if (validateRol(rolEntity)) {
                servicesRol.save(rolEntity);
                validRol = true;
            }
        } catch (Exception e) {
            validRol = false;
        }
        return validRol;
    }

    //    public boolean deleteRol(int idRol) { //TODO DESCOMENTAR CUANDO SE TENGA LA VISTA BIEN, YA QUE SE VA A BUSCAR ES POR EL ID DEL ROL
    public boolean deleteRol(String name) {

        boolean deleteRol = false;
        RolesEntity rolesEntityBd = null;

        try {
//            rolesEntityBd = servicesRol.findByIdRol(idRol); TODO DESCOMENTAR CUANDO SE TENGA LA VISTA BIEN, YA QUE SE VA A BUSCAR ES POR EL ID DEL ROL
            rolesEntityBd = servicesRol.findRolByName(name); //TODO QUITAR CUANDO SE TENGA LA VISTA BIEN, YA QUE SE VA A BUSCAR ES POR EL ID DEL ROL
            if (rolesEntityBd != null) {
                rolesEntityBd.setStatedelete((byte) 1);
                servicesRol.delete(rolesEntityBd);
                deleteRol = true;
            }

        } catch (Exception e) {

        }

        return deleteRol;
    }

    //    public boolean deleteRol(RolesEntity  rolesEntity) { //TODO DESCOMENTAR CUANDO SE TENGA LA VISTA BIEN, YA QUE SE VA A BUSCAR ES POR EL ID DEL ROL
    public boolean updateRol(RolesEntity  rolesEntity) {

        boolean updateRol = false;
        RolesEntity rolesEntityBd = null;

        try {
//            rolesEntityBd = servicesRol.findByIdRol(idRol); TODO DESCOMENTAR CUANDO SE TENGA LA VISTA BIEN, YA QUE SE VA A BUSCAR ES POR EL ID DEL ROL
            rolesEntityBd = servicesRol.findByIdRol(rolesEntity.getIdrol()); //TODO QUITAR CUANDO SE TENGA LA VISTA BIEN, YA QUE SE VA A BUSCAR ES POR EL ID DEL ROL
            if (rolesEntityBd != null) {
                if(!rolesEntity.equals(rolesEntityBd)){
                    servicesRol.update(rolesEntity);
                    updateRol = true;
                }

            }

        } catch (Exception e) {

        }

        return updateRol;
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


}
