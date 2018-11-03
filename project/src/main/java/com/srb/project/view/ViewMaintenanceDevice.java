package com.srb.project.view;


import com.srb.project.controller.ControllerDevice;
import com.srb.project.enumConstans.EnumLabel;
import com.srb.project.model.DeviceEntity;
import com.srb.project.model.VehicleEntity;
import com.srb.project.persister.ServicesVehicle;
import com.vaadin.navigator.View;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.VerticalLayout;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.crudui.crud.CrudListener;
import org.vaadin.crudui.crud.CrudOperation;
import org.vaadin.crudui.crud.impl.GridCrud;
import org.vaadin.crudui.form.impl.form.factory.GridLayoutCrudFormFactory;
import org.vaadin.crudui.layout.impl.HorizontalSplitCrudLayout;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

@UIScope
@SpringView(name = ViewMaintenanceDevice.VIEW_NAME)
public class ViewMaintenanceDevice extends VerticalLayout implements View {

    public static final String VIEW_NAME = "dispositivos";
    private HorizontalSplitCrudLayout horizontalSplitCrudLayout;
    private GridCrud<DeviceEntity> crud;
    private GridLayoutCrudFormFactory<DeviceEntity> formFactory;
    @Autowired
    ControllerDevice controllerDevice;
    @Autowired
    ServicesVehicle servicesVehicle;
    Collection vehicles;

    @PostConstruct
    void init() {

        formFactory.setFieldType("nameVehicle", com.vaadin.ui.ComboBox.class);
        vehicles = servicesVehicle.findAllVehicle();
        ArrayList<String> stringArrayList = new ArrayList<>();

        formFactory.setFieldProvider("nameVehicle", () -> new ComboBox("nameVehicle", stringArrayList));
        formFactory.setFieldCreationListener("nameVehicle", field -> {
            com.vaadin.ui.ComboBox comboBox = (com.vaadin.ui.ComboBox) field;
            Iterator iterator = vehicles.iterator();
            stringArrayList.clear();

            while (iterator.hasNext()) {
                VehicleEntity vehicleEntity = (VehicleEntity) iterator.next();
                stringArrayList.add(vehicleEntity.getLicenseplate());

            }
            comboBox.setItems(stringArrayList);
        });

    }

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
//        crud.getGrid().getColumn("nameVehicle").setCaption("Vehiculo asociado");

    }

    private void loadSetVisibleProperties() {
        formFactory.setVisibleProperties(CrudOperation.READ, "mark", "model", "imei", "phonenumber");
        formFactory.setVisibleProperties(CrudOperation.ADD, "mark", "model", "imei", "phonenumber","nameVehicle");
        formFactory.setVisibleProperties(CrudOperation.UPDATE, "mark", "model", "imei", "phonenumber","nameVehicle");
        formFactory.setVisibleProperties(CrudOperation.DELETE, "mark", "model", "imei", "phonenumber");
    }

    private void loadSetFieldCaptions() {
        formFactory.setFieldCaptions(CrudOperation.READ, "Marca", "Modelo", "IMEI", "Numero de telefono");
        formFactory.setFieldCaptions(CrudOperation.ADD, "Marca", "Modelo", "IMEI", "Numero de telefono","Vehiculo");
        formFactory.setFieldCaptions(CrudOperation.UPDATE, "Marca", "Modelo", "IMEI", "Numero de telefono","Vehiculo");
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
