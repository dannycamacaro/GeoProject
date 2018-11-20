package com.srb.project.controller;


import com.srb.project.enumConstans.EnumOperation;
import com.srb.project.model.AssignedvehicleEntity;
import com.srb.project.model.AuditsEntity;
import com.srb.project.model.UsersEntity;
import com.srb.project.model.VehicleEntity;
import com.srb.project.persister.ServicesAssignedVehicle;
import com.srb.project.persister.ServicesAudit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.Collection;

@Controller
public class ControllerAssignedVehicle {

    @Autowired
    ServicesAssignedVehicle servicesAssignedVehicle;

    @Autowired
    ServicesAudit servicesAudit;

    @Autowired
    private ApplicationContext appContext;


    public void save(AssignedvehicleEntity assignedvehicleEntity) {
        AuditsEntity auditsEntity = new AuditsEntity();

        try {
            auditsEntity = ControllerAudit.loadInformationAudit(assignedvehicleEntity.toString(), EnumOperation.ADD_ASSIGNEDVEHICLE.getIdOperation(),"controllerLogin",appContext);
            servicesAssignedVehicle.save(assignedvehicleEntity);
            auditsEntity.setStatusoperation(AuditsEntity.OPERATION_SUCCESSFUL);
            servicesAudit.save(auditsEntity);
        } catch (Exception e) {
            e.printStackTrace();
            auditsEntity.setStatusoperation(AuditsEntity.OPERATION_NOT_SUCCESSFUL);
            servicesAudit.save(auditsEntity);
        }
    }

    public Collection<AssignedvehicleEntity> findAllAssignedVehicle() {

        Collection<AssignedvehicleEntity> assignedvehicleEntities = new ArrayList<>();
        assignedvehicleEntities = servicesAssignedVehicle.findAllAssignedVehicle();


        for (AssignedvehicleEntity entities :assignedvehicleEntities){
            entities.setNameUser(entities.getUsersByIdusers().getUsername());
            entities.setLicensePlate(entities.getVehicleByIdvehicle().getLicenseplate());
        }

        return assignedvehicleEntities;
    }

    public AssignedvehicleEntity updateAssigneVehicle(AssignedvehicleEntity assignedvehicleEntity) {
        AssignedvehicleEntity assignedvehicleEntityBd = null;
        AuditsEntity  auditsEntity = ControllerAudit.loadInformationAudit(assignedvehicleEntity.toString(),EnumOperation.EDIT_ASSIGNEDVEHICLE.getIdOperation(),"controllerLogin",appContext);


        try {
            assignedvehicleEntityBd = servicesAssignedVehicle.findById(assignedvehicleEntity.getIdassignedvehicle());
            if (assignedvehicleEntityBd != null) {
                if (!assignedvehicleEntity.equals(assignedvehicleEntityBd)) {
                    servicesAssignedVehicle.update(assignedvehicleEntity);
                    auditsEntity.setStatusoperation(AuditsEntity.OPERATION_SUCCESSFUL);
                    servicesAudit.save(auditsEntity);
                }

            }

        } catch (Exception e) {
            auditsEntity.setStatusoperation(AuditsEntity.OPERATION_NOT_SUCCESSFUL);;
            servicesAudit.save(auditsEntity);
        }

        return assignedvehicleEntity;
    }

    public boolean deleteAssignedVehicle(AssignedvehicleEntity assignedvehicleEntity) {

        boolean deleteVehicle = false;
        AssignedvehicleEntity vehicleEntityBd = null;
        AuditsEntity  auditsEntity = ControllerAudit.loadInformationAudit(assignedvehicleEntity.toString(),EnumOperation.DELETE_ASSIGNEDVEHICLE.getIdOperation(),"controllerLogin",appContext);

        try {
            vehicleEntityBd = servicesAssignedVehicle.findById(assignedvehicleEntity.getIdassignedvehicle());
            if (vehicleEntityBd != null) {
                servicesAssignedVehicle.delete(vehicleEntityBd);
                auditsEntity.setStatusoperation(AuditsEntity.OPERATION_SUCCESSFUL);
                servicesAudit.save(auditsEntity);
                deleteVehicle = true;
            }

        } catch (Exception e) {
            auditsEntity.setStatusoperation(AuditsEntity.OPERATION_NOT_SUCCESSFUL);
            servicesAudit.save(auditsEntity);        }

        return deleteVehicle;
    }

    public AssignedvehicleEntity findUserByVehicleID(VehicleEntity vehicleEntity) {
        AssignedvehicleEntity asignedVehicle = servicesAssignedVehicle.findById(vehicleEntity.getIdvehicle());
        return asignedVehicle;
    }

    public AssignedvehicleEntity findUserByUserID(UsersEntity userEntity) {
        AssignedvehicleEntity asignedVehicle = servicesAssignedVehicle.findByIdUsers(userEntity.getIdusers());
        return asignedVehicle;
    }
}
