package com.srb.project.controller;

import com.srb.project.model.LocationEntity;
import com.srb.project.model.VehicleEntity;
import com.srb.project.persister.ServicesAudit;
import com.srb.project.persister.ServicesLocation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Controller
public class ControllerLocation {


    @Autowired
    ServicesLocation servicesLocation;

    @Autowired
    ServicesAudit servicesAudit;

    @Autowired
    private ApplicationContext appContext;

    public void save(LocationEntity locationEntity) {
        try {
            servicesLocation.save(locationEntity);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public Collection<LocationEntity> findRoutesDetailByIdVehicle(Integer id) {

        List<LocationEntity> locationEntitys = new ArrayList<>();
        locationEntitys = servicesLocation.findByIdVehicle(id);

        return locationEntitys;
    }

    public Collection<LocationEntity> findByVehicleAndDate(VehicleEntity vehicleEntity, LocalDate fecha) {
        Collection<LocationEntity> entityCollection = new ArrayList<>();
        entityCollection = servicesLocation.findByVehicleAndDate(vehicleEntity.getIdvehicle(),fecha);
        return entityCollection;
    }
}
