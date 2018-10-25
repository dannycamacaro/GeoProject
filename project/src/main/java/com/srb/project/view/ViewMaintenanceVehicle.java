package com.srb.project.view;


import com.srb.project.controller.ControllerVehicle;
import com.srb.project.enumConstans.EnumLabel;
import com.srb.project.model.VehicleEntity;
import com.vaadin.navigator.View;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.VerticalLayout;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.crudui.crud.CrudListener;
import org.vaadin.crudui.crud.CrudOperation;
import org.vaadin.crudui.crud.impl.GridCrud;
import org.vaadin.crudui.form.impl.form.factory.GridLayoutCrudFormFactory;
import org.vaadin.crudui.layout.impl.HorizontalSplitCrudLayout;

import java.util.Collection;

@UIScope
@SpringView(name = ViewMaintenanceVehicle.VIEW_NAME)
public class ViewMaintenanceVehicle  extends VerticalLayout implements View {

    public static final String VIEW_NAME = "vehicle";

    @Autowired
    ControllerVehicle controllerVehicle;

    public ViewMaintenanceVehicle() {
        buildForm();

    }

    private void buildForm() {
        this.setSizeFull();
        this.setWidth("100%");

        FormLayout formVehicle = new FormLayout();
        GridCrud<VehicleEntity> crud = new GridCrud<>(VehicleEntity.class, new HorizontalSplitCrudLayout());
        GridLayoutCrudFormFactory<VehicleEntity> formFactory = new GridLayoutCrudFormFactory<>(VehicleEntity.class, 2, 2);

        crud.getGrid().setColumns("mark","licenseplate","vehicleyear","ton");
        crud.getGrid().getColumn("mark").setCaption("Marca");
        crud.getGrid().getColumn("licenseplate").setCaption("Placa");
        crud.getGrid().getColumn("vehicleyear").setCaption("Año");
        crud.getGrid().getColumn("ton").setCaption("Tonelada");


        formFactory.setVisibleProperties(CrudOperation.READ, "mark","licenseplate","vehicleyear","ton");
        formFactory.setVisibleProperties(CrudOperation.ADD, "mark","licenseplate","vehicleyear","ton");
        formFactory.setVisibleProperties(CrudOperation.UPDATE, "mark","licenseplate","vehicleyear","ton");
        formFactory.setVisibleProperties(CrudOperation.DELETE, "mark","licenseplate","vehicleyear","ton");
//        formFactory.setFieldCaptions(CrudOperation.ADD, "Nombre del usuario","Password", "Nombres","Apellidos","Documento de identidad","Edad","Numero de telefono","Correo electronico");
//        formFactory.setFieldCaptions(CrudOperation.UPDATE, "Nombre del usuario","Password", "Nombres","Apellidos","Documento de identidad","Edad","Numero de telefono","Correo electronico");
//        formFactory.setFieldCaptions(CrudOperation.DELETE, "Nombre del usuario", "Nombres","Apellidos");
        formFactory.setButtonCaption(CrudOperation.ADD, EnumLabel.REGISTRAR_LABEL.getLabel());
        formFactory.setButtonCaption(CrudOperation.UPDATE, EnumLabel.EDITAR_LABEL.getLabel());
        formFactory.setButtonCaption(CrudOperation.DELETE, EnumLabel.ELIMINAR_LABEL.getLabel());
//        formFactory.setFieldType("password", PasswordField.class);


        crud.setCrudListener(new CrudListener<VehicleEntity>() {
            @Override
            public Collection<VehicleEntity> findAll() {
                return controllerVehicle.findAllVehicle();
            }

            @Override
            public VehicleEntity add(VehicleEntity vehicle) {
                controllerVehicle.save(vehicle);
                return vehicle;
            }

            @Override
            public VehicleEntity update(VehicleEntity vehicle) {

                controllerVehicle.updateVehicle(vehicle);
                return vehicle;
            }

            @Override
            public void delete(VehicleEntity vehicle) {
                controllerVehicle.deleteVehicle(vehicle);
            }
        });

        crud.setCrudFormFactory(formFactory);
        this.addComponent(crud);


    }


}
