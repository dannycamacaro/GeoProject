package com.srb.project.view;


import com.srb.project.controller.ControllerVehicle;
import com.srb.project.enumConstans.EnumLabel;
import com.srb.project.enumConstans.EnumMessages;
import com.srb.project.model.VehicleEntity;
import com.srb.project.util.ValidationsString;
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
    private Grid<VehicleEntity> grid = new Grid<>();

    //Layouts
    private HorizontalLayout menuLayout = new HorizontalLayout();
    private HorizontalLayout principalLayout = new HorizontalLayout();
    private Panel principalPanel = new Panel("Mantenimiento de Vehiculo");
    private VerticalLayout leftLayout = new VerticalLayout();
    private VerticalLayout rightLayout = new VerticalLayout();
    private HorizontalLayout buttonsPrincipalLayout = new HorizontalLayout();
    private GridLayout fieldsLayout = new GridLayout(2, 2);
    private HorizontalLayout buttonsSecondaryLayout = new HorizontalLayout();
    private MenuBar menuBar;

    //fields
    private TextField txtMark = new TextField(EnumLabel.MARCA_LABEL.getLabel());
    private TextField txtLicense = new TextField(EnumLabel.MATRICULA_LABEL.getLabel());
    private TextField txtYear = new TextField(EnumLabel.YEAR_LABEL.getLabel());
    private TextField txtTon = new TextField(EnumLabel.TONELADAS_LABEL.getLabel());

    //Buttons
    private Button btnNew = new Button(EnumLabel.REGISTRAR_LABEL.getLabel());
    private Button btnAccept = new Button(EnumLabel.ACEPTAR_LABEL.getLabel());
    private Button btnEdit = new Button(EnumLabel.EDITAR_LABEL.getLabel());
    private Button btnDelete = new Button(EnumLabel.ELIMINAR_LABEL.getLabel());
    private Button btnCancel = new Button(EnumLabel.CANCELAR_LABEL.getLabel());


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
        if (menuBar == null)
            menuBar = ViewMenu.buildMenu();
        menuLayout.addComponent(menuBar);
        fieldsLayout.setSpacing(true);
        setPropertiesField();
        setLeftPanel();
        setRightPanel();

        principalPanel.setSizeFull();
        principalPanel.setContent(principalLayout);
        this.addComponents(menuLayout, principalPanel);
        showFields(false);
        this.setComponentAlignment(menuLayout, Alignment.TOP_CENTER);
    }

    private void setRightPanel() {
        rightLayout.setSizeFull();
        buildButtons();
        buildFields();
        buildButtonsFooter();
        principalLayout.addComponent(rightLayout);
    }

    private void buildButtonsFooter() {

        btnCancel.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                showFields(false);
                clearFields();
            }
        });
        btnAccept.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                if (action.equalsIgnoreCase("new")) {
                    processAddVehicle();
                } else if (action.equalsIgnoreCase("edit")) {
                    processUpdateVehicle();
                } else if (action.equalsIgnoreCase("delete")) {
                    processDeleteVehicle();
                }
            }
        });

        buttonsSecondaryLayout.addComponents(btnCancel, btnAccept);
        rightLayout.addComponent(buttonsSecondaryLayout);
    }

    private void buildFields() {

        fieldsLayout.addComponents(txtLicense, txtMark, txtYear, txtTon);
        rightLayout.addComponent(fieldsLayout);
    }

    private void buildButtons() {

        btnNew.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                showFields(true);
                clearFields();
                action = "new";
            }
        });
        btnEdit.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                if (!isValidationAllField(EnumMessages.SELECT_REGISTER.getMessage())) {
                    showFields(true);
                    action = "edit";
                }
            }
        });
        btnDelete.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                if (!isValidationAllField(EnumMessages.SELECT_REGISTER.getMessage())) {
                    showFields(true);
                    action = "delete";
                    enableFields(false);
                }
            }
        });
        buttonsPrincipalLayout.addComponents(btnNew, btnEdit, btnDelete);
        rightLayout.addComponent(buttonsPrincipalLayout);
    }

    private void enableFields(boolean value) {
        txtLicense.setEnabled(value);
        txtMark.setEnabled(value);
        txtYear.setEnabled(value);
        txtTon.setEnabled(value);
    }

    private boolean isValidationAllField(String message) {
        if (isValidationFieldEmpty(txtTon.getValue())) {
            Notification.show("Debe llenar el campo Toneladas", Notification.Type.ERROR_MESSAGE);
            return true;
        } else if (isValidationFieldEmpty(txtYear.getValue())) {
            Notification.show("Debe llenar el campo Año", Notification.Type.ERROR_MESSAGE);
            return true;
        } else if (isValidationFieldEmpty(txtMark.getValue())) {
            Notification.show("Debe llenar el campo Marca", Notification.Type.ERROR_MESSAGE);
            return true;
        } else if (isValidationFieldEmpty(txtLicense.getValue())) {
            Notification.show("Debe llenar el campo Matricula", Notification.Type.ERROR_MESSAGE);
            return true;
        }
        if (ValidationsString.onlyNumbers(txtTon.getValue())) {
            Notification.show("Tonelada solo puede ser numerico", Notification.Type.ERROR_MESSAGE);
            return true;
        } else if (ValidationsString.onlyNumbers(txtYear.getValue())) {
            Notification.show("Año solo puede ser numerico", Notification.Type.ERROR_MESSAGE);
            return true;
        }
        return false;
    }

    private boolean isValidationFieldEmpty(String textField) {
        boolean validation = false;
        if (textField == null || textField.isEmpty()) {

            validation = true;
        }
        return validation;
    }

    private void setLeftPanel() {

        principalLayout.setSizeFull();
        leftLayout.setSizeFull();
        createGrid();
        leftLayout.addComponent(grid);
        principalLayout.addComponent(leftLayout);
    }

    private void setPropertiesField() {
        txtLicense.setRequiredIndicatorVisible(true);
        txtMark.setRequiredIndicatorVisible(true);
        txtYear.setRequiredIndicatorVisible(true);
        txtTon.setRequiredIndicatorVisible(true);

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
                vehicleSelected = event.getItem();
                txtLicense.setValue(vehicleSelected.getLicenseplate());
                txtMark.setValue(vehicleSelected.getMark());
                txtYear.setValue(vehicleSelected.getVehicleyear().toString());
                txtTon.setValue(vehicleSelected.getTon().toString());
            }
        });
        leftLayout.setSizeFull();
        leftLayout.addComponent(grid);
        principalLayout.addComponent(leftLayout);
    }

    private void clearFields() {
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

    private void showFields(Boolean value) {
        fieldsLayout.setVisible(value);
        buttonsSecondaryLayout.setVisible(value);
    }

    private void processAddVehicle() {
        VehicleEntity vehicleEntity = new VehicleEntity();
        if (!isValidationAllField(EnumMessages.MESSAGE_REQUIRED_FIELD.getMessage())) {
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
                    showFields(false);
                } else {
                    Notification.show(EnumMessages.EXIST_VEHICLE.getMessage(), Notification.Type.ERROR_MESSAGE);
                }
            }catch (NumberFormatException e){
                Notification.show(EnumMessages.MESSAGES_ERROR_NUMBERFORMAT.getMessage(), Notification.Type.ERROR_MESSAGE);
            }
            catch (Exception e) {
                Notification.show(EnumMessages.MESSAGES_ERROR_SAVE.getMessage(), Notification.Type.ERROR_MESSAGE);
            }
        }
    }

    private void processUpdateVehicle() {
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
                showFields(false);
            } else {
                Notification.show(EnumMessages.EXIST_VEHICLE.getMessage(), Notification.Type.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            Notification.show(EnumMessages.MESSAGES_ERROR_SAVE.getMessage(), Notification.Type.ERROR_MESSAGE);
        }
    }

    private void processDeleteVehicle() {
        try {
            controllerVehicle.deleteVehicle(vehicleSelected);
            Notification.show(EnumMessages.MESSAGE_SUCESS_DELETE.getMessage(), Notification.Type.HUMANIZED_MESSAGE);
            refreshInformationGrid();
            clearFields();
            showFields(false);
            enableFields(true);
        } catch (Exception e) {
            Notification.show(EnumMessages.MESSAGES_ERROR_SAVE.getMessage(), Notification.Type.ERROR_MESSAGE);
        }
    }

}
