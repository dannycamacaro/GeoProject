package com.srb.project;

import com.srb.project.model.LocationEntity;
import com.srb.project.model.RolesEntity;
import com.srb.project.model.VehicleEntity;
import com.srb.project.persister.ServicesLocation;
import com.srb.project.persister.ServicesRol;
import com.srb.project.persister.ServicesVehicle;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProjectApplicationTests {
	@Autowired
	ServicesRol servicesRol;

	@Autowired
	ServicesVehicle servicesVehicle;
	@Autowired
	ServicesLocation servicesLocation;
	@Test
	public void contextLoads() {


		/*RolesEntity rolEntity= new RolesEntity();

		rolEntity.setNamerole("CIMUN");
		rolEntity.setDescriptionrole("valad");
		if(servicesRol.loadRolByName(rolEntity.getNamerole())>0){
			System.out.println("true");
		}*/
	}




}
