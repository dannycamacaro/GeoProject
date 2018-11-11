package com.srb.project.controller;

import com.srb.project.model.AuditsEntity;
import com.vaadin.server.Page;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;

@Controller
public class ControllerAudit {

    public static AuditsEntity loadInformationAudit(String beanString, String operation, String nameBean, ApplicationContext appContext) {
        AuditsEntity auditsEntity = new AuditsEntity();
        String address = Page.getCurrent().getWebBrowser().getAddress();
        auditsEntity.setIp(address);
        switch (nameBean) {
            case "controllerLogin":
                ControllerLogin controller = (ControllerLogin) appContext.getBean("controllerLogin");
                if (controller != null && controller.getUsersEntity() != null)

                {
                    auditsEntity.setIdusers(controller.getUsersEntity().getIdusers());
                }
                break;

        }
        auditsEntity.setContent(beanString);


        auditsEntity.setTypeoperation(operation);
        return auditsEntity;
    }
}
