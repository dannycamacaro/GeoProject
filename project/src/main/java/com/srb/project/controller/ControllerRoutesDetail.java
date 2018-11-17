package com.srb.project.controller;


import com.srb.project.enumConstans.EnumOperation;
import com.srb.project.model.AuditsEntity;
import com.srb.project.model.RoutedetailEntity;
import com.srb.project.model.RoutesEntity;
import com.srb.project.persister.ServicesAudit;
import com.srb.project.persister.ServicesRouteDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.Collection;

@Controller
public class ControllerRoutesDetail {

    @Autowired
    ServicesRouteDetail servicesRouteDetail;

    @Autowired
    ServicesAudit servicesAudit;

    @Autowired
    private ApplicationContext appContext;

    public void save(RoutedetailEntity routesDetail) {
        AuditsEntity auditsEntity = new AuditsEntity();

        try {
            auditsEntity = ControllerAudit.loadInformationAudit(routesDetail.toString(), EnumOperation.ADD_ROUTES_DETAIL.getIdOperation(), "controllerLogin", appContext);
            routesDetail.setIdroutes(1); //TODO AJUSTAR CON LA LISTA DE RUTAS
            servicesRouteDetail.save(routesDetail);
            auditsEntity.setStatusoperation(AuditsEntity.OPERATION_SUCCESSFUL);
            servicesAudit.save(auditsEntity);
        } catch (Exception e) {
            e.printStackTrace();
            auditsEntity.setStatusoperation(AuditsEntity.OPERATION_NOT_SUCCESSFUL);
            servicesAudit.save(auditsEntity);
        }
    }

    public RoutedetailEntity updateRoutes(RoutedetailEntity routesDetail) {

        RoutedetailEntity routesEntityBd = null;
        AuditsEntity auditsEntity = ControllerAudit.loadInformationAudit(routesDetail.toString(), EnumOperation.EDIT_ROUTES_DETAIL.getIdOperation(), "controllerLogin", appContext);

        try {

            routesEntityBd = servicesRouteDetail.findById(routesDetail.getIdroutedetail());
            if (routesEntityBd != null) {
                if (!routesDetail.equals(routesEntityBd)) {
                    servicesRouteDetail.update(routesDetail);
                    auditsEntity.setStatusoperation(AuditsEntity.OPERATION_SUCCESSFUL);
                    servicesAudit.save(auditsEntity);
                }

            }

        } catch (Exception e) {
            auditsEntity.setStatusoperation(AuditsEntity.OPERATION_NOT_SUCCESSFUL);
            ;
            servicesAudit.save(auditsEntity);

        }

        return routesDetail;
    }

    public boolean deleteRoutes(RoutedetailEntity routesDetail) {

        AuditsEntity auditsEntity = ControllerAudit.loadInformationAudit(routesDetail.toString(), EnumOperation.DELETE_ROUTES_DETAIL.getIdOperation(), "controllerLogin", appContext);
        boolean deleteRoutes = false;
        RoutedetailEntity routesDetailEntityBd = null;

        try {
//            routesDetailEntityBd = servicesRouteDetail.findById(routesDetail.getIdroutedetail());
//            if (routesDetailEntityBd != null) {
            servicesRouteDetail.delete(routesDetail);
            auditsEntity.setStatusoperation(AuditsEntity.OPERATION_SUCCESSFUL);
            servicesAudit.save(auditsEntity);
            deleteRoutes = true;
//            }

        } catch (Exception e) {
            auditsEntity.setStatusoperation(AuditsEntity.OPERATION_NOT_SUCCESSFUL);
            servicesAudit.save(auditsEntity);
        }

        return deleteRoutes;
    }

    public Collection<RoutedetailEntity> findAllRoutesDetail() {

        Collection<RoutedetailEntity> routesEntities = new ArrayList<>();
        routesEntities = servicesRouteDetail.findAllRoutesDetail();

        return routesEntities;
    }
    public Collection<RoutedetailEntity> findRoutesDetailByIdRoute(Integer id) {
        servicesRouteDetail.findDetailsByIdRoute(id);
        Collection<RoutedetailEntity> routesEntities = new ArrayList<>();
        routesEntities = servicesRouteDetail.findDetailsByIdRoute(id);

        return routesEntities;
    }
}
