package com.srb.project.controller;


import com.srb.project.enumConstans.EnumOperation;
import com.srb.project.model.AuditsEntity;
import com.srb.project.model.VehicleEntity;
import com.srb.project.persister.ServicesAudit;
import com.srb.project.persister.ServicesVehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.Collection;

@Controller
public class ControllerVehicle {

    @Autowired
    ServicesVehicle servicesVehicle;

    @Autowired
    ServicesAudit servicesAudit;

    @Autowired
    private ApplicationContext appContext;


    public void save(VehicleEntity vehicleEntity) {
        AuditsEntity auditsEntity = new AuditsEntity();

        try {
            auditsEntity = ControllerAudit.loadInformationAudit(vehicleEntity.toString(), EnumOperation.ADD_VEHICLE.getIdOperation(),"controllerLogin",appContext);
            vehicleEntity.setStatedelete((byte) 1);
            servicesVehicle.save(vehicleEntity);
            auditsEntity.setStatusoperation(AuditsEntity.OPERATION_SUCCESSFUL);
            servicesAudit.save(auditsEntity);
        } catch (Exception e) {
            e.printStackTrace();
            auditsEntity.setStatusoperation(AuditsEntity.OPERATION_NOT_SUCCESSFUL);
            servicesAudit.save(auditsEntity);
        }
    }

    public Collection<VehicleEntity> findAllVehicle() {

        Collection<VehicleEntity> vehicleEntities = new ArrayList<>();
        vehicleEntities = servicesVehicle.findAllVehicle();

        return vehicleEntities;
    }

    public VehicleEntity updateVehicle(VehicleEntity vehicleEntity) {
        VehicleEntity vehicleEntityBd = null;
        AuditsEntity  auditsEntity = ControllerAudit.loadInformationAudit(vehicleEntity.toString(),EnumOperation.EDIT_VEHICLE.getIdOperation(),"controllerLogin",appContext);


        try {
            vehicleEntityBd = servicesVehicle.findById(vehicleEntity.getIdvehicle());
            if (vehicleEntityBd != null) {
                if (!vehicleEntity.equals(vehicleEntityBd)) {
                    servicesVehicle.update(vehicleEntity);
                    auditsEntity.setStatusoperation(AuditsEntity.OPERATION_SUCCESSFUL);
                    servicesAudit.save(auditsEntity);
                }

            }

        } catch (Exception e) {
            auditsEntity.setStatusoperation(AuditsEntity.OPERATION_NOT_SUCCESSFUL);;
            servicesAudit.save(auditsEntity);
        }

        return vehicleEntity;
    }

    public boolean deleteVehicle(VehicleEntity vehicle) {

        boolean deleteVehicle = false;
        VehicleEntity vehicleEntityBd = null;
        AuditsEntity  auditsEntity = ControllerAudit.loadInformationAudit(vehicle.toString(),EnumOperation.DELETE_VEHICLE.getIdOperation(),"controllerLogin",appContext);

        try {
            vehicleEntityBd = servicesVehicle.findById(vehicle.getIdvehicle());
            if (vehicleEntityBd != null) {
                vehicleEntityBd.setStatedelete((byte) 0);
                servicesVehicle.delete(vehicleEntityBd);
                auditsEntity.setStatusoperation(AuditsEntity.OPERATION_SUCCESSFUL);
                servicesAudit.save(auditsEntity);
                deleteVehicle = true;
            }

        } catch (Exception e) {
            auditsEntity.setStatusoperation(AuditsEntity.OPERATION_NOT_SUCCESSFUL);
            servicesAudit.save(auditsEntity);        }

        return deleteVehicle;
    }
}
