package com.srb.project.view;

import com.srb.project.controller.ControllerRol;
import com.srb.project.enumConstans.EnumLabel;
import com.srb.project.enumConstans.EnumMessages;
import com.srb.project.model.RolesEntity;
import com.vaadin.data.provider.DataProvider;
import com.vaadin.data.provider.ListDataProvider;
import com.vaadin.navigator.View;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.*;
import com.vaadin.ui.components.grid.ItemClickListener;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.Collection;

@UIScope
@SpringView(name = ViewMaintenanceRol.VIEW_NAME)
public class ViewMaintenanceRol extends VerticalLayout implements View {
    public static final String VIEW_NAME = "rol";
    private TextField txtDescription;
    private TextField txtNameRol;
    private Button btnNew;
    private Button btnAccept;
    private Button btnEdit;
    private Button btnDelete;
    private Button btnCancel;
    //Layouts
    private HorizontalLayout menuLayout = new HorizontalLayout();
    private HorizontalLayout principalLayout = new HorizontalLayout();
    private VerticalLayout leftlayout = new VerticalLayout();
    private VerticalLayout rightLayout = new VerticalLayout();
    private HorizontalLayout buttonsPrincipalLayout = new HorizontalLayout();
    private GridLayout fieldsLayout = new GridLayout(2, 2);
    private HorizontalLayout buttonsSecondaryLayout = new HorizontalLayout();
    private MenuBar menuBar;

    private String action;
    private RolesEntity roles;
    private Grid<RolesEntity> grid = new Grid<>();
    private ListDataProvider<RolesEntity> dataProvider;
    private Collection<RolesEntity> collectionRol;

    @Autowired
    ControllerRol controllerRol;


    public ViewMaintenanceRol() {

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
        setLeftPanel();
        setRightPanel();

        this.addComponents(menuLayout, principalLayout);
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
                    processAddRol();
                } else if (action.equalsIgnoreCase("edit")) {
                    processUpdateRol();
                } else if (action.equalsIgnoreCase("delete")) {
                    processDeleteRol();
                }
            }
        });

        buttonsSecondaryLayout.addComponents(btnCancel, btnAccept);
        rightLayout.addComponent(buttonsSecondaryLayout);
    }

    private void buildFields() {
        fieldsLayout.addComponents(txtNameRol, txtDescription);
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
                if (!txtDescription.isEmpty() && !txtNameRol.isEmpty()) {
                    showFields(true);
                    action = "edit";
                } else {
                    Notification.show(EnumMessages.SELECT_ROL.getMessage(), Notification.Type.ERROR_MESSAGE);
                }
            }
        });
        btnDelete.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                if (!txtDescription.isEmpty() && !txtNameRol.isEmpty()) {
                    showFields(true);
                    action = "delete";
                    enableFields(false);
                } else {
                    Notification.show(EnumMessages.SELECT_ROL.getMessage(), Notification.Type.ERROR_MESSAGE);
                }
            }
        });
        buttonsPrincipalLayout.addComponents(btnNew, btnEdit, btnDelete);
        rightLayout.addComponent(buttonsPrincipalLayout);
    }

    private void enableFields(boolean value) {
        txtDescription.setEnabled(value);
        txtNameRol.setEnabled(value);
    }

    private void setPropertiesField() {
        txtDescription = new TextField(EnumLabel.DESCRIPTION_ROL_LABEL.getLabel());
        txtNameRol = new TextField(EnumLabel.NAME_ROL_LABEL.getLabel());
        btnNew = new Button(EnumLabel.REGISTRAR_LABEL.getLabel());
        btnAccept = new Button(EnumLabel.ACEPTAR_LABEL.getLabel());
        btnEdit = new Button(EnumLabel.EDITAR_LABEL.getLabel());
        btnDelete = new Button(EnumLabel.ELIMINAR_LABEL.getLabel());
        btnCancel = new Button(EnumLabel.CANCELAR_LABEL.getLabel());
        txtNameRol.setRequiredIndicatorVisible(true);
        txtDescription.setRequiredIndicatorVisible(true);
    }

    private void loadInformationGrid() {
        collectionRol = controllerRol.findAllRoles();
        dataProvider = DataProvider.ofCollection(collectionRol);
        grid.setDataProvider(dataProvider);
    }

    private void refreshInformationGrid() {
        collectionRol = controllerRol.findAllRoles();
        dataProvider = DataProvider.ofCollection(collectionRol);
        grid.setDataProvider(dataProvider);
    }


    private void processAddRol() {
        RolesEntity rolesEntity = new RolesEntity();
        if (!isValidationFieldEmpty()) {
            try {
                rolesEntity.setNamerole(txtNameRol.getValue());
                rolesEntity.setDescriptionrole(txtDescription.getValue());
                if (controllerRol.validateRol(rolesEntity.getNamerole())) {
                    controllerRol.save(rolesEntity);
                    Notification.show(EnumMessages.MESSAGES_SUCESS_SAVE.getMessage(), Notification.Type.HUMANIZED_MESSAGE);
                    refreshInformationGrid();
                    clearFields();
                    showFields(false);
                } else {
                    Notification.show(EnumMessages.EXIST_ROL.getMessage(), Notification.Type.ERROR_MESSAGE);
                }
            } catch (Exception e) {
                Notification.show(EnumMessages.MESSAGES_ERROR_SAVE.getMessage(), Notification.Type.ERROR_MESSAGE);
            }
        }
    }

    private void processUpdateRol() {
        try {
            if ((txtNameRol.getValue().equalsIgnoreCase(roles.getNamerole())) || (controllerRol.validateRol(txtNameRol.getValue()))) {
                roles.setNamerole(txtNameRol.getValue());
                roles.setDescriptionrole(txtDescription.getValue());
                controllerRol.updateRol(roles);
                Notification.show(EnumMessages.MESSAGES_EDIT.getMessage(), Notification.Type.HUMANIZED_MESSAGE);
                refreshInformationGrid();
                clearFields();
                showFields(false);
            } else {
                Notification.show(EnumMessages.EXIST_ROL.getMessage(), Notification.Type.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            Notification.show(EnumMessages.MESSAGES_ERROR_SAVE.getMessage(), Notification.Type.ERROR_MESSAGE);
        }
    }

    private void processDeleteRol() {
        try {
            controllerRol.deleteRol(roles);
            Notification.show(EnumMessages.MESSAGE_SUCESS_DELETE.getMessage(), Notification.Type.HUMANIZED_MESSAGE);
            refreshInformationGrid();
            clearFields();
            showFields(false);
            enableFields(true);
        } catch (Exception e) {
            Notification.show(EnumMessages.MESSAGES_ERROR_SAVE.getMessage(), Notification.Type.ERROR_MESSAGE);
        }
    }

    private void clearFields() {
        action = "";
        txtDescription.clear();
        txtNameRol.clear();
    }

    private void showFields(Boolean value) {
        fieldsLayout.setVisible(value);
        buttonsSecondaryLayout.setVisible(value);
    }


    private boolean isValidationFieldEmpty() {
        boolean validation = false;
        if (txtNameRol.getValue().isEmpty() || txtDescription.getValue().isEmpty()) {
            Notification.show(EnumMessages.MESSAGE_REQUIRED_FIELD.getMessage(), Notification.Type.ERROR_MESSAGE);
            validation = true;
        }
        return validation;
    }

    private void createGrid() {
        collectionRol = controllerRol.findAllRoles();
        dataProvider = DataProvider.ofCollection(collectionRol);
        grid.setSizeFull();
        grid.setEnabled(true);
        grid.addColumn(RolesEntity::getNamerole).setCaption(EnumLabel.NAME_ROL_LABEL.getLabel());
        grid.addColumn(RolesEntity::getDescriptionrole).setCaption(EnumLabel.DESCRIPTION_ROL_LABEL.getLabel());
        grid.setDataProvider(dataProvider);
        grid.addItemClickListener(new ItemClickListener<RolesEntity>() {
            @Override
            public void itemClick(Grid.ItemClick<RolesEntity> event) {
                roles = event.getItem();
                txtDescription.setValue(event.getItem().getDescriptionrole());
                txtNameRol.setValue(event.getItem().getNamerole());
            }
        });

    }

}
