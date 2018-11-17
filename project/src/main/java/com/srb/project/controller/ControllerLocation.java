package com.srb.project.controller;

import com.srb.project.model.LocationEntity;
import com.srb.project.model.RoutedetailEntity;
import com.srb.project.persister.ServicesAudit;
import com.srb.project.persister.ServicesLocation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;

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

    public Collection<LocationEntity> findRoutesDetailByIdVehicle(Integer id) {

        List<LocationEntity> locationEntitys = new ArrayList<>();
        locationEntitys = servicesLocation.findByIdVehicle(id);

        return locationEntitys;
    }

}
