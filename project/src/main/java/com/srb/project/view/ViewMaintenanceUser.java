package com.srb.project.view;


import com.srb.project.controller.ControllerRol;
import com.srb.project.controller.ControllerUser;
import com.srb.project.enumConstans.EnumLabel;
import com.srb.project.enumConstans.EnumMessages;
import com.srb.project.model.RolesEntity;
import com.srb.project.model.UsersEntity;
import com.srb.project.persister.ServicesRol;
import com.vaadin.data.provider.DataProvider;
import com.vaadin.data.provider.ListDataProvider;
import com.vaadin.navigator.View;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.*;
import com.vaadin.ui.components.grid.ItemClickListener;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.*;

@UIScope
@SpringView(name = ViewMaintenanceUser.VIEW_NAME)
public class ViewMaintenanceUser extends VerticalLayout implements View {

    public static final String VIEW_NAME = "user";
    private TextField txtUserName;
    private TextField txtPassword;
    private TextField txtFirstName;
    private TextField txtLastName;
    private TextField txtIdentityDocument;
    private TextField txtAge;
    private TextField txtPhoneNumber;
    private TextField txtEmail;
    private Button btnNew;
    private Button btnAccept;
    private Button btnEdit;
    private Button btnDelete;
    private Button btnCancel;
    private VerticalLayout leftPanel;
    private VerticalLayout rightPanel;
    private HorizontalLayout menuLayout;
    private HorizontalLayout principalLayout;
    private HorizontalLayout operationButtons;
    private HorizontalLayout operationButtonsFooter;
    private String action;
    private GridLayout gridLayout;
    private UsersEntity usersEntity;
    private Grid<UsersEntity> grid = new Grid<>();
    private ListDataProvider<UsersEntity> dataProvider;
    private Collection<UsersEntity> collectionUsers;
    private ComboBox<String> cmbRol;
    private Collection<String> nameRoles = new ArrayList<>();
    private Collection<RolesEntity> collectionRoles;


    @Autowired
    ControllerUser controllerUser;

    @Autowired
    ControllerRol controllerRol;

    @Autowired
    ServicesRol servicesRol;


    public ViewMaintenanceUser() {


    }

    @PostConstruct
    private void buildForm() {
        this.setSizeFull();
        this.setWidth("100%");
        this.setHeightUndefined();
        this.setSpacing(false);
        menuLayout = new HorizontalLayout();
        principalLayout = new HorizontalLayout();
        leftPanel = new VerticalLayout();
        rightPanel = new VerticalLayout();
        operationButtons = new HorizontalLayout();
        operationButtonsFooter = new HorizontalLayout();
        gridLayout = new GridLayout(2, 5);
        grid = new Grid<>();
        cmbRol = new ComboBox<>("Roles");
        action = "";
        createMenu();
        setPropertiesField();
        setLeftPanel();
        setRightPanel();
        principalLayout.setSizeFull();
        this.addComponent(principalLayout);
    }

    private void createMenu() {
        MenuBar menuBar = ViewMenu.buildMenu();
        menuLayout.addComponent(menuBar);
        menuLayout.setWidth("100%");
        menuLayout.setHeightUndefined();
        menuLayout.setComponentAlignment(menuBar, Alignment.TOP_CENTER);
        this.addComponent(menuLayout);
    }


    private void setPropertiesField() {


        txtUserName = new TextField(EnumLabel.USERNAME_LABEL.getLabel());
        txtPassword = new TextField(EnumLabel.PASSWORD_LABEL.getLabel());
        txtFirstName = new TextField(EnumLabel.FIRST_NAME_LABEL.getLabel());
        txtLastName = new TextField(EnumLabel.LAST_NAME_LABEL.getLabel());
        txtIdentityDocument = new TextField(EnumLabel.IDENTITY_DOCUMENT_LABEL.getLabel());
        txtAge = new TextField(EnumLabel.AGE_LABEL.getLabel());
        txtPhoneNumber = new TextField(EnumLabel.PHONE_NUMBER_LABEL.getLabel());
        txtEmail = new TextField(EnumLabel.EMAIL_LABEL.getLabel());
        btnNew = new Button(EnumLabel.REGISTRAR_LABEL.getLabel());
        btnAccept = new Button(EnumLabel.ACEPTAR_LABEL.getLabel());
        btnEdit = new Button(EnumLabel.EDITAR_LABEL.getLabel());
        btnDelete = new Button(EnumLabel.ELIMINAR_LABEL.getLabel());
        btnCancel = new Button(EnumLabel.CANCELAR_LABEL.getLabel());

        txtUserName.setRequiredIndicatorVisible(true);
        txtPassword.setRequiredIndicatorVisible(true);
        txtFirstName.setRequiredIndicatorVisible(true);
        txtLastName.setRequiredIndicatorVisible(true);
        txtIdentityDocument.setRequiredIndicatorVisible(true);
        txtAge.setRequiredIndicatorVisible(true);
        txtPhoneNumber.setRequiredIndicatorVisible(true);
        txtEmail.setRequiredIndicatorVisible(true);

    }

    private void setLeftPanel() {
        leftPanel.setSizeFull();
        loadInformationGrid();
        grid.setSizeFull();
        leftPanel.addComponent(grid);
        principalLayout.addComponent(leftPanel);
    }

    private void loadInformationGrid() {
        collectionUsers = controllerUser.findAllUsers();
        dataProvider = DataProvider.ofCollection(collectionUsers);

        grid.setEnabled(true);
        grid.addColumn(UsersEntity::getUsername).setCaption(EnumLabel.USERNAME_LABEL.getLabel());
        grid.addColumn(UsersEntity::getFirstname).setCaption(EnumLabel.FIRST_NAME_LABEL.getLabel());
        grid.addColumn(UsersEntity::getLastname).setCaption(EnumLabel.LAST_NAME_LABEL.getLabel());
        grid.setDataProvider(dataProvider);
        grid.addItemClickListener(new ItemClickListener<UsersEntity>() {
            @Override
            public void itemClick(Grid.ItemClick<UsersEntity> event) {
                usersEntity = event.getItem();
                txtUserName.setValue(usersEntity.getUsername());
                txtPassword.setValue(usersEntity.getPassword());
                txtFirstName.setValue(usersEntity.getFirstname());
                txtLastName.setValue(usersEntity.getLastname());
                txtIdentityDocument.setValue(usersEntity.getIdentitydocument());
                txtAge.setValue(String.valueOf(usersEntity.getAge()));
                txtPhoneNumber.setValue(usersEntity.getPhonenumber());
                txtEmail.setValue(usersEntity.getEmail());
                for (RolesEntity rol : collectionRoles) {
                    if (rol.getIdrol() == event.getItem().getIdrol()) {
                        cmbRol.setSelectedItem(rol.getNamerole());
                    }
                }
            }
        });
    }

    private void setRightPanel() {

        operationButtons.addComponent(btnNew);
        operationButtons.addComponent(btnEdit);
        operationButtons.addComponent(btnDelete);
        operationButtonsFooter.addComponent(btnAccept);
        operationButtonsFooter.addComponent(btnCancel);
        loadInformationComboxRol();
        fillGridLayout();
        newUserAction();
        editUserAction();
        deleteUserAction();
        hideFields();
        rightPanel.addComponent(operationButtons);
        rightPanel.addComponent(gridLayout);
        rightPanel.addComponent(operationButtonsFooter);
        rightPanel.setComponentAlignment(gridLayout, Alignment.MIDDLE_LEFT);
        principalLayout.addComponent(rightPanel);

    }

    private void editUserAction() {

        btnEdit.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                if (!isValidationAllField(EnumMessages.SELECT_USER.getMessage())) {
                    enabledGridLayout(true);
                    showContent();
                    action = "edit";
                    acceptUserAction();
                    cancelUserAction();
                }
            }
        });
    }

    private void deleteUserAction() {
        btnDelete.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                if (!isValidationAllField(EnumMessages.SELECT_USER.getMessage())) {
                    enabledGridLayout(false);
                    showContent();
                    action = "delete";
                    acceptUserAction();
                    cancelUserAction();
                }
            }
        });
    }

    private void newUserAction() {
        btnNew.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {

                visibleGridLayout(true);
                enabledGridLayout(true);
                visibleOperationButtonsFooter(true);
                emptySetValue();
                action = "new";
                acceptUserAction();
                cancelUserAction();
            }
        });
    }

    private void acceptUserAction() {

        btnAccept.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                if (action.equalsIgnoreCase("new")) {
                    processAddUser();
                } else if (action.equalsIgnoreCase("edit")) {
                    processUpdateUser();
                } else if (action.equalsIgnoreCase("delete")) {
                    processDeleteUser();
                }
            }
        });
    }

    private void processDeleteUser() {

        try {
            controllerUser.deleteUser(usersEntity);
            Notification.show(EnumMessages.MESSAGE_SUCESS_DELETE.getMessage(), Notification.Type.HUMANIZED_MESSAGE);
            refreshInformationGrid();
            emptySetValue();
            hideFields();
        } catch (Exception e) {
            Notification.show(EnumMessages.MESSAGES_ERROR_SAVE.getMessage(), Notification.Type.ERROR_MESSAGE);
        }
    }

    private void processUpdateUser() {
        try {
            usersEntity.setUsername(txtUserName.getValue());
            usersEntity.setPassword(txtPassword.getValue());
            usersEntity.setFirstname(txtFirstName.getValue());
            usersEntity.setLastname(txtLastName.getValue());
            usersEntity.setIdentitydocument(txtIdentityDocument.getValue());
            usersEntity.setAge(Integer.valueOf(txtAge.getValue()));
            usersEntity.setPhonenumber(txtPhoneNumber.getValue());
            usersEntity.setEmail(txtEmail.getValue());
            for (RolesEntity rolesEntity : collectionRoles) {
                if (rolesEntity.getNamerole().equalsIgnoreCase(cmbRol.getValue())) {
                    usersEntity.setIdrol(rolesEntity.getIdrol());
                    usersEntity.setIdrol(rolesEntity.getIdrol());
                    usersEntity.setRolesByIdrol(rolesEntity);
                    break;
                }
            }
            controllerUser.updateUser(usersEntity);
            Notification.show(EnumMessages.MESSAGE_SUCESS_UPDATE.getMessage(), Notification.Type.HUMANIZED_MESSAGE);
            refreshInformationGrid();
            emptySetValue();
            hideFields();
        } catch (Exception e) {
            Notification.show(EnumMessages.MESSAGES_ERROR_SAVE.getMessage(), Notification.Type.ERROR_MESSAGE);
        }

    }

    private void processAddUser() {
        UsersEntity entityUser = new UsersEntity();
        if (!isValidationAllField(EnumMessages.MESSAGE_REQUIRED_FIELD.getMessage())) {
            try {
                entityUser.setUsername(txtUserName.getValue());
                entityUser.setPassword(txtPassword.getValue());
                entityUser.setFirstname(txtFirstName.getValue());
                entityUser.setLastname(txtLastName.getValue());
                entityUser.setIdentitydocument(txtIdentityDocument.getValue());
                entityUser.setAge(Integer.valueOf(txtAge.getValue()));
                entityUser.setPhonenumber(txtPhoneNumber.getValue());
                entityUser.setEmail(txtEmail.getValue());
                for (RolesEntity rolesEntity : collectionRoles) {
                    if (rolesEntity.getNamerole().equalsIgnoreCase(cmbRol.getValue())) {
                        entityUser.setIdrol(rolesEntity.getIdrol());
                        entityUser.setIdrol(rolesEntity.getIdrol());
                        entityUser.setRolesByIdrol(rolesEntity);
                        break;
                    }
                }
                controllerUser.save(entityUser);
                Notification.show(EnumMessages.MESSAGES_SUCESS_SAVE.getMessage(), Notification.Type.HUMANIZED_MESSAGE);
                refreshInformationGrid();
                emptySetValue();
                hideFields();
            } catch (Exception e) {
                Notification.show(EnumMessages.MESSAGES_ERROR_SAVE.getMessage(), Notification.Type.ERROR_MESSAGE);
            }
        }
    }

    private boolean isValidationAllField(String message) {
        if (isValidationFieldEmpty(txtUserName)) {
            Notification.show(message, Notification.Type.ERROR_MESSAGE);
            return true;
        } else if (isValidationFieldEmpty(txtPassword)) {
            Notification.show(message, Notification.Type.ERROR_MESSAGE);
            return true;
        } else if (isValidationFieldEmpty(txtFirstName)) {
            Notification.show(message, Notification.Type.ERROR_MESSAGE);
            return true;
        } else if (isValidationFieldEmpty(txtLastName)) {
            Notification.show(message, Notification.Type.ERROR_MESSAGE);
            return true;
        } else if (isValidationFieldEmpty(txtIdentityDocument)) {
            Notification.show(message, Notification.Type.ERROR_MESSAGE);
            return true;
        } else if (isValidationFieldEmpty(txtAge)) {
            Notification.show(message, Notification.Type.ERROR_MESSAGE);
            return true;
        } else if (isValidationFieldEmpty(txtPhoneNumber)) {
            Notification.show(message, Notification.Type.ERROR_MESSAGE);
            return true;
        } else if (isValidationFieldEmpty(txtEmail)) {
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

    private void hideFields() {
        visibleGridLayout(false);
        visibleOperationButtonsFooter(false);
    }

    private void showContent() {
        visibleGridLayout(true);
        visibleOperationButtonsFooter(true);
    }

    private void refreshInformationGrid() {

        collectionUsers = controllerUser.findAllUsers();
        dataProvider = DataProvider.ofCollection(collectionUsers);
        grid.setDataProvider(dataProvider);
    }

    private void enabledGridLayout(boolean visible) {
        gridLayout.setEnabled(visible);
    }

    private void visibleGridLayout(boolean visible) {
        gridLayout.setVisible(visible);

    }

    private void cancelUserAction() {
        btnCancel.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {

                enabledGridLayout(false);
                hideFields();
                emptySetValue();
            }
        });
    }

    private void emptySetValue() {
        action = "";
        txtUserName.clear();
        txtPassword.clear();
        txtFirstName.clear();
        txtLastName.clear();
        txtIdentityDocument.clear();
        txtAge.clear();
        txtPhoneNumber.clear();
        txtEmail.clear();
        cmbRol.clear();
    }

    private void visibleOperationButtonsFooter(boolean visible) {
        operationButtonsFooter.setVisible(visible);

    }


    private void fillGridLayout() {

        gridLayout.addComponents(txtUserName, txtPassword, txtFirstName, txtLastName, txtIdentityDocument, txtAge, txtPhoneNumber, txtEmail, cmbRol);
    }

    private void loadInformationComboxRol() {
        collectionRoles = controllerRol.findAllRoles();
        for (RolesEntity rolesEntity : collectionRoles) {
            nameRoles.add(rolesEntity.getNamerole());
        }
        cmbRol.setItems(nameRoles);
    }


}

