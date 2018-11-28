package com.srb.project.view;


import com.srb.project.controller.ControllerAssignedRoutes;
import com.srb.project.controller.ControllerDevice;
import com.srb.project.controller.ControllerRoutes;
import com.srb.project.enumConstans.EnumLabel;
import com.srb.project.enumConstans.EnumMessages;
import com.srb.project.model.AssignedroutesEntity;
import com.srb.project.model.DeviceEntity;
import com.srb.project.model.RoutesEntity;
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
@SpringView(name = ViewMaintenanceAssignedRoutes.VIEW_NAME)
public class ViewMaintenanceAssignedRoutes extends VerticalLayout implements View {

    public static final String VIEW_NAME = "assignedroutes";
    private Grid<AssignedroutesEntity> grid = new Grid<>();

    //Layouts
    private HorizontalLayout menuLayout = new HorizontalLayout();
    private HorizontalLayout principalLayout = new HorizontalLayout();
    private Panel principalPanel = new Panel("Asignar Ruta");
    private VerticalLayout leftlayout = new VerticalLayout();
    private VerticalLayout rightLayout = new VerticalLayout();
    private HorizontalLayout buttonsPrincipalLayout = new HorizontalLayout();
    private GridLayout fieldsLayout = new GridLayout(2, 1);
    private HorizontalLayout buttonsSecondaryLayout = new HorizontalLayout();
    private MenuBar menuBar;

    //fields
    ComboBox<String> cmbDevice = new ComboBox<>(EnumLabel.DEVICE_LABEL.getLabel());
    ComboBox<String> cmbRoutes = new ComboBox<>(EnumLabel.ROUTES_LABEL.getLabel());

    //Buttons
    private Button btnNew = new Button(EnumLabel.REGISTRAR_LABEL.getLabel());
    private Button btnAccept = new Button(EnumLabel.ACEPTAR_LABEL.getLabel());
    private Button btnEdit = new Button(EnumLabel.EDITAR_LABEL.getLabel());
    private Button btnDelete = new Button(EnumLabel.ELIMINAR_LABEL.getLabel());
    private Button btnCancel = new Button(EnumLabel.CANCELAR_LABEL.getLabel());


    @Autowired
    ControllerDevice controllerDevice;

    @Autowired
    ControllerRoutes controllerRoutes;

    @Autowired
    ControllerAssignedRoutes controllerAssignedRoutes;

    private Collection<AssignedroutesEntity> collectionsAssignedRoutes;
    private ListDataProvider<AssignedroutesEntity> dataProviderAssignedRoutes;
    private AssignedroutesEntity assignedRoutesSelected;
    private Collection<DeviceEntity> collectionsDevices;
    private Collection<String> arrayDevices = new ArrayList<>();
    private Collection<RoutesEntity> collectionsRoutes;
    private Collection<String> arrayUsers = new ArrayList<>();
    private String action;

    public ViewMaintenanceAssignedRoutes() {

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

        cmbRoutes.setRequiredIndicatorVisible(true);
        cmbDevice.setRequiredIndicatorVisible(true);

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
        collectionsDevices = controllerDevice.findAllDevice();
        collectionsRoutes = controllerRoutes.findAllRoutes();
        for (DeviceEntity deviceEntity : collectionsDevices) {
            arrayDevices.add(deviceEntity.getPhonenumber());
        }
        cmbDevice.setItems(arrayDevices);
        for (RoutesEntity routesEntity : collectionsRoutes) {

            arrayUsers.add(routesEntity.getNameroutes());
        }
        cmbRoutes.setItems(arrayUsers);
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
                    processAddAssignedRoutes();
                } else if (action.equalsIgnoreCase("edit")) {
                    processUpdateAssignedRoutes();
                } else if (action.equalsIgnoreCase("delete")) {
                    processDeleteAssignedRoutes();
                }
            }
        });

        buttonsSecondaryLayout.addComponents(btnCancel, btnAccept);
        rightLayout.addComponent(buttonsSecondaryLayout);
    }

    private void processDeleteAssignedRoutes() {

        try {
            controllerAssignedRoutes.deleteAssignedRoutes(assignedRoutesSelected);
            Notification.show(EnumMessages.MESSAGE_SUCESS_DELETE.getMessage(), Notification.Type.HUMANIZED_MESSAGE);
            refreshInformationGrid();
            clearFields();
            showFields(false);
            enableFields(true);
        } catch (Exception e) {
            Notification.show(EnumMessages.MESSAGES_ERROR_SAVE.getMessage(), Notification.Type.ERROR_MESSAGE);
        }

    }

    private void processUpdateAssignedRoutes() {

        try {

            for (DeviceEntity deviceEntity : collectionsDevices) {
                if (deviceEntity.getPhonenumber().equalsIgnoreCase(cmbDevice.getValue())) {
                    assignedRoutesSelected.setIddevice(deviceEntity.getIddevice());
                    assignedRoutesSelected.setDeviceByIddevice(deviceEntity);
                    break;
                }
            }
            for (RoutesEntity routesEntity : collectionsRoutes) {
                if (routesEntity.getNameroutes().equalsIgnoreCase(cmbRoutes.getValue())) {
                    assignedRoutesSelected.setIdroutes(routesEntity.getIdroutes());
                    assignedRoutesSelected.setRoutesByIdroutes(routesEntity);
                    break;
                }
            }


            controllerAssignedRoutes.updateAssigneRoutes(assignedRoutesSelected);
            Notification.show(EnumMessages.MESSAGE_SUCESS_UPDATE.getMessage(), Notification.Type.HUMANIZED_MESSAGE);
            refreshInformationGrid();
            clearFields();
            showFields(false);
        } catch (Exception e) {
            Notification.show(EnumMessages.MESSAGES_ERROR_SAVE.getMessage(), Notification.Type.ERROR_MESSAGE);
        }

    }

    private void processAddAssignedRoutes() {

        AssignedroutesEntity assignedroutesEntity = new AssignedroutesEntity();
        if (!isValidationAllField(EnumMessages.MESSAGE_REQUIRED_FIELD.getMessage())) {
            try {

                for (DeviceEntity deviceEntity : collectionsDevices) {
                    if (deviceEntity.getPhonenumber().equalsIgnoreCase(cmbDevice.getValue())) {
                        assignedroutesEntity.setIddevice(deviceEntity.getIddevice());
                        assignedroutesEntity.setDeviceByIddevice(deviceEntity);
                        break;
                    }
                }
                for (RoutesEntity routesEntity : collectionsRoutes) {
                    if (routesEntity.getNameroutes().equalsIgnoreCase(cmbRoutes.getValue())) {
                        assignedroutesEntity.setIdroutes(routesEntity.getIdroutes());
                        assignedroutesEntity.setRoutesByIdroutes(routesEntity);
                        break;
                    }
                }
                controllerAssignedRoutes.save(assignedroutesEntity);
                Notification.show(EnumMessages.MESSAGES_SUCESS_SAVE.getMessage(), Notification.Type.HUMANIZED_MESSAGE);
                refreshInformationGrid();
                clearFields();
                showFields(false);
            } catch (Exception e) {
                Notification.show(EnumMessages.MESSAGES_ERROR_SAVE.getMessage(), Notification.Type.ERROR_MESSAGE);
            }
        }
    }

    private void refreshInformationGrid() {
        collectionsAssignedRoutes = controllerAssignedRoutes.findAllAssignedRoutes();
        dataProviderAssignedRoutes = DataProvider.ofCollection(collectionsAssignedRoutes);
        grid.setDataProvider(dataProviderAssignedRoutes);

    }

    private void buildFields() {
        fieldsLayout.addComponents(cmbDevice, cmbRoutes);
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
        cmbDevice.setEnabled(value);
        cmbRoutes.setEnabled(value);
    }

    private void clearFields() {
        action = "";
        cmbRoutes.clear();
        cmbDevice.clear();
    }

    private void setLeftPanel() {
        principalLayout.setSizeFull();
        leftlayout.setSizeFull();
        createGrid();
        leftlayout.addComponent(grid);
        principalLayout.addComponent(leftlayout);
    }

    private void createGrid() {
        collectionsAssignedRoutes = controllerAssignedRoutes.findAllAssignedRoutes();
        dataProviderAssignedRoutes = DataProvider.ofCollection(collectionsAssignedRoutes);
        collectionsDevices = controllerDevice.findAllDevice();
        collectionsRoutes = controllerRoutes.findAllRoutes();
        grid.setSizeFull();
        grid.setEnabled(true);
        grid.addColumn(AssignedroutesEntity::getPhoneNumber).setCaption(EnumLabel.PHONE_NUMBER_LABEL.getLabel());
        grid.addColumn(AssignedroutesEntity::getNameRoute).setCaption(EnumLabel.ROUTE_NAME_LABEL.getLabel());
        grid.setDataProvider(dataProviderAssignedRoutes);
        grid.addItemClickListener(new ItemClickListener<AssignedroutesEntity>() {
            @Override
            public void itemClick(Grid.ItemClick<AssignedroutesEntity> event) {
                assignedRoutesSelected = event.getItem();
                for (DeviceEntity deviceEntity : collectionsDevices) {
                    if (deviceEntity.getIddevice() == event.getItem().getIddevice()) {
                        cmbDevice.setSelectedItem(deviceEntity.getPhonenumber());
                        break;
                    }
                }

                for (RoutesEntity routesEntity : collectionsRoutes) {
                    if (routesEntity.getIdroutes() == event.getItem().getIdroutes()) {
                        cmbRoutes.setSelectedItem(routesEntity.getNameroutes());
                        break;
                    }
                }
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
        if (isValidationFieldEmpty(cmbRoutes.getValue())) {
            Notification.show(message, Notification.Type.ERROR_MESSAGE);
            return true;
        } else if (isValidationFieldEmpty(cmbDevice.getValue())) {
            Notification.show(message, Notification.Type.ERROR_MESSAGE);
            return true;
        }
        return false;
    }
}
