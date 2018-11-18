package com.srb.project.controller;


import com.srb.project.enumConstans.EnumOperation;
import com.srb.project.model.AssignedroutesEntity;
import com.srb.project.model.AuditsEntity;
import com.srb.project.persister.ServicesAssignedRoutes;
import com.srb.project.persister.ServicesAudit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.Collection;

@Controller
public class ControllerAssignedRoutes {

    @Autowired
    ServicesAssignedRoutes servicesAssignedRoutes;

    @Autowired
    ServicesAudit servicesAudit;

    @Autowired
    private ApplicationContext appContext;


    public void save(AssignedroutesEntity assignedRoutesEntity) {
        AuditsEntity auditsEntity = new AuditsEntity();

        try {
            auditsEntity = ControllerAudit.loadInformationAudit(assignedRoutesEntity.toString(), EnumOperation.ADD_ASSIGNEDROUTES.getIdOperation(),"controllerLogin",appContext);
            servicesAssignedRoutes.save(assignedRoutesEntity);
            auditsEntity.setStatusoperation(AuditsEntity.OPERATION_SUCCESSFUL);
            servicesAudit.save(auditsEntity);
        } catch (Exception e) {
            e.printStackTrace();
            auditsEntity.setStatusoperation(AuditsEntity.OPERATION_NOT_SUCCESSFUL);
            servicesAudit.save(auditsEntity);
        }
    }

    public Collection<AssignedroutesEntity> findAllAssignedRoutes() {

        Collection<AssignedroutesEntity> assignedroutesEntities = new ArrayList<>();
        assignedroutesEntities = servicesAssignedRoutes.findAllAssignedRoutes();


        for (AssignedroutesEntity entities :assignedroutesEntities){
            entities.setPhoneNumber(entities.getDeviceByIddevice().getPhonenumber());
            entities.setNameRoute(entities.getRoutesByIdroutes().getNameroutes());
        }

        return assignedroutesEntities;
    }

    public AssignedroutesEntity updateAssigneRoutes(AssignedroutesEntity assignedRoutesEntity) {
        AssignedroutesEntity assignedRoutesEntityBd = null;
        AuditsEntity  auditsEntity = ControllerAudit.loadInformationAudit(assignedRoutesEntity.toString(),EnumOperation.EDIT_ASSIGNEDROUTES.getIdOperation(),"controllerLogin",appContext);


        try {
            assignedRoutesEntityBd = servicesAssignedRoutes.findById(assignedRoutesEntity.getIdassignedroutes());
            if (assignedRoutesEntityBd != null) {
                if (!assignedRoutesEntity.equals(assignedRoutesEntityBd)) {
                    servicesAssignedRoutes.update(assignedRoutesEntity);
                    auditsEntity.setStatusoperation(AuditsEntity.OPERATION_SUCCESSFUL);
                    servicesAudit.save(auditsEntity);
                }

            }

        } catch (Exception e) {
            auditsEntity.setStatusoperation(AuditsEntity.OPERATION_NOT_SUCCESSFUL);;
            servicesAudit.save(auditsEntity);
        }

        return assignedRoutesEntity;
    }

    public boolean deleteAssignedRoutes(AssignedroutesEntity assignedRoutesEntity) {

        boolean deleteRoutes = false;
        AssignedroutesEntity routesEntityBd = null;
        AuditsEntity  auditsEntity = ControllerAudit.loadInformationAudit(assignedRoutesEntity.toString(),EnumOperation.DELETE_ASSIGNEDROUTES.getIdOperation(),"controllerLogin",appContext);

        try {
            routesEntityBd = servicesAssignedRoutes.findById(assignedRoutesEntity.getIdassignedroutes());
            if (routesEntityBd != null) {
                servicesAssignedRoutes.delete(routesEntityBd);
                auditsEntity.setStatusoperation(AuditsEntity.OPERATION_SUCCESSFUL);
                servicesAudit.save(auditsEntity);
                deleteRoutes = true;
            }

        } catch (Exception e) {
            auditsEntity.setStatusoperation(AuditsEntity.OPERATION_NOT_SUCCESSFUL);
            servicesAudit.save(auditsEntity);        }

        return deleteRoutes;
    }

    public Collection<AssignedroutesEntity> findAllAssignedRoutesByIdDevice(int iddevice) {
        return servicesAssignedRoutes.findByIdDevice(iddevice);
    }
}
