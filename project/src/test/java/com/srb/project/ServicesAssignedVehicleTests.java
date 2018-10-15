package com.srb.project;

import com.srb.project.model.AssignedvehicleEntity;
import com.srb.project.model.UsersEntity;
import com.srb.project.model.VehicleEntity;
import com.srb.project.persister.ServicesAssignedVehicle;
import com.srb.project.persister.ServicesUser;
import com.srb.project.persister.ServicesVehicle;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Calendar;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ServicesAssignedVehicleTests {

    @Autowired
    ServicesAssignedVehicle servicesAssignedVehicle;
    @Autowired
    ServicesUser servicesUser;
    @Autowired
    ServicesVehicle servicesVehicle;
    @Test
    public void contextLoads() {

        insertAudit();

    }

    private void insertAudit() {
        UsersEntity usersEntity = servicesUser.findById(1);
        VehicleEntity vehicleEntity = servicesVehicle.findById(1);
        Date dateInit = new Date();
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, 1);


        AssignedvehicleEntity assignedvehicleEntity = new AssignedvehicleEntity();
        assignedvehicleEntity.setIdusers(usersEntity.getIdusers());
        assignedvehicleEntity.setIdvehicle(vehicleEntity.getIdvehicle());
        assignedvehicleEntity.setInitialdate(dateInit);
        date = calendar.getTime();
        assignedvehicleEntity.setFinishdate(date);


        try {
            servicesAssignedVehicle.save(assignedvehicleEntity);
            System.out.println("Insertado correctamente");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Falla en la insercion");
        }
    }





}
