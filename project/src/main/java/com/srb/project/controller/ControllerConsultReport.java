package com.srb.project.controller;


import com.srb.project.enumConstans.EnumOperation;
import com.srb.project.model.AuditsEntity;
import com.srb.project.model.DeviceEntity;
import com.srb.project.model.VehicleEntity;
import com.srb.project.persister.ServicesAudit;
import com.srb.project.persister.ServicesDevice;
import com.srb.project.persister.ServicesVehicle;
import com.srb.project.pojo.ConsultReportAssignedDevice;
import com.srb.project.pojo.ConsultReportAudit;
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

        for(DeviceEntity deviceEntity : deviceEntityCollection){
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


}
