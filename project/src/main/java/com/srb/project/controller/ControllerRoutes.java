package com.srb.project.controller;


import com.srb.project.enumConstans.EnumOperation;
import com.srb.project.model.AuditsEntity;
import com.srb.project.model.RoutesEntity;
import com.srb.project.persister.ServicesAudit;
import com.srb.project.persister.ServicesRoutes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.Collection;

@Controller
public class ControllerRoutes {

    @Autowired
    ServicesRoutes servicesRoutes;
    @Autowired
    ServicesAudit servicesAudit;

    @Autowired
    private ApplicationContext appContext;

    public void save(RoutesEntity routes) {
        AuditsEntity auditsEntity = new AuditsEntity();
        try {
            auditsEntity = ControllerAudit.loadInformationAudit(routes.toString(), EnumOperation.ADD_ROUTES.getIdOperation(),"controllerLogin",appContext);
            routes.setStatedelete((byte) 1);
            servicesRoutes.save(routes);
            auditsEntity.setStatusoperation(AuditsEntity.OPERATION_SUCCESSFUL);
            servicesAudit.save(auditsEntity);
        } catch (Exception e) {
            e.printStackTrace();
            auditsEntity.setStatusoperation(AuditsEntity.OPERATION_NOT_SUCCESSFUL);
            servicesAudit.save(auditsEntity);
        }
    }

    public RoutesEntity updateRoutes(RoutesEntity routes) {

        RoutesEntity routesEntityBd = null;
        AuditsEntity  auditsEntity = ControllerAudit.loadInformationAudit(routes.toString(),EnumOperation.EDIT_ROUTES.getIdOperation(),"controllerLogin",appContext);


        try {
            routesEntityBd = servicesRoutes.findById(routes.getIdroutes());
            if (routesEntityBd != null) {
                if (!routes.equals(routesEntityBd)) {
                    servicesRoutes.update(routes);
                    auditsEntity.setStatusoperation(AuditsEntity.OPERATION_SUCCESSFUL);
                    servicesAudit.save(auditsEntity);
                }

            }

        } catch (Exception e) {
            auditsEntity.setStatusoperation(AuditsEntity.OPERATION_NOT_SUCCESSFUL);;
            servicesAudit.save(auditsEntity);
        }

        return routes;
    }

    public boolean deleteRoutes(RoutesEntity routes) {

        boolean deleteRoutes = false;
        RoutesEntity routesEntityBd = null;
        AuditsEntity  auditsEntity = ControllerAudit.loadInformationAudit(routes.toString(),EnumOperation.DELETE_ROUTES.getIdOperation(),"controllerLogin",appContext);

        try {
            routesEntityBd = servicesRoutes.findById(routes.getIdroutes());
            if (routesEntityBd != null) {
                routesEntityBd.setStatedelete((byte) 0);
                servicesRoutes.delete(routesEntityBd);
                auditsEntity.setStatusoperation(AuditsEntity.OPERATION_SUCCESSFUL);
                servicesAudit.save(auditsEntity);
                deleteRoutes = true;
            }

        } catch (Exception e) {
            auditsEntity.setStatusoperation(AuditsEntity.OPERATION_NOT_SUCCESSFUL);
            servicesAudit.save(auditsEntity);
        }

        return deleteRoutes;
    }

    public Collection<RoutesEntity> findAllRoutes() {

        Collection<RoutesEntity> routesEntities = new ArrayList<>();
        routesEntities = servicesRoutes.findAllRoutes();

        return routesEntities;
    }
    public RoutesEntity findRouteById(Integer idRoute) {
        RoutesEntity routesEntity = new RoutesEntity();
        routesEntity = servicesRoutes.findById(idRoute);

        return routesEntity;
    }

    public boolean validateRoutes(String nameroutes) {

        boolean expresion = false;
        if (!existRoutesByName(nameroutes)) {
            expresion = true;
        }
        return expresion;
    }

    private boolean existRoutesByName(String nameroutes) {

        boolean existe = false;
        if (servicesRoutes.loadRouteByName(nameroutes) > 0) {
            existe = true;
        }
        return existe;
    }

}
