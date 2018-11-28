package com.srb.project.view;


import com.srb.project.controller.ControllerAssignedVehicle;
import com.srb.project.controller.ControllerUser;
import com.srb.project.controller.ControllerVehicle;
import com.srb.project.enumConstans.EnumLabel;
import com.srb.project.enumConstans.EnumMessages;
import com.srb.project.model.AssignedvehicleEntity;
import com.srb.project.model.UsersEntity;
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
import java.util.ArrayList;
import java.util.Collection;

@UIScope
@SpringView(name = ViewMaintenanceAssignedVehicle.VIEW_NAME)
public class ViewMaintenanceAssignedVehicle extends VerticalLayout implements View {

    public static final String VIEW_NAME = "assignedvehicle";
    private Grid<AssignedvehicleEntity> grid = new Grid<>();

    //Layouts
    private HorizontalLayout menuLayout = new HorizontalLayout();
    private HorizontalLayout principalLayout = new HorizontalLayout();
    private Panel principalPanel = new Panel("Asignar Vehiculo");
    private VerticalLayout leftLayout = new VerticalLayout();
    private VerticalLayout rightLayout = new VerticalLayout();
    private HorizontalLayout buttonsPrincipalLayout = new HorizontalLayout();
    private GridLayout fieldsLayout = new GridLayout(2, 2);
    private HorizontalLayout buttonsSecondaryLayout = new HorizontalLayout();
    private MenuBar menuBar;

    //fields
    private DateField txtInitDate = new DateField(EnumLabel.INIT_DATE_LABEL.getLabel());
    private DateField txtFinishDate = new DateField(EnumLabel.FINISH_DATE_LABEL.getLabel());
    ComboBox<String> cmbVehicle = new ComboBox<>(EnumLabel.VEHICULO_LABEL.getLabel());
    ComboBox<String> cmbUsers = new ComboBox<>(EnumLabel.USER_LABEL.getLabel());

    //Buttons
    private Button btnNew = new Button(EnumLabel.REGISTRAR_LABEL.getLabel());
    private Button btnAccept = new Button(EnumLabel.ACEPTAR_LABEL.getLabel());
    private Button btnEdit = new Button(EnumLabel.EDITAR_LABEL.getLabel());
    private Button btnDelete = new Button(EnumLabel.ELIMINAR_LABEL.getLabel());
    private Button btnCancel = new Button(EnumLabel.CANCELAR_LABEL.getLabel());


    @Autowired
    ControllerVehicle controllerVehicle;

    @Autowired
    ControllerUser controllerUser;

    @Autowired
    ControllerAssignedVehicle controllerAssignedVehicle;

    private Collection<AssignedvehicleEntity> collectionsAssignedVehicles;
    private ListDataProvider<AssignedvehicleEntity> dataProviderAssignedVehicles;
    private AssignedvehicleEntity assignedVehicleSelected;
    private Collection<VehicleEntity> collectionsVehicles;
    private Collection<String> arrayVehicle = new ArrayList<>();
    private Collection<UsersEntity> collectionsUsers;
    private Collection<String> arrayUsers = new ArrayList<>();
    private String action;
    private UsersEntity usersEntitySelect;

    public ViewMaintenanceAssignedVehicle() {

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

    private void setPropertiesField() {

        txtFinishDate.setRequiredIndicatorVisible(true);
        txtInitDate.setRequiredIndicatorVisible(true);
        cmbUsers.setRequiredIndicatorVisible(true);
        cmbVehicle.setRequiredIndicatorVisible(true);

    }

    private void setRightPanel() {

        rightLayout.setSizeFull();
        loadInformationCombox();
        buildButtons();
        buildFields();
        buildButtonsFooter();
        principalLayout.addComponent(rightLayout);
    }

    private void loadInformationCombox() {
        collectionsVehicles = controllerVehicle.findAllVehicle();
        collectionsUsers = controllerUser.findAllUsers();
        for (VehicleEntity vehicleEntity : collectionsVehicles) {
            arrayVehicle.add(vehicleEntity.getLicenseplate());
        }
        cmbVehicle.setItems(arrayVehicle);
        for (UsersEntity usersEntity : collectionsUsers) {

            arrayUsers.add(usersEntity.getUsername());
        }
        cmbUsers.setItems(arrayUsers);
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
                    processAddAssignedVehicle();
                } else if (action.equalsIgnoreCase("edit")) {
                    processUpdateAssignedVehicle();
                } else if (action.equalsIgnoreCase("delete")) {
                    processDeleteAssignedVehicle();
                }
            }
        });

        buttonsSecondaryLayout.addComponents(btnCancel, btnAccept);
        rightLayout.addComponent(buttonsSecondaryLayout);
    }

    private void processDeleteAssignedVehicle() {

        try {
            controllerAssignedVehicle.deleteAssignedVehicle(assignedVehicleSelected);
            Notification.show(EnumMessages.MESSAGE_SUCESS_DELETE.getMessage(), Notification.Type.HUMANIZED_MESSAGE);
            refreshInformationGrid();
            clearFields();
            showFields(false);
            enableFields(true);
        } catch (Exception e) {
            Notification.show(EnumMessages.MESSAGES_ERROR_SAVE.getMessage(), Notification.Type.ERROR_MESSAGE);
        }

    }

    private void processUpdateAssignedVehicle() {

        try {

            for (VehicleEntity vehicleEntity : collectionsVehicles) {
                if (vehicleEntity.getLicenseplate().equalsIgnoreCase(cmbVehicle.getValue())) {
                    assignedVehicleSelected.setIdvehicle(vehicleEntity.getIdvehicle());
                    assignedVehicleSelected.setVehicleByIdvehicle(vehicleEntity);
                    break;
                }
            }
            for (UsersEntity usersEntity : collectionsUsers) {
                if (usersEntity.getUsername().equalsIgnoreCase(cmbUsers.getValue())) {
                    assignedVehicleSelected.setIdusers(usersEntity.getIdusers());
                    assignedVehicleSelected.setUsersByIdusers(usersEntity);
                    break;
                }
            }

            assignedVehicleSelected.setInitialdate(txtInitDate.getValue());
            assignedVehicleSelected.setFinishdate(txtFinishDate.getValue());

            controllerAssignedVehicle.updateAssigneVehicle(assignedVehicleSelected);
            Notification.show(EnumMessages.MESSAGE_SUCESS_UPDATE.getMessage(), Notification.Type.HUMANIZED_MESSAGE);
            refreshInformationGrid();
            clearFields();
            showFields(false);
        } catch (Exception e) {
            Notification.show(EnumMessages.MESSAGES_ERROR_SAVE.getMessage(), Notification.Type.ERROR_MESSAGE);
        }

    }

    private void processAddAssignedVehicle() {

        AssignedvehicleEntity assignedvehicleEntity = new AssignedvehicleEntity();
        if (!isValidationAllField(EnumMessages.MESSAGE_REQUIRED_FIELD.getMessage())) {
            try {

                for (VehicleEntity vehicleEntity : collectionsVehicles) {
                    if (vehicleEntity.getLicenseplate().equalsIgnoreCase(cmbVehicle.getValue())) {
                        assignedvehicleEntity.setIdvehicle(vehicleEntity.getIdvehicle());
                        assignedvehicleEntity.setVehicleByIdvehicle(vehicleEntity);
                        break;
                    }
                }
                for (UsersEntity usersEntity : collectionsUsers) {
                    if (usersEntity.getUsername().equalsIgnoreCase(cmbUsers.getValue())) {
                        assignedvehicleEntity.setIdusers(usersEntity.getIdusers());
                        assignedvehicleEntity.setUsersByIdusers(usersEntity);
                        usersEntitySelect = usersEntity;
                        break;
                    }
                }
                assignedvehicleEntity.setInitialdate(txtInitDate.getValue());
                assignedvehicleEntity.setFinishdate(txtFinishDate.getValue());
                if (controllerAssignedVehicle.validateUserAssignedVehicle(usersEntitySelect.getIdusers())) {
                    controllerAssignedVehicle.save(assignedvehicleEntity);
                    Notification.show(EnumMessages.MESSAGES_SUCESS_SAVE.getMessage(), Notification.Type.HUMANIZED_MESSAGE);
                    refreshInformationGrid();
                    clearFields();
                    showFields(false);
                } else {
                    Notification.show(EnumMessages.EXIST_ASSIGNEDVEHICLE.getMessage(), Notification.Type.ERROR_MESSAGE);
                }
            } catch (Exception e) {
                Notification.show(EnumMessages.MESSAGES_ERROR_SAVE.getMessage(), Notification.Type.ERROR_MESSAGE);
            }
        }
    }

    private void refreshInformationGrid() {
        collectionsAssignedVehicles = controllerAssignedVehicle.findAllAssignedVehicle();
        dataProviderAssignedVehicles = DataProvider.ofCollection(collectionsAssignedVehicles);
        grid.setDataProvider(dataProviderAssignedVehicles);

    }

    private void buildFields() {
        fieldsLayout.addComponents(cmbVehicle, cmbUsers, txtInitDate, txtFinishDate);
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
        cmbVehicle.setEnabled(value);
        cmbUsers.setEnabled(value);
        txtInitDate.setEnabled(value);
        txtFinishDate.setEnabled(value);
    }

    private void clearFields() {
        action = "";
        cmbUsers.clear();
        cmbVehicle.clear();
        txtFinishDate.clear();
        txtInitDate.clear();
    }

    private void setLeftPanel() {
        principalLayout.setSizeFull();
        leftLayout.setSizeFull();
        createGrid();
        leftLayout.addComponent(grid);
        principalLayout.addComponent(leftLayout);
    }

    private void createGrid() {
        collectionsAssignedVehicles = controllerAssignedVehicle.findAllAssignedVehicle();
        dataProviderAssignedVehicles = DataProvider.ofCollection(collectionsAssignedVehicles);
        collectionsVehicles = controllerVehicle.findAllVehicle();
        collectionsUsers = controllerUser.findAllUsers();
        grid.setSizeFull();
        grid.setEnabled(true);
        grid.addColumn(AssignedvehicleEntity::getNameUser).setCaption(EnumLabel.USERNAME_LABEL.getLabel());
        grid.addColumn(AssignedvehicleEntity::getLicensePlate).setCaption(EnumLabel.LICENSEPLATE_LABEL.getLabel());
        grid.addColumn(AssignedvehicleEntity::getInitialdate).setCaption(EnumLabel.INIT_DATE_LABEL.getLabel());
        grid.addColumn(AssignedvehicleEntity::getFinishdate).setCaption(EnumLabel.FINISH_DATE_LABEL.getLabel());
        grid.setDataProvider(dataProviderAssignedVehicles);
        grid.addItemClickListener(new ItemClickListener<AssignedvehicleEntity>() {
            @Override
            public void itemClick(Grid.ItemClick<AssignedvehicleEntity> event) {
                assignedVehicleSelected = event.getItem();
                for (VehicleEntity vehicle : collectionsVehicles) {
                    if (vehicle.getIdvehicle() == event.getItem().getIdvehicle()) {
                        cmbVehicle.setSelectedItem(vehicle.getLicenseplate());
                        break;
                    }
                }

                for (UsersEntity usersEntity : collectionsUsers) {
                    if (usersEntity.getIdusers() == event.getItem().getIdusers()) {
                        cmbUsers.setSelectedItem(usersEntity.getUsername());
                        break;
                    }
                }
                txtFinishDate.setValue(assignedVehicleSelected.getFinishdate());
                txtInitDate.setValue(assignedVehicleSelected.getInitialdate());
            }
        });

    }

    private void showFields(Boolean value) {
        fieldsLayout.setVisible(value);
        buttonsSecondaryLayout.setVisible(value);
    }

    private boolean isValidationFieldEmpty(String textField) {
        boolean validation = false;
        if (textField == null || textField.isEmpty()) {

            validation = true;
        }
        return validation;
    }

    private boolean isValidationAllField(String message) {
        if (isValidationFieldEmpty(cmbUsers.getValue())) {
            Notification.show("Debe llenar el campo Usuario", Notification.Type.ERROR_MESSAGE);
            return true;
        } else if (isValidationFieldEmpty(cmbVehicle.getValue())) {
            Notification.show("Debe llenar el campo Usuario", Notification.Type.ERROR_MESSAGE);
            return true;
        } else if (txtInitDate.getValue() == null || ValidationsString.isEmptyOrNull(txtInitDate.getValue().toString())) {
            Notification.show("Debe llenar el campo Fecha Inicial", Notification.Type.ERROR_MESSAGE);
            return true;
        } else if (txtFinishDate.getValue() == null || ValidationsString.isEmptyOrNull(txtFinishDate.getValue().toString())) {
            Notification.show("Debe llenar el campo Fecha Final", Notification.Type.ERROR_MESSAGE);
            return true;
        }
        return false;
    }
}
