package com.srb.project.controller;


import com.srb.project.model.VehicleEntity;
import com.srb.project.persister.ServicesVehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.Collection;

@Controller
public class ControllerVehicle {

    @Autowired
    ServicesVehicle servicesVehicle;


    public void save(VehicleEntity vehicleEntity) {

        vehicleEntity.setStatedelete((byte) 1);
        servicesVehicle.save(vehicleEntity);
    }

    public Collection<VehicleEntity> findAllVehicle() {

        Collection<VehicleEntity> vehicleEntities = new ArrayList<>();
        vehicleEntities = servicesVehicle.findAllVehicle();

        return vehicleEntities;
    }

    public VehicleEntity updateVehicle(VehicleEntity vehicleEntity) {
        boolean updateVehicle = false;
        VehicleEntity vehicleEntityBd = null;

        try {
            vehicleEntityBd = servicesVehicle.findById(vehicleEntity.getIdvehicle());
            if (vehicleEntityBd != null) {
                if (!vehicleEntity.equals(vehicleEntityBd)) {
                    servicesVehicle.update(vehicleEntity);
                    updateVehicle = true;
                }

            }

        } catch (Exception e) {

        }

        return vehicleEntity;
    }

    public boolean deleteVehicle(VehicleEntity vehicle) {

        boolean deleteVehicle = false;
        VehicleEntity vehicleEntityBd = null;

        try {
            vehicleEntityBd = servicesVehicle.findById(vehicle.getIdvehicle());
            if (vehicleEntityBd != null) {
                vehicleEntityBd.setStatedelete((byte) 0);
                servicesVehicle.delete(vehicleEntityBd);
                deleteVehicle = true;
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return deleteVehicle;
    }
}
