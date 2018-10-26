package com.srb.project.controller;


import com.srb.project.model.RoutedetailEntity;
import com.srb.project.model.RoutesEntity;
import com.srb.project.persister.ServicesRouteDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.Collection;

@Controller
public class ControllerRoutesDetail {

    @Autowired
    ServicesRouteDetail servicesRouteDetail;

    public void save(RoutedetailEntity routesDetail) {
        routesDetail.setIdroutes(1); //TODO AJUSTAR CON LA LISTA DE RUTAS
        servicesRouteDetail.save(routesDetail);
    }

    public RoutedetailEntity updateRoutes(RoutedetailEntity routesDetail) {

        boolean updateRoutesDetail = false;
        RoutedetailEntity routesEntityBd = null;

        try {
            routesEntityBd = servicesRouteDetail.findById(routesDetail.getIdroutedetail());
            if (routesEntityBd != null) {
                if (!routesDetail.equals(routesEntityBd)) {
                    servicesRouteDetail.update(routesDetail);
                    updateRoutesDetail = true;
                }

            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return routesDetail;
    }

    public boolean deleteRoutes(RoutedetailEntity routesDetail) {

        boolean deleteRoutes = false;
        RoutedetailEntity routesDetailEntityBd = null;

        try {
//            routesDetailEntityBd = servicesRouteDetail.findById(routesDetail.getIdroutedetail());
//            if (routesDetailEntityBd != null) {
                servicesRouteDetail.delete(routesDetail);
                deleteRoutes = true;
//            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return deleteRoutes;
    }

    public Collection<RoutedetailEntity> findAllRoutesDetail() {

        Collection<RoutedetailEntity> routesEntities = new ArrayList<>();
        routesEntities = servicesRouteDetail.findAllRoutesDetail();

        return routesEntities;
    }
}
