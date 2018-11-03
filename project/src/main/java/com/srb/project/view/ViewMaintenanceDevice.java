package com.srb.project.view;


import com.srb.project.controller.ControllerDevice;
import com.srb.project.enumConstans.EnumLabel;
import com.srb.project.model.DeviceEntity;
import com.vaadin.navigator.View;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
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
    private HorizontalSplitCrudLayout horizontalSplitCrudLayout;
    private GridCrud<DeviceEntity> crud;
    private GridLayoutCrudFormFactory<DeviceEntity> formFactory;
    @Autowired
    ControllerDevice controllerDevice;

    public ViewMaintenanceDevice() {
        horizontalSplitCrudLayout = new HorizontalSplitCrudLayout();
        horizontalSplitCrudLayout.setFormCaption(CrudOperation.DELETE, EnumLabel.ELIMINAR_REGISTRO_LABEL.getLabel());
        crud = new GridCrud<>(DeviceEntity.class, horizontalSplitCrudLayout);
        formFactory = new GridLayoutCrudFormFactory<>(DeviceEntity.class, 2, 2);
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

        crud.getGrid().setColumns("mark", "model", "imei", "phonenumber");
        crud.getGrid().getColumn("mark").setCaption("Marca");
        crud.getGrid().getColumn("model").setCaption("Modelo");
        crud.getGrid().getColumn("imei").setCaption("IMEI");
        crud.getGrid().getColumn("phonenumber").setCaption("Numero de telefono");

    }

    private void loadSetVisibleProperties() {
        formFactory.setVisibleProperties(CrudOperation.READ, "mark", "model", "imei", "phonenumber");
        formFactory.setVisibleProperties(CrudOperation.ADD, "mark", "model", "imei", "phonenumber");
        formFactory.setVisibleProperties(CrudOperation.UPDATE, "mark", "model", "imei", "phonenumber");
        formFactory.setVisibleProperties(CrudOperation.DELETE, "mark", "model", "imei", "phonenumber");
    }

    private void loadSetFieldCaptions() {
        formFactory.setFieldCaptions(CrudOperation.READ, "Marca", "Modelo", "IMEI", "Numero de telefono");
        formFactory.setFieldCaptions(CrudOperation.ADD, "Marca", "Modelo", "IMEI", "Numero de telefono");
        formFactory.setFieldCaptions(CrudOperation.UPDATE, "Marca", "Modelo", "IMEI", "Numero de telefono");
        formFactory.setFieldCaptions(CrudOperation.DELETE, "Marca", "Modelo", "IMEI", "Numero de telefono");
    }

    private void loadSetButtonCaption() {
        formFactory.setButtonCaption(CrudOperation.READ, EnumLabel.ACEPTAR_LABEL.getLabel());
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
    }

}
