package com.srb.project.controller;


import com.srb.project.model.RoutesEntity;
import com.srb.project.persister.ServicesRoutes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.Collection;

@Controller
public class ControllerRoutes {

    @Autowired
    ServicesRoutes servicesRoutes;

    public void save(RoutesEntity routes) {
        routes.setStatedelete((byte) 1);
        servicesRoutes.save(routes);
    }

    public RoutesEntity updateRoutes(RoutesEntity routes) {

        boolean updateRoutes = false;
        RoutesEntity routesEntityBd = null;

        try {
            routesEntityBd = servicesRoutes.findById(routes.getIdroutes());
            if (routesEntityBd != null) {
                if (!routes.equals(routesEntityBd)) {
                    servicesRoutes.update(routes);
                    updateRoutes = true;
                }

            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return routes;
    }

    public boolean deleteRoutes(RoutesEntity routes) {

        boolean deleteRoutes = false;
        RoutesEntity routesEntityBd = null;

        try {
            routesEntityBd = servicesRoutes.findById(routes.getIdroutes());
            if (routesEntityBd != null) {
                routesEntityBd.setStatedelete((byte) 0);
                servicesRoutes.delete(routesEntityBd);
                deleteRoutes = true;
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return deleteRoutes;
    }

    public Collection<RoutesEntity> findAllRoutes() {

        Collection<RoutesEntity> routesEntities = new ArrayList<>();
        routesEntities = servicesRoutes.findAllRoutes();

        return routesEntities;
    }
}
