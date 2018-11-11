package com.srb.project.controller;


import com.srb.project.enumConstans.EnumOperation;
import com.srb.project.model.AuditsEntity;
import com.srb.project.model.DeviceEntity;
import com.srb.project.persister.ServicesAudit;
import com.srb.project.persister.ServicesDevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.Collection;

@Controller
public class ControllerDevice {
    @Autowired
    ServicesDevice servicesDevice;

    @Autowired
    ServicesAudit servicesAudit;

    @Autowired
    private ApplicationContext appContext;

    public void save(DeviceEntity device) {
        AuditsEntity auditsEntity = new AuditsEntity();
        try {
            auditsEntity = ControllerAudit.loadInformationAudit(device.toString(), EnumOperation.ADD_DEVICE.getIdOperation(), "controllerLogin", appContext);

            device.setStatedelete((byte) 1);
            device.setIdvehicle(2); // TODO AJUSTAR CON LA LISTA DE LA VISTA
            servicesDevice.save(device);
            auditsEntity.setStatusoperation(AuditsEntity.OPERATION_SUCCESSFUL);
            servicesAudit.save(auditsEntity);
        } catch (Exception e) {
            e.printStackTrace();
            auditsEntity.setStatusoperation(AuditsEntity.OPERATION_NOT_SUCCESSFUL);
            servicesAudit.save(auditsEntity);
        }

    }

    public DeviceEntity updateDevice(DeviceEntity device) {

        DeviceEntity deviceEntityBd = null;
        AuditsEntity auditsEntity = ControllerAudit.loadInformationAudit(device.toString(), EnumOperation.EDIT_DEVICE.getIdOperation(), "controllerLogin", appContext);

        try {
            deviceEntityBd = servicesDevice.findById(device.getIddevice());
            if (deviceEntityBd != null) {
                if (!device.equals(deviceEntityBd)) {
                    servicesDevice.update(device);
                    auditsEntity.setStatusoperation(AuditsEntity.OPERATION_SUCCESSFUL);
                    servicesAudit.save(auditsEntity);
                }

            }

        } catch (Exception e) {
            auditsEntity.setStatusoperation(AuditsEntity.OPERATION_NOT_SUCCESSFUL);
            ;
            servicesAudit.save(auditsEntity);
        }

        return device;
    }

    public boolean deleteDevice(DeviceEntity device) {

        boolean deleteDevice = false;
        DeviceEntity deviceEntityBd = null;
        AuditsEntity auditsEntity = ControllerAudit.loadInformationAudit(device.toString(), EnumOperation.DELETE_DEVICE.getIdOperation(), "controllerLogin", appContext);
        try {
            deviceEntityBd = servicesDevice.findById(device.getIddevice());
            if (deviceEntityBd != null) {
                deviceEntityBd.setStatedelete((byte) 0);
                servicesDevice.delete(deviceEntityBd);
                auditsEntity.setStatusoperation(AuditsEntity.OPERATION_SUCCESSFUL);
                servicesAudit.save(auditsEntity);
                deleteDevice = true;
            }

        } catch (Exception e) {
            auditsEntity.setStatusoperation(AuditsEntity.OPERATION_NOT_SUCCESSFUL);
            servicesAudit.save(auditsEntity);
        }

        return deleteDevice;
    }

    public Collection<DeviceEntity> findAllDevice() {

        Collection<DeviceEntity> deviceEntities = new ArrayList<>();
        deviceEntities = servicesDevice.findAllDevice();

        return deviceEntities;
    }
}
