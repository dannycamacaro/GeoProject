package com.srb.project;

import com.srb.project.model.DeviceEntity;
import com.srb.project.model.VehicleEntity;
import com.srb.project.persister.ServicesDevice;
import com.srb.project.persister.ServicesVehicle;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ServicesDeviceTests {


    @Autowired
    ServicesDevice servicesDevice;

    @Autowired
    ServicesVehicle servicesVehicle;

    @Test
    public void contextLoads() {

//        insertDevice();
        deleteDevice();
    }

    private void insertDevice() {
        VehicleEntity vehicleEntity = servicesVehicle.findById(1);

        DeviceEntity deviceEntity = new DeviceEntity();
        deviceEntity.setMark("AAA");
        deviceEntity.setIdvehicle(vehicleEntity.getIdvehicle());
        deviceEntity.setPhonenumber("123456789");
        deviceEntity.setStatedelete((byte) 0);

        try {
            servicesDevice.save(deviceEntity);
            System.out.println("Insertado correctamente");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Falla en la insercion");
        }
    }

    private void deleteDevice() {
        DeviceEntity deviceEntity = servicesDevice.findById(1);

        if (deviceEntity != null) {
            deviceEntity.setStatedelete((byte) 1);
            servicesDevice.delete(deviceEntity);
            System.out.println("Eliminado correctamente");
        } else {
            System.out.println("Falla en la eliminacion");
        }

    }


}
