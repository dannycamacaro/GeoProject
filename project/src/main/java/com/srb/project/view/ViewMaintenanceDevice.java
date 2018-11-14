package com.srb.project.view;


import com.srb.project.controller.ControllerDevice;
import com.srb.project.controller.ControllerVehicle;
import com.srb.project.enumConstans.EnumLabel;
import com.srb.project.model.DeviceEntity;
import com.srb.project.model.RoutedetailEntity;
import com.srb.project.model.VehicleEntity;
import com.srb.project.navigator.UniverseNavigator;
import com.srb.project.persister.ServicesVehicle;
import com.vaadin.data.provider.DataProvider;
import com.vaadin.data.provider.ListDataProvider;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.VaadinRequest;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.*;
import com.vaadin.ui.components.grid.ItemClickListener;
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

    @Autowired
    ControllerDevice controllerDevice;
    @Autowired
    ControllerVehicle controllerVehicle;

    Collection<VehicleEntity> collectionVehicles;
    Collection<String> plateVehicles = new ArrayList<>();
    Collection<DeviceEntity> collectionDevice;

    MenuBar mainMenu;

    // componentes de la vista

    TextField txtMark = new TextField("Marca");
    TextField txtModel = new TextField("Modelo");
    TextField txtImei = new TextField("IMEI");
    TextField txtPhoneNumber = new TextField("N° Telefono");
    ComboBox<String> cmbVehicle = new ComboBox<>("Vehiculo");

    //Botones

    Button btnSave = new Button(EnumLabel.REGISTRAR_LABEL.getLabel());
    Button btnEdit = new Button(EnumLabel.EDITAR_LABEL.getLabel());
    Button btnDelete = new Button(EnumLabel.ELIMINAR_LABEL.getLabel());

    Button btnAccept = new Button("Aceptar");
    Button btnCancel = new Button("Cancelar");

    VerticalLayout leftPanel = new VerticalLayout();
    VerticalLayout rightPanel = new VerticalLayout();

    HorizontalLayout contenedor = new HorizontalLayout();
    HorizontalLayout buttonsPanel = new HorizontalLayout();
    HorizontalLayout infoPanel = new HorizontalLayout();
    HorizontalLayout infoPanel2 = new HorizontalLayout();
    HorizontalLayout infoPanel3 = new HorizontalLayout();
    HorizontalLayout buttonLayout2 = new HorizontalLayout();

    private ListDataProvider<DeviceEntity> dataProvider;
    private Grid<DeviceEntity> grid;

    String action="";

    @PostConstruct
    void init() {
        hideContent();
        this.setSizeFull();
        VerticalLayout menuPanel = new VerticalLayout();
        if (mainMenu == null) {
            mainMenu = ViewMenu.buildMenu();
        }
        menuPanel.addComponents(mainMenu);
        menuPanel.setComponentAlignment(mainMenu, Alignment.TOP_CENTER);
        menuPanel.setHeight("100%");
        menuPanel.setHeight("30px");
        menuPanel.setMargin(new MarginInfo(false, false, false, false));

        loadAllData();

        buildLeftPanel();
        buildRightPanel();
        this.addComponent(menuPanel);
        contenedor.setSizeFull();
        this.addComponent(contenedor);
        this.setSizeUndefined();
    }

    private void buildRightPanel() {
        rightPanel.setSpacing(true);
        rightPanel.setSizeFull();
        buttonsPanel = new HorizontalLayout();
        buttonsPanel.setHeight("50px");
        buttonsPanel.addComponents(btnSave, btnEdit, btnDelete, cmbVehicle);

        btnSave.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
            showContent();
            action = "new";
            }
        });

        btnDelete.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                showContent();
                action = "new";
            }
        });

        btnEdit.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                showContent();
                action = "new";
            }
        });

        buttonsPanel.setSpacing(false);
        buttonsPanel.setMargin(new MarginInfo(false, false, false, false));

        for (VehicleEntity vehicles : collectionVehicles) {
            plateVehicles.add(vehicles.getLicenseplate());
        }

        cmbVehicle.setItems(plateVehicles);

        rightPanel.addComponent(buttonsPanel);
        infoPanel.addComponents(txtMark, txtModel);
        infoPanel.setSizeUndefined();
        infoPanel2.addComponents(txtImei, txtPhoneNumber);
        infoPanel2.setSizeUndefined();
        infoPanel3.addComponents(cmbVehicle);
        infoPanel3.setSizeUndefined();

        rightPanel.addComponents(infoPanel);
        rightPanel.addComponents(infoPanel2);
        rightPanel.addComponents(infoPanel3);

        buttonLayout2.addComponents(btnCancel, btnAccept);

        btnAccept.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                if (action.equalsIgnoreCase("new")) {
                    processAddDevice();
                } else if (action.equalsIgnoreCase("edit")) {
                    processUpdateDevice();
                } else if (action.equalsIgnoreCase("delete")) {
                    processDeleteDevice();
                }
            }
        });

        btnCancel.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                hideContent();
                action = "new";
            }
        });


        rightPanel.addComponents(buttonLayout2);

        rightPanel.setComponentAlignment(buttonsPanel, Alignment.MIDDLE_LEFT);
        rightPanel.setComponentAlignment(buttonLayout2, Alignment.BOTTOM_LEFT);
        contenedor.addComponent(rightPanel);
    }

    private void processDeleteDevice() {
    }

    private void processUpdateDevice() {
    }

    private void processAddDevice() {
        DeviceEntity deviceEntity = new DeviceEntity();
        deviceEntity.setStatedelete(Byte.valueOf("1"));
        deviceEntity.setImei(txtImei.getValue());
        deviceEntity.setMark(txtMark.getValue());
        deviceEntity.setModel(txtModel.getValue());
        deviceEntity.setPhonenumber(txtPhoneNumber.getValue());
        for (VehicleEntity vehicle:collectionVehicles) {
            if (vehicle.getLicenseplate().equalsIgnoreCase(cmbVehicle.getValue())){
                deviceEntity.setIdvehicle(vehicle.getIdvehicle());
                deviceEntity.setIdvehicle(vehicle.getIdvehicle());
                deviceEntity.setVehicleByIdvehicle(vehicle);
                break;
            }
        }
        try{
            controllerDevice.save(deviceEntity);
            hideContent();
            Notification.show("Guardado Exitoso", Notification.Type.HUMANIZED_MESSAGE);
            refresGrid();
        }catch (Exception e){
            Notification.show("Ocurrio un Error", Notification.Type.HUMANIZED_MESSAGE);
            hideContent();
        }
    }

    private void refresGrid() {
        collectionDevice = controllerDevice.findAllDevice();
        dataProvider = DataProvider.ofCollection(collectionDevice);
        grid.setDataProvider(dataProvider);
    }

    private void buildLeftPanel() {
        dataProvider = DataProvider.ofCollection(collectionDevice);
        grid = new Grid<>();
        grid.setEnabled(true);
        grid.addColumn(DeviceEntity::getPhonenumber).setCaption("Numero Telefonico");
        grid.addColumn(DeviceEntity::getMark).setCaption("Marca");
        grid.addColumn(DeviceEntity::getImei).setCaption("Imei");
        grid.addColumn(DeviceEntity::getModel).setCaption("Modelo");
        grid.setDataProvider(dataProvider);
        grid.addItemClickListener(new ItemClickListener<DeviceEntity>() {
            @Override
            public void itemClick(Grid.ItemClick<DeviceEntity> event) {
                txtMark.setValue(event.getItem().getMark());
                txtModel.setValue(event.getItem().getModel());
                txtImei.setValue(event.getItem().getImei());
                txtPhoneNumber.setValue(event.getItem().getPhonenumber());
                for(VehicleEntity vehicle : collectionVehicles){
                    if (vehicle.getIdvehicle() == event.getItem().getIdvehicle()){
                        cmbVehicle.setSelectedItem(vehicle.getLicenseplate());
                    }
                }
            }
        });
        leftPanel.setSpacing(true);
        leftPanel.setMargin(new MarginInfo(true, true, true, true));
        leftPanel.setSizeFull();
        grid.setSizeFull();
        leftPanel.addComponent(grid);
        contenedor.addComponent(leftPanel);
    }


    private void loadAllData() {
        collectionDevice = controllerDevice.findAllDevice();
        collectionVehicles = controllerVehicle.findAllVehicle();
    }

    private void hideContent() {
        infoPanel.setVisible(false);
        infoPanel2.setVisible(false);
        infoPanel3.setVisible(false);
        buttonLayout2.setVisible(false);
    }

    private void showContent() {
        infoPanel.setVisible(true);
        infoPanel2.setVisible(true);
        infoPanel3.setVisible(true);
        buttonLayout2.setVisible(true);
    }

}
