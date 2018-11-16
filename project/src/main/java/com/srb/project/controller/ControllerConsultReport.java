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
public class ControllerConsultReport {

    @Autowired
    ServicesVehicle servicesVehicle;

    @Autowired
    ServicesAudit servicesAudit;

    @Autowired
    private ApplicationContext appContext;




    public Collection<VehicleEntity> findAllVehicleActive() {

        Collection<VehicleEntity> vehicleEntities = new ArrayList<>();
        vehicleEntities = servicesVehicle.findAllVehicle();

        return vehicleEntities;
    }




}
