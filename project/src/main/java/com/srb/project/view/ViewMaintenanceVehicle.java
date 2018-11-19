package com.srb.project.view;


import com.srb.project.controller.ControllerVehicle;
import com.srb.project.enumConstans.EnumMessages;
import com.srb.project.model.VehicleEntity;
import com.vaadin.data.provider.DataProvider;
import com.vaadin.data.provider.ListDataProvider;
import com.vaadin.navigator.View;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.*;
import com.vaadin.ui.components.grid.ItemClickListener;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.Collection;

@UIScope
@SpringView(name = ViewMaintenanceVehicle.VIEW_NAME)
public class ViewMaintenanceVehicle extends VerticalLayout implements View {

    public static final String VIEW_NAME = "vehicle";
    private Grid<VehicleEntity> grid= new Grid<>();

    //Layouts
    private HorizontalLayout menuLayout = new HorizontalLayout();
    private HorizontalLayout principalLayout = new HorizontalLayout();
    private VerticalLayout leftlayout = new VerticalLayout();
    private VerticalLayout rightLayout = new VerticalLayout();
    private HorizontalLayout buttonsPrincipalLayout = new HorizontalLayout();
    private GridLayout fieldsLayout = new GridLayout(2,2);
    private HorizontalLayout buttonsSecondaryLayout = new HorizontalLayout();
    private MenuBar menuBar;

    //fields
    private TextField txtMark = new TextField("Marca");
    private TextField txtLicense = new TextField("Matricula");
    private TextField txtYear = new TextField("Año");
    private TextField txtTon = new TextField("Toneladas");

    //Buttons
    private Button btnNew =new Button("Registrar");
    private Button btnAccept =new Button("Aceptar");
    private Button btnEdit =new Button("Editar");
    private Button btnDelete =new Button("Eliminar");
    private Button btnCancel =new Button("Cancelar");


    @Autowired
    ControllerVehicle controllerVehicle;
    private Collection<VehicleEntity> collectionsVehicles;
    private ListDataProvider<VehicleEntity> dataProvider;
    private VehicleEntity vehicleSelected;
    private String action;

    public ViewMaintenanceVehicle() {

    }

    @PostConstruct
    private void buildForm() {
        this.setWidth("100%");
        this.setHeightUndefined();
        if (menuBar==null)
            menuBar = ViewMenu.buildMenu();
        menuLayout.addComponent(menuBar);
        fieldsLayout.setSpacing(true);
        createGrid();
        cretePrincipalButtons();
        createFields();
        creteSecondaryButtons();

        this.addComponents(menuLayout,principalLayout);
        hideFields();
        this.setComponentAlignment(menuLayout,Alignment.TOP_CENTER);
    }

    private void creteSecondaryButtons() {
        btnCancel.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                clearFields();
                hideFields();
            }
        });

        btnAccept.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                if (action.equalsIgnoreCase("new")) {
                    processAddVehicle();
                } else if (action.equalsIgnoreCase("edit")) {
                    processUpdateRol();
                } else if (action.equalsIgnoreCase("delete")) {
                    processDeleteRol();
                }
            }
        });

        buttonsSecondaryLayout.addComponents(btnCancel,btnAccept);
        rightLayout.addComponent(buttonsSecondaryLayout);
        principalLayout.addComponent(rightLayout);
    }

    private void createFields() {
        fieldsLayout.addComponents(txtLicense,txtMark, txtYear,txtTon);
        rightLayout.addComponent(fieldsLayout);
        principalLayout.addComponent(rightLayout);
    }

    private void cretePrincipalButtons() {
        btnNew.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                clearFields();
                showFields();
                action = "new";
            }
        });

        btnEdit.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                if (vehicleSelected != null){
                    showFields();
                    action = "edit";
                }else {
                    showMessage("Debe seleccionar un vehiculo para editar", Notification.Type.HUMANIZED_MESSAGE);
                }
            }
        });

        btnDelete.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                if (vehicleSelected != null){
                    showFields();
                    action = "delete";
                }else {
                    showMessage("Debe seleccionar un vehiculo para eliminar", Notification.Type.HUMANIZED_MESSAGE);
                }
            }
        });

        buttonsPrincipalLayout.addComponents(btnNew,btnEdit,btnDelete);
        rightLayout.addComponent(buttonsPrincipalLayout);
        principalLayout.addComponent(rightLayout);
    }

    private void createGrid() {
        principalLayout.setSizeFull();

        collectionsVehicles = controllerVehicle.findAllVehicle();
        dataProvider = DataProvider.ofCollection(collectionsVehicles);
        grid.setSizeFull();
        grid.setEnabled(true);
        grid.addColumn(VehicleEntity::getLicenseplate).setCaption("Matricula");
        grid.addColumn(VehicleEntity::getMark).setCaption("Marca");
        grid.addColumn(VehicleEntity::getVehicleyear).setCaption("Año");
        grid.addColumn(VehicleEntity::getTon).setCaption("Capacidad Toneladas");
        grid.setDataProvider(dataProvider);
        grid.addItemClickListener(new ItemClickListener<VehicleEntity>() {
            @Override
            public void itemClick(Grid.ItemClick<VehicleEntity> event) {
                vehicleSelected  = event.getItem();
                txtLicense.setValue(vehicleSelected.getLicenseplate());
                txtMark.setValue(vehicleSelected.getMark());
                txtYear.setValue(vehicleSelected.getVehicleyear().toString());
                txtTon.setValue(vehicleSelected.getTon().toString());
            }
        });
        leftlayout.setSizeFull();
        leftlayout.addComponent(grid);
        principalLayout.addComponent(leftlayout);
    }

    private void clearFields(){
        txtLicense.clear();
        txtMark.clear();
        txtYear.clear();
        txtTon.clear();
    }

    private void refreshInformationGrid() {
        collectionsVehicles = controllerVehicle.findAllVehicle();
        dataProvider = DataProvider.ofCollection(collectionsVehicles);
        grid.setDataProvider(dataProvider);
    }

    private void showMessage(String mensaje, Notification.Type type){
        Notification.show(mensaje, type);
    }

    private void showFields() {
        fieldsLayout.setVisible(true);
        buttonsSecondaryLayout.setVisible(true);
    }

    private void hideFields() {
        fieldsLayout.setVisible(false);
        buttonsSecondaryLayout.setVisible(false);
    }

    private void processAddVehicle() {
        VehicleEntity vehicleEntity = new VehicleEntity();
        if (!isValidationFieldEmpty()) {
            try {
                vehicleEntity.setStatedelete(new Byte("1"));
                vehicleEntity.setMark(txtMark.getValue());
                vehicleEntity.setLicenseplate(txtLicense.getValue());
                vehicleEntity.setTon(Integer.valueOf(txtTon.getValue()));
                vehicleEntity.setVehicleyear(Integer.valueOf(txtYear.getValue()));
                if (controllerVehicle.validateVehicle(vehicleEntity.getLicenseplate())) {
                    controllerVehicle.save(vehicleEntity);
                    Notification.show(EnumMessages.MESSAGES_SUCESS_SAVE.getMessage(), Notification.Type.HUMANIZED_MESSAGE);
                    refreshInformationGrid();
                    clearFields();
                    hideFields();
                }else {
                    Notification.show(EnumMessages.EXIST_VEHICLE.getMessage(), Notification.Type.ERROR_MESSAGE);
                }
            } catch (Exception e) {
                Notification.show(EnumMessages.MESSAGES_ERROR_SAVE.getMessage(), Notification.Type.ERROR_MESSAGE);
            }
        }
    }

    private void processUpdateRol() {
        try {
            if ((txtLicense.getValue().equalsIgnoreCase(vehicleSelected.getLicenseplate())) || (controllerVehicle.validateVehicle(txtLicense.getValue()))) {
                vehicleSelected.setStatedelete(new Byte("1"));
                vehicleSelected.setMark(txtMark.getValue());
                vehicleSelected.setLicenseplate(txtLicense.getValue());
                vehicleSelected.setTon(Integer.valueOf(txtTon.getValue()));
                vehicleSelected.setVehicleyear(Integer.valueOf(txtYear.getValue()));
                controllerVehicle.updateVehicle(vehicleSelected);
                Notification.show(EnumMessages.MESSAGE_SUCESS_UPDATE.getMessage(), Notification.Type.HUMANIZED_MESSAGE);
                refreshInformationGrid();
                clearFields();
                hideFields();
            }else{
                Notification.show(EnumMessages.EXIST_VEHICLE.getMessage(), Notification.Type.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            Notification.show(EnumMessages.MESSAGES_ERROR_SAVE.getMessage(), Notification.Type.ERROR_MESSAGE);
        }
    }

    private void processDeleteRol() {
        try {
            controllerVehicle.deleteVehicle(vehicleSelected);
            Notification.show(EnumMessages.MESSAGE_SUCESS_DELETE.getMessage(), Notification.Type.HUMANIZED_MESSAGE);
            refreshInformationGrid();
            clearFields();
            hideFields();
        } catch (Exception e) {
            Notification.show(EnumMessages.MESSAGES_ERROR_SAVE.getMessage(), Notification.Type.ERROR_MESSAGE);
        }
    }

    private boolean isValidationFieldEmpty() {
        boolean validation = false;
        if (txtMark.getValue().isEmpty() || txtLicense.getValue().isEmpty() || txtYear.getValue().isEmpty() || txtTon.getValue().isEmpty()) {
            Notification.show(EnumMessages.MESSAGE_REQUIRED_FIELD.getMessage(), Notification.Type.ERROR_MESSAGE);
            validation = true;
        }
        return validation;
    }
}
