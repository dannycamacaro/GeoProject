package com.srb.project.controller;


import com.srb.project.model.DeviceEntity;
import com.srb.project.persister.ServicesDevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.Collection;

@Controller
public class ControllerDevice {
    @Autowired
    ServicesDevice servicesDevice;


    public void save(DeviceEntity device) {
        device.setStatedelete((byte) 1);
        device.setIdvehicle(2); // TODO AJUSTAR CON LA LISTA DE LA VISTA
        servicesDevice.save(device);
    }

    public DeviceEntity updateDevice(DeviceEntity device) {

        boolean updateDevice = false;
        DeviceEntity deviceEntityBd = null;

        try {
            deviceEntityBd = servicesDevice.findById(device.getIddevice());
            if (deviceEntityBd != null) {
                if (!device.equals(deviceEntityBd)) {
                    servicesDevice.update(device);
                    updateDevice = true;
                }

            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return device;
    }

    public boolean deleteDevice(DeviceEntity device) {

        boolean deleteDevice = false;
        DeviceEntity deviceEntityBd = null;

        try {
            deviceEntityBd = servicesDevice.findById(device.getIddevice());
            if (deviceEntityBd != null) {
                deviceEntityBd.setStatedelete((byte) 0);
                servicesDevice.delete(deviceEntityBd);
                deleteDevice = true;
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return deleteDevice;
    }

    public Collection<DeviceEntity> findAllDevice() {

        Collection<DeviceEntity> deviceEntities = new ArrayList<>();
        deviceEntities = servicesDevice.findAllDevice();

        return deviceEntities;
    }
}
