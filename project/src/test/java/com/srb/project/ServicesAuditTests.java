package com.srb.project;

import com.srb.project.enumConstans.EnumOperation;
import com.srb.project.model.AuditsEntity;
import com.srb.project.model.DeviceEntity;
import com.srb.project.model.UsersEntity;
import com.srb.project.persister.ServicesAudit;
import com.srb.project.persister.ServicesUser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ServicesAuditTests {

    @Autowired
    ServicesAudit servicesAudit;
    @Autowired
    ServicesUser servicesUser;
    @Test
    public void contextLoads() {

        insertAudit();

    }

    private void insertAudit() {
        UsersEntity usersEntity = servicesUser.findById(1);

        AuditsEntity auditsEntity = new AuditsEntity();
        auditsEntity.setIdusers(usersEntity.getIdusers());
        auditsEntity.setTypeoperation(EnumOperation.DELETE_DEVICE.getIdOperation());
        auditsEntity.setIp("127.0.0.1");
        try {
            servicesAudit.save(auditsEntity);
            System.out.println("Insertado correctamente");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Falla en la insercion");
        }
    }





}
