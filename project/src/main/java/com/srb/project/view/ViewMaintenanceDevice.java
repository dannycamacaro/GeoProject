package com.srb.project.view;


import com.srb.project.controller.ControllerDevice;
import com.srb.project.controller.ControllerRoutes;
import com.srb.project.enumConstans.EnumLabel;
import com.srb.project.model.DeviceEntity;
import com.vaadin.navigator.View;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.VerticalLayout;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.crudui.crud.CrudListener;
import org.vaadin.crudui.crud.CrudOperation;
import org.vaadin.crudui.crud.impl.GridCrud;
import org.vaadin.crudui.form.impl.form.factory.GridLayoutCrudFormFactory;
import org.vaadin.crudui.layout.impl.HorizontalSplitCrudLayout;

import java.util.Collection;

@UIScope
@SpringView(name = ViewMaintenanceDevice.VIEW_NAME)
public class ViewMaintenanceDevice extends VerticalLayout implements View {

    public static final String VIEW_NAME = "dispositivos";

    @Autowired
    ControllerDevice controllerDevice;

    public ViewMaintenanceDevice() {
        buildForm();

    }

    private void buildForm() {
        this.setSizeFull();
        this.setWidth("100%");

        FormLayout formDevice = new FormLayout();
        GridCrud<DeviceEntity> crud = new GridCrud<>(DeviceEntity.class, new HorizontalSplitCrudLayout());
        GridLayoutCrudFormFactory<DeviceEntity> formFactory = new GridLayoutCrudFormFactory<>(DeviceEntity.class, 2, 2);

        crud.getGrid().setColumns("mark","model","imei","phonenumber");
        crud.getGrid().getColumn("mark").setCaption("Marca");
        crud.getGrid().getColumn("model").setCaption("Modelo");
        crud.getGrid().getColumn("imei").setCaption("IMEI");
        crud.getGrid().getColumn("phonenumber").setCaption("Numero de telefono");


        formFactory.setVisibleProperties(CrudOperation.READ, "mark","model","imei","phonenumber");
        formFactory.setVisibleProperties(CrudOperation.ADD, "mark","model","imei","phonenumber");
        formFactory.setVisibleProperties(CrudOperation.UPDATE, "mark","model","imei","phonenumber");
        formFactory.setVisibleProperties(CrudOperation.DELETE, "mark","model","imei","phonenumber");
        formFactory.setFieldCaptions(CrudOperation.ADD, "Marca","Modelo","IMEI","Numero de telefono");
        formFactory.setFieldCaptions(CrudOperation.UPDATE, "Marca","Modelo","IMEI","Numero de telefono");
        formFactory.setFieldCaptions(CrudOperation.DELETE, "Marca","Modelo","IMEI","Numero de telefono");
//        formFactory.setFieldCaptions(CrudOperation.UPDATE, "Nombre del usuario","Password", "Nombres","Apellidos","Documento de identidad","Edad","Numero de telefono","Correo electronico");
//        formFactory.setFieldCaptions(CrudOperation.DELETE, "Nombre del usuario", "Nombres","Apellidos");
        formFactory.setButtonCaption(CrudOperation.ADD, EnumLabel.REGISTRAR_LABEL.getLabel());
        formFactory.setButtonCaption(CrudOperation.UPDATE, EnumLabel.EDITAR_LABEL.getLabel());
        formFactory.setButtonCaption(CrudOperation.DELETE, EnumLabel.ELIMINAR_LABEL.getLabel());
//        formFactory.setFieldType("password", PasswordField.class);


        crud.setCrudListener(new CrudListener<DeviceEntity>() {
            @Override
            public Collection<DeviceEntity> findAll() {
                return controllerDevice.findAllDevice();
            }

            @Override
            public DeviceEntity add(DeviceEntity device) {
                controllerDevice.save(device);
                return device;
            }

            @Override
            public DeviceEntity update(DeviceEntity device) {

                controllerDevice.updateDevice(device);
                return device;
            }

            @Override
            public void delete(DeviceEntity device) {
                controllerDevice.deleteDevice(device);
            }
        });

        crud.setCrudFormFactory(formFactory);
        this.addComponent(crud);


    }


}
