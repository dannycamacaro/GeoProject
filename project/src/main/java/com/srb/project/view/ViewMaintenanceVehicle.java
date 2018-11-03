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
public class ViewMaintenanceVehicle extends VerticalLayout implements View {

    public static final String VIEW_NAME = "vehicle";
    private HorizontalSplitCrudLayout horizontalSplitCrudLayout;
    private GridCrud<VehicleEntity> crud;
    private GridLayoutCrudFormFactory<VehicleEntity> formFactory;

    @Autowired
    ControllerVehicle controllerVehicle;

    public ViewMaintenanceVehicle() {
        horizontalSplitCrudLayout = new HorizontalSplitCrudLayout();
        horizontalSplitCrudLayout.setFormCaption(CrudOperation.DELETE,EnumLabel.ELIMINAR_REGISTRO_LABEL.getLabel());
        crud = new GridCrud<>(VehicleEntity.class, horizontalSplitCrudLayout);
        formFactory = new GridLayoutCrudFormFactory<>(VehicleEntity.class, 2, 2);
        buildForm();

    }

    private void buildForm() {
        this.setSizeFull();
        this.setWidth("100%");

        loadSetColumns();
        loadSetVisibleProperties();
        loadSetFieldCaptions();
        loadSetButtonCaption();
        loadMessagesForm();
        actionButtons();

        crud.setCrudFormFactory(formFactory);
        this.addComponent(crud);

    }

    private void loadSetColumns() {

        crud.getGrid().setColumns("mark", "licenseplate", "vehicleyear", "ton");
        crud.getGrid().getColumn("mark").setCaption("Marca");
        crud.getGrid().getColumn("licenseplate").setCaption("Placa");
        crud.getGrid().getColumn("vehicleyear").setCaption("Año");
        crud.getGrid().getColumn("ton").setCaption("Carga");
    }

    private void loadSetVisibleProperties() {
        formFactory.setVisibleProperties(CrudOperation.READ, "mark", "licenseplate", "vehicleyear", "ton");
        formFactory.setVisibleProperties(CrudOperation.ADD, "mark", "licenseplate", "vehicleyear", "ton");
        formFactory.setVisibleProperties(CrudOperation.UPDATE, "mark", "licenseplate", "vehicleyear", "ton");
        formFactory.setVisibleProperties(CrudOperation.DELETE, "mark", "licenseplate", "vehicleyear", "ton");
    }

    private void loadSetFieldCaptions() {
        formFactory.setFieldCaptions(CrudOperation.READ, "Marca del vehiculo", "Placa", "Año del vehiculo", "Carga");
        formFactory.setFieldCaptions(CrudOperation.ADD, "Marca del vehiculo", "Placa", "Año del vehiculo", "Carga");
        formFactory.setFieldCaptions(CrudOperation.UPDATE, "Marca del vehiculo", "Placa", "Año del vehiculo", "Carga");
        formFactory.setFieldCaptions(CrudOperation.DELETE, "Marca del vehiculo", "Placa", "Año del vehiculo", "Carga");
    }

    private void loadSetButtonCaption() {
        formFactory.setButtonCaption(CrudOperation.READ,EnumLabel.ACEPTAR_LABEL.getLabel());
        formFactory.setButtonCaption(CrudOperation.ADD, EnumLabel.REGISTRAR_LABEL.getLabel());
        formFactory.setButtonCaption(CrudOperation.UPDATE, EnumLabel.EDITAR_LABEL.getLabel());
        formFactory.setButtonCaption(CrudOperation.DELETE, EnumLabel.ELIMINAR_LABEL.getLabel());
        formFactory.setCancelButtonCaption(EnumLabel.CANCELAR_LABEL.getLabel());
    }

    private void loadMessagesForm() {

        crud.setSavedMessage(EnumLabel.REGISTRO_SALVADO_LABEL.getLabel());
        crud.setDeletedMessage(EnumLabel.REGISTRO_ELIMINADO_LABEL.getLabel());
        crud.setRowCountCaption(EnumLabel.ROW_COUNT_CAPTION_LABEL.getLabel());
        crud.getFindAllButton().setDescription(EnumLabel.REFRESCAR_LABEL.getLabel());
        crud.getAddButton().setDescription(EnumLabel.REGISTRAR_LABEL.getLabel());
        crud.getUpdateButton().setDescription(EnumLabel.EDITAR_LABEL.getLabel());
        crud.getDeleteButton().setDescription(EnumLabel.ELIMINAR_LABEL.getLabel());
    }

    private void actionButtons() {
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
    }
}
