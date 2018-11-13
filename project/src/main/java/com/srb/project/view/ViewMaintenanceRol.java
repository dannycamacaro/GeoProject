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

import java.util.Collection;

@UIScope
@SpringView(name = ViewMaintenanceRol.VIEW_NAME)
public class ViewMaintenanceRol extends HorizontalLayout implements View {
    public static final String VIEW_NAME = "rol";
    private TextField txtDescription;
    private TextField txtNameRol;
    private Button btnNew;
    private Button btnAccept;
    private Button btnEdit;
    private Button btnDelete;
    private Button btnCancel;
    private VerticalLayout leftPanel;
    private VerticalLayout rightPanel;
    private HorizontalLayout operationButtons;
    private HorizontalLayout operationButtonsFooter;
    private HorizontalLayout fieldForm;
    private String action;
    private RolesEntity roles;
    Grid<RolesEntity> grid = new Grid<>();
    ListDataProvider<RolesEntity> dataProvider;
    private Collection<RolesEntity> collectionRol;

    @Autowired
    ControllerRol controllerRol;


    public ViewMaintenanceRol() {

    }

    public void buildForm() {
        leftPanel = new VerticalLayout();
        rightPanel = new VerticalLayout();
        operationButtons = new HorizontalLayout();
        operationButtonsFooter = new HorizontalLayout();
        fieldForm = new HorizontalLayout();
        grid = new Grid<>();
        action = "";

        setPropertiesField();
        setLeftPanel();
        setRightPanel();
        this.setSizeFull();
        this.setWidth("100%");
        this.addComponent(leftPanel);
        this.addComponent(rightPanel);
        this.setSizeFull();


    }

    private void setLeftPanel() {
        leftPanel.setWidth("100%");
        leftPanel.setHeight("100%");
        leftPanel.setMargin(new MarginInfo(true, false, false, true));
        leftPanel.setSpacing(false);
        loadInformationGrid();
        leftPanel.addComponent(grid);
    }

    private void setRightPanel() {

        rightPanel.setHeight("100%");
        rightPanel.setWidth("100%");
        operationButtons.addComponent(btnNew);
        operationButtons.addComponent(btnEdit);
        operationButtons.addComponent(btnDelete);
        operationButtonsFooter.addComponent(btnAccept);
        operationButtonsFooter.addComponent(btnCancel);
        fieldForm.addComponent(txtNameRol);
        fieldForm.addComponent(txtDescription);
        newRolAction();
        editRolAction();
        deleteRolAction();
        rightPanel.addComponent(operationButtons);
        rightPanel.addComponent(fieldForm);
        rightPanel.addComponent(operationButtonsFooter);

    }

    private void setPropertiesField() {
        txtDescription = new TextField(EnumLabel.DESCRIPTION_ROL_LABEL.getLabel());
        txtNameRol = new TextField(EnumLabel.NAME_ROL_LABEL.getLabel());
        txtDescription.setVisible(false);
        txtNameRol.setVisible(false);
        btnNew = new Button(EnumLabel.REGISTRAR_LABEL.getLabel());
        btnAccept = new Button(EnumLabel.ACEPTAR_LABEL.getLabel());
        btnEdit = new Button(EnumLabel.EDITAR_LABEL.getLabel());
        btnDelete = new Button(EnumLabel.ELIMINAR_LABEL.getLabel());
        btnCancel = new Button(EnumLabel.CANCELAR_LABEL.getLabel());
        btnAccept.setVisible(false);
        btnCancel.setVisible(false);
        txtNameRol.setRequiredIndicatorVisible(true);
        txtDescription.setRequiredIndicatorVisible(true);
    }

    private void loadInformationGrid() {
        collectionRol = controllerRol.findAllRoles();
        dataProvider = DataProvider.ofCollection(collectionRol);

        grid.setEnabled(true);
        grid.addColumn(RolesEntity::getNamerole).setCaption(EnumLabel.NAME_ROL_LABEL.getLabel());
        grid.addColumn(RolesEntity::getDescriptionrole).setCaption(EnumLabel.DESCRIPTION_ROL_LABEL.getLabel());
        grid.setDataProvider(dataProvider);
        grid.addItemClickListener(new ItemClickListener<RolesEntity>() {
            @Override
            public void itemClick(Grid.ItemClick<RolesEntity> event) {
                grid.getUI().setData(event.getItem());
                roles = event.getItem();
                txtDescription.setValue(event.getItem().getDescriptionrole());
                txtNameRol.setValue(event.getItem().getNamerole());
            }
        });
    }

    @Override
    public void attach() {

        buildForm();

    }


    private void newRolAction() {
        btnNew.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                visibleComponents();
                enabledComponents();
                emptySetValue();
                action = "new";
                acceptRolAction();
                cancelRolAction();
            }
        });
    }

    private void acceptRolAction() {
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
    }

    private void refreshInformationGrid() {
        collectionRol = controllerRol.findAllRoles();
        dataProvider = DataProvider.ofCollection(collectionRol);
        grid.setDataProvider(dataProvider);
    }

    private void editRolAction() {
        btnEdit.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                visibleComponents();
                action = "edit";
                acceptRolAction();
                cancelRolAction();
            }
        });

    }

    private void deleteRolAction() {
        btnDelete.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                visibleComponents();
                notEnabledComponents();
                action = "delete";
                acceptRolAction();
                cancelRolAction();
            }
        });
    }

    private void cancelRolAction() {
        btnCancel.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {

                notEnabledComponents();
                notVisibleComponents();
                emptySetValue();
            }
        });
    }

    private void processAddRol() {
        RolesEntity rolesEntity = new RolesEntity();
        if(!isValidationFieldEmpty()){
        rolesEntity.setNamerole(txtNameRol.getValue());
        rolesEntity.setDescriptionrole(txtDescription.getValue());
        controllerRol.save(rolesEntity);
        Notification.show(EnumMessages.MESSAGES_SAVE.getMessage(), Notification.Type.HUMANIZED_MESSAGE);
        refreshInformationGrid();
        emptySetValue();
        notVisibleComponents();
        }
    }

    private void processUpdateRol() {
        txtDescription.setVisible(true);
        txtNameRol.setVisible(true);
        RolesEntity rolesEntity = new RolesEntity();
        rolesEntity.setIdrol(roles.getIdrol());
        rolesEntity.setNamerole(txtNameRol.getValue());
        rolesEntity.setDescriptionrole(txtDescription.getValue());
        controllerRol.updateRol(rolesEntity);
        Notification.show(EnumMessages.MESSAGES_EDIT.getMessage(), Notification.Type.HUMANIZED_MESSAGE);
        refreshInformationGrid();
        emptySetValue();
        notVisibleComponents();
    }

    private void processDeleteRol() {
        txtDescription.setVisible(true);
        txtNameRol.setVisible(true);
        RolesEntity rolesEntity = new RolesEntity();
        rolesEntity.setIdrol(roles.getIdrol());
        controllerRol.deleteRol(rolesEntity);
        Notification.show(EnumMessages.MESSAGE_SUCESS_DELETE.getMessage(), Notification.Type.HUMANIZED_MESSAGE);
        refreshInformationGrid();
        emptySetValue();
        enabledComponents();
        notVisibleComponents();
    }

    private void emptySetValue() {
        action = "";
        txtDescription.setValue("");
        txtNameRol.setValue("");
    }

    private void enabledComponents() {
        txtNameRol.setEnabled(true);
        txtDescription.setEnabled(true);
    }

    private void notEnabledComponents() {
        txtNameRol.setEnabled(false);
        txtDescription.setEnabled(false);
    }

    private void notVisibleComponents() {
        txtDescription.setVisible(false);
        txtNameRol.setVisible(false);
        btnAccept.setVisible(false);
        btnCancel.setVisible(false);

    }

    private void visibleComponents() {
        txtDescription.setVisible(true);
        txtNameRol.setVisible(true);
        btnAccept.setVisible(true);
        btnCancel.setVisible(true);
    }

    private boolean isValidationFieldEmpty() {
        boolean validation = false;
        if (txtNameRol.getValue().isEmpty() || txtDescription.getValue().isEmpty()) {
            Notification.show("Debe de introducir todos los campos", Notification.Type.ERROR_MESSAGE);
            validation = true;
        }
        return validation;
    }
}
