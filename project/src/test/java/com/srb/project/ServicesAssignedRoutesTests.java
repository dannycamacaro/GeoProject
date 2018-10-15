package com.srb.project;

import com.srb.project.model.*;
import com.srb.project.persister.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ServicesAssignedRoutesTests {

    @Autowired
    ServicesAssignedRoutes servicesAssignedRoutes;
    @Autowired
    ServicesDevice servicesDevice;
    @Autowired
    ServicesRoutes servicesRoutes;
    @Test
    public void contextLoads() {

        insertAudit();

    }

    private void insertAudit() {
        DeviceEntity deviceEntity = servicesDevice.findById(1);
        RoutesEntity routesEntity = servicesRoutes.findById(1);

        AssignedroutesEntity assignedroutesEntity = new AssignedroutesEntity();
        assignedroutesEntity.setIdroutes(routesEntity.getIdroutes());
        assignedroutesEntity.setIddevice(deviceEntity.getIddevice());
        try {
            servicesAssignedRoutes.save(assignedroutesEntity);
            System.out.println("Insertado correctamente");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Falla en la insercion");
        }
    }





}
