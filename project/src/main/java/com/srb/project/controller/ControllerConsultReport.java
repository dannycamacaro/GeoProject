package com.srb.project.controller;


import com.srb.project.enumConstans.EnumOperation;
import com.srb.project.model.*;
import com.srb.project.persister.ServicesAudit;
import com.srb.project.persister.ServicesDevice;
import com.srb.project.persister.ServicesRoutes;
import com.srb.project.persister.ServicesVehicle;
import com.srb.project.pojo.ConsultReportAssignedDevice;
import com.srb.project.pojo.ConsultReportAudit;
import com.srb.project.pojo.ConsultReportDetailRoute;
import com.srb.project.pojo.ConsultReportRoutes;
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
    ServicesDevice servicesDevice;

    @Autowired
    ServicesAudit servicesAudit;

    @Autowired
    ServicesRoutes servicesRoutes;

    @Autowired
    private ApplicationContext appContext;


    public Collection<VehicleEntity> findAllVehicleActive() {

        Collection<VehicleEntity> vehicleEntities = new ArrayList<>();
        vehicleEntities = servicesVehicle.findAllVehicle();

        return vehicleEntities;
    }

    public Collection<ConsultReportAssignedDevice> findAssignedDevice() {

        Collection<DeviceEntity> deviceEntityCollection = new ArrayList<>();
        Collection<ConsultReportAssignedDevice> consultReportAssignedDevices = new ArrayList<>();

        deviceEntityCollection = servicesDevice.findAssignedDevice();

        for (DeviceEntity deviceEntity : deviceEntityCollection) {
            ConsultReportAssignedDevice reportAssignedDevice = new ConsultReportAssignedDevice();
            reportAssignedDevice.setNumeroTelefono(deviceEntity.getPhonenumber());
            reportAssignedDevice.setImei(deviceEntity.getImei());
            reportAssignedDevice.setPlaca(deviceEntity.getVehicleByIdvehicle().getLicenseplate());
            reportAssignedDevice.setMarca(deviceEntity.getVehicleByIdvehicle().getMark());
            consultReportAssignedDevices.add(reportAssignedDevice);
        }

        return consultReportAssignedDevices;

    }


    public Collection<ConsultReportAudit> loadAudit() {

        Collection<ConsultReportAudit> consultReportAudits = new ArrayList<>();
        Collection<AuditsEntity> auditsEntities = new ArrayList<>();

        auditsEntities = servicesAudit.findAllAudits();
        for (AuditsEntity auditsEntity : auditsEntities) {
            ConsultReportAudit reportAudit = new ConsultReportAudit();

            reportAudit.setIp(auditsEntity.getIp());
            reportAudit.setNombreUsuario(auditsEntity.getUsersByIdusers().getUsername());
            reportAudit.setFechaOperacion(auditsEntity.getAuditDate().toString());
            reportAudit.setContenido(auditsEntity.getContent());
            reportAudit.setTipoOperacion(getTyperOperation(auditsEntity.getTypeoperation()));
            consultReportAudits.add(reportAudit);
        }

        return consultReportAudits;

    }

    private String getTyperOperation(String typeoperation) {
        for (EnumOperation enumOperation : EnumOperation.values()) {
            if (enumOperation.getIdOperation().equalsIgnoreCase(typeoperation)) {
                return enumOperation.getOperationName();
            }
        }
        return "";
    }


    public Collection loadRoute(RoutesEntity routesEntitySelected) {

        Collection<RoutesEntity> routesEntityCollection = new ArrayList<>();
        Collection<ConsultReportDetailRoute> consultReportDetailRoutes = new ArrayList<>();
        routesEntityCollection = servicesRoutes.findListById(routesEntitySelected.getIdroutes());

        for (RoutesEntity routesEntity : routesEntityCollection) {
            if (routesEntity.getRoutedetailsByIdroutes().size() > 0) {
                for (RoutedetailEntity routedetailEntity : routesEntity.getRoutedetailsByIdroutes()) {
                    ConsultReportDetailRoute reportDetailRute = new ConsultReportDetailRoute();
                    reportDetailRute.setNombreRuta(routesEntity.getNameroutes());
                    reportDetailRute.setNombreDetalleRuta(routedetailEntity.getDescription());
                    reportDetailRute.setCoordenadaLatitud(routedetailEntity.getRoutelatitude());
                    reportDetailRute.setCoordenadaLongitud(routedetailEntity.getRoutelength());
                    consultReportDetailRoutes.add(reportDetailRute);
                }


            }else{
                ConsultReportDetailRoute reportDetailRute = new ConsultReportDetailRoute();
                reportDetailRute.setNombreRuta(routesEntity.getNameroutes());
                consultReportDetailRoutes.add(reportDetailRute);
            }
        }


        return consultReportDetailRoutes;
    }

    public Collection loadAllRoute() {

        Collection<RoutesEntity> routesEntityCollection = new ArrayList<>();
        Collection<ConsultReportRoutes> reportRoutes = new ArrayList<>();
        routesEntityCollection = servicesRoutes.findAllRoutes();

        for (RoutesEntity routesEntity : routesEntityCollection) {
            ConsultReportRoutes consultReportRoutes = new ConsultReportRoutes();
            consultReportRoutes.setNombreRuta(routesEntity.getNameroutes());
            consultReportRoutes.setDescripcion(routesEntity.getDescription());

            reportRoutes.add(consultReportRoutes);
        }


        return reportRoutes;
    }

}
