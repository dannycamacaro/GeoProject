package com.srb.project.view;


import com.srb.project.controller.ControllerRoutes;
import com.srb.project.enumConstans.EnumLabel;
import com.srb.project.enumConstans.EnumMessages;
import com.srb.project.model.RoutesEntity;
import com.vaadin.data.provider.DataProvider;
import com.vaadin.data.provider.ListDataProvider;
import com.vaadin.navigator.View;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.*;
import com.vaadin.ui.components.grid.ItemClickListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.crudui.crud.impl.GridCrud;


import javax.annotation.PostConstruct;
import java.util.Collection;


@UIScope
@SpringView(name = ViewMaintenanceRoutes.VIEW_NAME)
public class ViewMaintenanceRoutes extends VerticalLayout implements View {
    @Autowired
    ControllerRoutes controllerRoutes;

    Collection<RoutesEntity> collectionRoutes;

    public static final String VIEW_NAME = "routes";
    private GridCrud<RoutesEntity> crud;
    //Buttons
    private Button btnNew = new Button(EnumLabel.REGISTRAR_LABEL.getLabel());
    private Button btnAccept = new Button(EnumLabel.ACEPTAR_LABEL.getLabel());
    private Button btnEdit = new Button(EnumLabel.EDITAR_LABEL.getLabel());
    private Button btnDelete = new Button(EnumLabel.ELIMINAR_LABEL.getLabel());
    private Button btnCancel = new Button(EnumLabel.CANCELAR_LABEL.getLabel());
    private HorizontalLayout menuLayout = new HorizontalLayout();
    private HorizontalLayout principalLayout = new HorizontalLayout();
    private VerticalLayout leftLayout = new VerticalLayout();
    private VerticalLayout rightLayout = new VerticalLayout();
    private GridLayout fieldsLayout = new GridLayout(1, 1);
    private HorizontalLayout principalButtonsLayout = new HorizontalLayout();
    private HorizontalLayout secondaryButtonsLayout = new HorizontalLayout();
    private Grid<RoutesEntity> grid = new Grid<>();
    private ListDataProvider<RoutesEntity> dataProvider;
    private RoutesEntity routesEntitySelected;
    private TextField txtNameRoute = new TextField(EnumLabel.NAME_ROUTE.getLabel());
    private TextField txtDescription = new TextField(EnumLabel.DESCRIPTION_ROUTE.getLabel());
    private String action;

    public ViewMaintenanceRoutes() {

    }

    private void createMenu() {
        MenuBar menuBar = ViewMenu.buildMenu();
        menuLayout.addComponent(menuBar);
        menuLayout.setWidth("100%");
        menuLayout.setHeightUndefined();
        menuLayout.setComponentAlignment(menuBar, Alignment.TOP_CENTER);
        this.addComponent(menuLayout);
    }

    @PostConstruct
    private void buildForm() {
        this.setWidth("100%");
        this.setHeightUndefined();
        this.setSpacing(false);
        createMenu();
        hideFields();
        createLeftLayout();
        createRightLayout();
        principalLayout.setSizeFull();
        this.addComponent(principalLayout);
    }

    private void createLeftLayout() {
        leftLayout.setSizeFull();
        leftLayout.addComponent(grid);
        refreshInformationGrid();
        grid.addColumn(RoutesEntity::getNameroutes).setCaption(EnumLabel.NAME_ROUTE.getLabel());
        grid.addColumn(RoutesEntity::getDescription).setCaption(EnumLabel.DESCRIPTION_ROUTE.getLabel());
        grid.setDataProvider(dataProvider);

        grid.addItemClickListener(new ItemClickListener<RoutesEntity>() {
            @Override
            public void itemClick(Grid.ItemClick<RoutesEntity> event) {
                routesEntitySelected = event.getItem();
                txtNameRoute.setValue(routesEntitySelected.getNameroutes());
                txtDescription.setValue(routesEntitySelected.getDescription());
            }
        });
        grid.setDataProvider(dataProvider);
        grid.setSizeFull();
        principalLayout.addComponent(leftLayout);
    }

    private void createRightLayout() {
        principalButtonsLayout.addComponents(btnNew, btnEdit, btnDelete);
        txtNameRoute.setRequiredIndicatorVisible(true);
        txtDescription.setRequiredIndicatorVisible(true);

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
                if (!txtNameRoute.isEmpty() && !txtDescription.isEmpty()) {
                    showFields();
                    action = "edit";
                } else {
                    Notification.show(EnumMessages.SELECT_ROUTES.getMessage(), Notification.Type.ERROR_MESSAGE);
                }
            }
        });

        btnDelete.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                if (!txtNameRoute.isEmpty() && !txtDescription.isEmpty()) {
                    showFields();
                    action = "delete";
                    enableFields(false);
                } else {
                    Notification.show(EnumMessages.SELECT_ROUTES.getMessage(), Notification.Type.ERROR_MESSAGE);
                }
            }
        });

        rightLayout.addComponent(principalButtonsLayout);
        fieldsLayout.addComponents(txtNameRoute, txtDescription);
        rightLayout.addComponents(fieldsLayout);
        rightLayout.setComponentAlignment(fieldsLayout, Alignment.MIDDLE_LEFT);

        secondaryButtonsLayout.addComponents(btnCancel, btnAccept);

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
                    processAddRoute();
                } else if (action.equalsIgnoreCase("edit")) {
                    processUpdateRoute();
                } else if (action.equalsIgnoreCase("delete")) {
                    processDeleteRoute();
                }
            }
        });
        rightLayout.addComponent(secondaryButtonsLayout);

        principalLayout.addComponent(rightLayout);
    }

    private void processDeleteRoute() {
        try {
            controllerRoutes.deleteRoutes(routesEntitySelected);
            showMessage(EnumMessages.MESSAGE_SUCESS_DELETE.getMessage(), Notification.Type.HUMANIZED_MESSAGE);
            hideContent();
            clearFields();
            refreshInformationGrid();
            enableFields(true);
        } catch (Exception e) {
            showMessage(EnumMessages.MESSAGES_ERROR_DELETE.getMessage(), Notification.Type.HUMANIZED_MESSAGE);
        }
    }

    private void processUpdateRoute() {
        try {
            if ((txtNameRoute.getValue().equalsIgnoreCase(routesEntitySelected.getNameroutes())) || (controllerRoutes.validateRoutes(txtNameRoute.getValue()))) {
                routesEntitySelected.setStatedelete(Byte.valueOf("1"));
                routesEntitySelected.setNameroutes(txtNameRoute.getValue());
                routesEntitySelected.setDescription(txtDescription.getValue());

                controllerRoutes.updateRoutes(routesEntitySelected);
                hideContent();
                clearFields();
                refreshInformationGrid();
                Notification.show(EnumMessages.MESSAGE_SUCESS_UPDATE.getMessage(), Notification.Type.HUMANIZED_MESSAGE);
            } else {
                Notification.show(EnumMessages.EXIST_ROUTES.getMessage(), Notification.Type.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            Notification.show(EnumMessages.MESSAGES_ERROR_UPDATE.getMessage(), Notification.Type.ERROR_MESSAGE);
            hideContent();
        }
    }

    private void processAddRoute() {
        RoutesEntity routesEntity = new RoutesEntity();
        try {
            if (!isValidationAllField(EnumMessages.MESSAGE_REQUIRED_FIELD.getMessage())) {

                routesEntity.setStatedelete(Byte.valueOf("1"));
                routesEntity.setNameroutes(txtNameRoute.getValue());
                routesEntity.setDescription(txtDescription.getValue());
                if (controllerRoutes.validateRoutes(routesEntity.getNameroutes())) {
                    controllerRoutes.save(routesEntity);
                    hideContent();
                    clearFields();
                    refreshInformationGrid();
                    Notification.show(EnumMessages.MESSAGES_SUCESS_SAVE.getMessage(), Notification.Type.HUMANIZED_MESSAGE);
                }else{
                    Notification.show(EnumMessages.EXIST_ROUTES.getMessage(), Notification.Type.ERROR_MESSAGE);
                }
            }
        } catch (Exception e) {
            Notification.show(EnumMessages.MESSAGES_ERROR_SAVE.getMessage(), Notification.Type.ERROR_MESSAGE);
            hideContent();
        }
    }


    private void hideContent() {
        fieldsLayout.setVisible(false);
        secondaryButtonsLayout.setVisible(false);
    }

    private void clearFields() {
        txtNameRoute.clear();
        txtDescription.clear();
    }

    private void showFields() {
        fieldsLayout.setVisible(true);
        secondaryButtonsLayout.setVisible(true);
    }

    private void hideFields() {
        fieldsLayout.setVisible(false);
        secondaryButtonsLayout.setVisible(false);
    }

    private void loadInformationGrid() {
        collectionRoutes = controllerRoutes.findAllRoutes();
        dataProvider = DataProvider.ofCollection(collectionRoutes);

        grid.setEnabled(true);
        grid.addColumn(RoutesEntity::getNameroutes).setCaption(EnumLabel.NAME_ROL_LABEL.getLabel());
        grid.addColumn(RoutesEntity::getDescription).setCaption(EnumLabel.DESCRIPTION_ROL_LABEL.getLabel());
        grid.setDataProvider(dataProvider);
        grid.addItemClickListener(new ItemClickListener<RoutesEntity>() {
            @Override
            public void itemClick(Grid.ItemClick<RoutesEntity> event) {
                grid.getUI().setData(event.getItem());
                routesEntitySelected = event.getItem();
                txtNameRoute.setValue(event.getItem().getNameroutes());
                txtDescription.setValue(event.getItem().getDescription());
            }
        });
    }


    private void refreshInformationGrid() {
        collectionRoutes = controllerRoutes.findAllRoutes();
        dataProvider = DataProvider.ofCollection(collectionRoutes);
        grid.setDataProvider(dataProvider);
    }

    private void showMessage(String mensaje, Notification.Type type) {
        Notification.show(mensaje, type);
    }

    private boolean isValidationAllField(String message) {
        if (isValidationFieldEmpty(txtDescription.getValue())) {
            Notification.show(message, Notification.Type.ERROR_MESSAGE);
            return true;
        } else if (isValidationFieldEmpty(txtNameRoute.getValue())) {
            Notification.show(message, Notification.Type.ERROR_MESSAGE);
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

    private void enableFields(boolean value) {
        txtDescription.setEnabled(value);
        txtNameRoute.setEnabled(value);
    }

}
