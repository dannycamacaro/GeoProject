package com.srb.project;

import com.srb.project.model.LocationEntity;
import com.srb.project.model.VehicleEntity;
import com.srb.project.persister.ServicesLocation;
import com.srb.project.persister.ServicesRol;
import com.srb.project.persister.ServicesVehicle;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ServicesLocationTests {

    @Autowired
    ServicesVehicle servicesVehicle;
    @Autowired
    ServicesLocation servicesLocation;

    @Test
    public void contextLoads() {

//		probarInsertLocation();
        buscarLocacion();


    }

    private void probarInsertLocation() {

        VehicleEntity vehicleEntity = servicesVehicle.findById(50);

        if (vehicleEntity != null) {
            LocationEntity locationEntity = new LocationEntity("100", "300", vehicleEntity.getIdvehicle(), (byte) 0);
            servicesLocation.save(locationEntity.toString());
            System.out.println("Insert Location correcto");
        } else {
            System.out.println("No se logro insertar la locacion");
        }
    }

    private void buscarLocacion() {
        List<LocationEntity> locationEntityList = new ArrayList<>();

        locationEntityList = servicesLocation.findByIdVehicle(1);
        if (locationEntityList.size() > 0) {
            for (LocationEntity locationEntity : locationEntityList) {
                System.out.println(locationEntity);
            }
        } else {
            System.out.println("No hay elementos");
        }
    }

}
