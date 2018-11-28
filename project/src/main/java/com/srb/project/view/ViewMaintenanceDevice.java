package com.srb.project.view;


import com.srb.project.controller.ControllerDevice;
import com.srb.project.controller.ControllerVehicle;
import com.srb.project.enumConstans.EnumLabel;
import com.srb.project.enumConstans.EnumMessages;
import com.srb.project.model.DeviceEntity;
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
import java.util.ArrayList;
import java.util.Collection;

@UIScope
@SpringView(name = ViewMaintenanceDevice.VIEW_NAME)
public class ViewMaintenanceDevice extends VerticalLayout implements View {

    public static final String VIEW_NAME = "dispositivos";

    @Autowired
    ControllerDevice controllerDevice;
    @Autowired
    ControllerVehicle controllerVehicle;

    private Collection<VehicleEntity> collectionVehicles;
    private Collection<String> plateVehicles = new ArrayList<>();
    private Collection<DeviceEntity> collectionDevice;

    private DeviceEntity deviceEntitySelect;

    // componentes de la vista
    private TextField txtMark = new TextField(EnumLabel.MARCA_LABEL.getLabel());
    private TextField txtModel = new TextField(EnumLabel.MODELO_LABEL.getLabel());
    private TextField txtImei = new TextField(EnumLabel.IMEI_LABEL.getLabel());
    private TextField txtPhoneNumber = new TextField(EnumLabel.NUMERO_TELEFONO_LABEL.getLabel());
    private ComboBox<String> cmbVehicle = new ComboBox<>(EnumLabel.VEHICULO_LABEL.getLabel());

    //Botones
    private Button btnSave = new Button(EnumLabel.REGISTRAR_LABEL.getLabel());
    private Button btnNew = new Button(EnumLabel.REGISTRAR_LABEL.getLabel());
    private Button btnAccept = new Button(EnumLabel.ACEPTAR_LABEL.getLabel());
    private Button btnEdit = new Button(EnumLabel.EDITAR_LABEL.getLabel());
    private Button btnDelete = new Button(EnumLabel.ELIMINAR_LABEL.getLabel());
    private Button btnCancel = new Button(EnumLabel.CANCELAR_LABEL.getLabel());


    private ListDataProvider<DeviceEntity> dataProvider;
    private Grid<DeviceEntity> grid = new Grid<>();
    //Layouts
    private HorizontalLayout menuLayout = new HorizontalLayout();
    private HorizontalLayout principalLayout = new HorizontalLayout();
    private Panel principalPanel = new Panel("Mantenimiento de Dispositivo");
    private VerticalLayout leftlayout = new VerticalLayout();
    private VerticalLayout rightLayout = new VerticalLayout();
    private HorizontalLayout buttonsPrincipalLayout = new HorizontalLayout();
    private GridLayout fieldsLayout = new GridLayout(2, 2);
    private HorizontalLayout buttonsSecondaryLayout = new HorizontalLayout();
    private MenuBar menuBar;

    String action = "";

    public ViewMaintenanceDevice() {
    }

    @PostConstruct
    public void buildForm() {
        this.setWidth("100%");
        this.setHeightUndefined();

        if (menuBar == null)
            menuBar = ViewMenu.buildMenu();
        menuLayout.addComponent(menuBar);
        fieldsLayout.setSpacing(true);
        setPropertiesField();
        loadAllData();
        loadInformationCombox();
        setLeftPanel();
        setRightPanel();

        principalPanel.setSizeFull();
        principalPanel.setContent(principalLayout);
        this.addComponents(menuLayout, principalPanel);
        showFields(false);
        this.setComponentAlignment(menuLayout, Alignment.TOP_CENTER);
    }

    private void setLeftPanel() {
        principalLayout.setSizeFull();
        leftlayout.setSizeFull();
        createGrid();
        leftlayout.addComponent(grid);
        principalLayout.addComponent(leftlayout);
    }

    private void createGrid() {
        dataProvider = DataProvider.ofCollection(collectionDevice);
        grid.setSizeFull();
        grid.setEnabled(true);
        grid.addColumn(DeviceEntity::getPhonenumber).setCaption(EnumLabel.NUMERO_TELEFONO_LABEL.getLabel());
        grid.addColumn(DeviceEntity::getMark).setCaption((EnumLabel.MARCA_LABEL.getLabel()));
        grid.addColumn(DeviceEntity::getImei).setCaption((EnumLabel.IMEI_LABEL.getLabel()));
        grid.addColumn(DeviceEntity::getModel).setCaption((EnumLabel.MODELO_LABEL.getLabel()));
        grid.setDataProvider(dataProvider);
        grid.addItemClickListener(new ItemClickListener<DeviceEntity>() {
            @Override
            public void itemClick(Grid.ItemClick<DeviceEntity> event) {
                deviceEntitySelect = event.getItem();
                txtMark.setValue(event.getItem().getMark());
                txtModel.setValue(event.getItem().getModel());
                txtImei.setValue(event.getItem().getImei());
                txtPhoneNumber.setValue(event.getItem().getPhonenumber());
                for (VehicleEntity vehicle : collectionVehicles) {
                    if (vehicle.getIdvehicle() == event.getItem().getIdvehicle()) {
                        cmbVehicle.setSelectedItem(vehicle.getLicenseplate());
                    }
                }
            }
        });

    }


    private void refreshInformationGrid() {
        collectionDevice = controllerDevice.findAllDevice();
        dataProvider = DataProvider.ofCollection(collectionDevice);
        grid.setDataProvider(dataProvider);
    }


    private void clearFields() {
        txtImei.clear();
        txtMark.clear();
        txtModel.clear();
        txtPhoneNumber.clear();
        cmbVehicle.setSelectedItem("");
    }

    private void showMessage(String mensaje, Notification.Type type) {
        Notification.show(mensaje, type);
    }

    private boolean isValidationAllField(String message) {
        if (isValidationFieldEmpty(txtMark)) {
            Notification.show(message, Notification.Type.ERROR_MESSAGE);
            return true;
        } else if (isValidationFieldEmpty(txtImei)) {
            Notification.show(message, Notification.Type.ERROR_MESSAGE);
            return true;
        } else if (isValidationFieldEmpty(txtModel)) {
            Notification.show(message, Notification.Type.ERROR_MESSAGE);
            return true;
        } else if (isValidationFieldEmpty(txtPhoneNumber)) {
            Notification.show(message, Notification.Type.ERROR_MESSAGE);
            return true;
        } else if (isValidationFieldEmpty(cmbVehicle)) {
            Notification.show(message, Notification.Type.ERROR_MESSAGE);
            return true;
        }
        return false;
    }

    private boolean isValidationFieldEmpty(TextField textField) {
        boolean validation = false;
        if (textField.getValue().isEmpty()) {

            validation = true;
        }
        return validation;
    }

    private boolean isValidationFieldEmpty(ComboBox comboBox) {
        boolean validation = false;
        if (comboBox.getValue() == null || comboBox.getValue().equals("")) {

            validation = true;
        }
        return validation;
    }

    private void setPropertiesField() {
        setRequieredField(txtImei);
        setRequieredField(txtMark);
        setRequieredField(txtModel);
        setRequieredField(txtPhoneNumber);
        setRequieredField(cmbVehicle);
    }

    private void setRequieredField(TextField requieredField) {

        requieredField.setRequiredIndicatorVisible(true);

    }

    private void setRequieredField(ComboBox requieredField) {

        requieredField.setRequiredIndicatorVisible(true);

    }

    private void showFields(Boolean value) {
        fieldsLayout.setVisible(value);
        buttonsSecondaryLayout.setVisible(value);
    }

    private void setRightPanel() {
        rightLayout.setSizeFull();
        buildButtons();
        buildFields();
        buildButtonsFooter();
        principalLayout.addComponent(rightLayout);
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

    private void buildFields() {
        fieldsLayout.addComponents(txtImei, txtMark, txtModel, txtPhoneNumber, cmbVehicle);
        rightLayout.addComponent(fieldsLayout);
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
                    processAddDevice();
                } else if (action.equalsIgnoreCase("edit")) {
                    processUpdateDevice();
                } else if (action.equalsIgnoreCase("delete")) {
                    processDeleteDevice();
                }
            }
        });

        buttonsSecondaryLayout.addComponents(btnCancel, btnAccept);
        rightLayout.addComponent(buttonsSecondaryLayout);
    }

    private void processAddDevice() {

        if (!isValidationAllField(EnumMessages.MESSAGE_REQUIRED_FIELD.getMessage())) {
            DeviceEntity deviceEntity = new DeviceEntity();
            deviceEntity.setStatedelete(Byte.valueOf("1"));
            deviceEntity.setImei(txtImei.getValue());
            deviceEntity.setMark(txtMark.getValue());
            deviceEntity.setModel(txtModel.getValue());
            deviceEntity.setPhonenumber(txtPhoneNumber.getValue());
            for (VehicleEntity vehicle : collectionVehicles) {
                if (vehicle.getLicenseplate().equalsIgnoreCase(cmbVehicle.getValue())) {
                    deviceEntity.setIdvehicle(vehicle.getIdvehicle());
                    deviceEntity.setIdvehicle(vehicle.getIdvehicle());
                    deviceEntity.setVehicleByIdvehicle(vehicle);
                    break;
                }
            }
            try {
                if (controllerDevice.validateDevice(deviceEntity.getImei())) {
                    controllerDevice.save(deviceEntity);
                    showMessage(EnumMessages.MESSAGES_SUCESS_SAVE.getMessage(), Notification.Type.HUMANIZED_MESSAGE);
                    refreshInformationGrid();
                    clearFields();
                    showFields(false);
                } else {
                    Notification.show(EnumMessages.EXIST_DEVICE.getMessage(), Notification.Type.ERROR_MESSAGE);
                }
            } catch (Exception e) {
                showMessage(EnumMessages.MESSAGES_ERROR_SAVE.getMessage(), Notification.Type.HUMANIZED_MESSAGE);
                showFields(false);
            }
        }
    }

    private void processUpdateDevice() {
        try {
            if ((txtImei.getValue().equalsIgnoreCase(deviceEntitySelect.getImei())) || (controllerDevice.validateDevice(txtImei.getValue()))) {
                deviceEntitySelect.setStatedelete(Byte.valueOf("1"));
                deviceEntitySelect.setImei(txtImei.getValue());
                deviceEntitySelect.setMark(txtMark.getValue());
                deviceEntitySelect.setModel(txtModel.getValue());
                deviceEntitySelect.setPhonenumber(txtPhoneNumber.getValue());
                for (VehicleEntity vehicle : collectionVehicles) {
                    if (vehicle.getLicenseplate().equalsIgnoreCase(cmbVehicle.getValue())) {
                        deviceEntitySelect.setIdvehicle(vehicle.getIdvehicle());
                        deviceEntitySelect.setIdvehicle(vehicle.getIdvehicle());
                        deviceEntitySelect.setVehicleByIdvehicle(vehicle);
                        break;
                    }
                }
                controllerDevice.updateDevice(deviceEntitySelect);
                showMessage(EnumMessages.MESSAGE_SUCESS_UPDATE.getMessage(), Notification.Type.HUMANIZED_MESSAGE);
                refreshInformationGrid();
                clearFields();
                showFields(false);

            } else {
                Notification.show(EnumMessages.EXIST_DEVICE.getMessage(), Notification.Type.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            showMessage(EnumMessages.MESSAGES_ERROR_UPDATE.getMessage(), Notification.Type.HUMANIZED_MESSAGE);
            clearFields();
            showFields(false);
        }
    }


    private void processDeleteDevice() {
        try {
            controllerDevice.deleteDevice(deviceEntitySelect);
            showMessage(EnumMessages.MESSAGE_SUCESS_DELETE.getMessage(), Notification.Type.HUMANIZED_MESSAGE);
            refreshInformationGrid();
            clearFields();
            showFields(false);
            enableFields(true);
        } catch (Exception e) {
            showMessage(EnumMessages.MESSAGES_ERROR_DELETE.getMessage(), Notification.Type.ERROR_MESSAGE);
        }
    }

    private void enableFields(boolean value) {
        cmbVehicle.setEnabled(value);
        txtImei.setEnabled(value);
        txtMark.setEnabled(value);
        txtModel.setEnabled(value);
        txtPhoneNumber.setEnabled(value);

    }
    private void loadAllData() {
        collectionDevice = controllerDevice.findAllDevice();
        collectionVehicles = controllerVehicle.findAllVehicle();
    }

    private void loadInformationCombox() {

        for (VehicleEntity vehicles : collectionVehicles) {
            plateVehicles.add(vehicles.getLicenseplate());
        }

        cmbVehicle.setItems(plateVehicles);
    }

}
