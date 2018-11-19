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
    //fields
    private TextField txtUserName = new TextField(EnumLabel.USERNAME_LABEL.getLabel());
    private PasswordField txtPassword = new PasswordField(EnumLabel.PASSWORD_LABEL.getLabel());
    private TextField txtFirstName = new TextField(EnumLabel.FIRST_NAME_LABEL.getLabel());
    private TextField txtLastName = new TextField(EnumLabel.LAST_NAME_LABEL.getLabel());
    private TextField txtIdentityDocument = new TextField(EnumLabel.IDENTITY_DOCUMENT_LABEL.getLabel());
    private TextField txtAge = new TextField(EnumLabel.AGE_LABEL.getLabel());
    private TextField txtPhoneNumber = new TextField(EnumLabel.PHONE_NUMBER_LABEL.getLabel());
    private TextField txtEmail = new TextField(EnumLabel.EMAIL_LABEL.getLabel());
    private ComboBox<String> cmbRol = new ComboBox<>(EnumLabel.ROL_LABEL.getLabel());
    ;
    //Buttons
    private Button btnNew = new Button(EnumLabel.REGISTRAR_LABEL.getLabel());
    private Button btnAccept = new Button(EnumLabel.ACEPTAR_LABEL.getLabel());
    private Button btnEdit = new Button(EnumLabel.EDITAR_LABEL.getLabel());
    private Button btnDelete = new Button(EnumLabel.ELIMINAR_LABEL.getLabel());
    private Button btnCancel = new Button(EnumLabel.CANCELAR_LABEL.getLabel());
    private MenuBar menuBar;
    //Layouts
    private VerticalLayout leftLayout = new VerticalLayout();
    private VerticalLayout rightLayout = new VerticalLayout();
    private HorizontalLayout principalLayout = new HorizontalLayout();
    private HorizontalLayout operationButtons = new HorizontalLayout();
    private HorizontalLayout operationButtonsFooter = new HorizontalLayout();
    private HorizontalLayout buttonsSecondaryLayout = new HorizontalLayout();
    private HorizontalLayout menuLayout = new HorizontalLayout();
    private HorizontalLayout buttonsPrincipalLayout = new HorizontalLayout();
    private GridLayout fieldsLayout = new GridLayout(2, 5);

    private String action;
    private UsersEntity usersEntitySelect;
    private Grid<UsersEntity> grid = new Grid<>();
    private ListDataProvider<UsersEntity> dataProvider;
    private Collection<UsersEntity> collectionUsers;

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
        this.setWidth("100%");
        this.setHeightUndefined();
        if (menuBar == null)
            menuBar = ViewMenu.buildMenu();
        menuLayout.addComponent(menuBar);
        fieldsLayout.setSpacing(true);
        setPropertiesField();
        setLeftPanel();
        setRightPanel();


        this.addComponents(menuBar, principalLayout);
        showFields(false);
        this.setComponentAlignment(menuBar, Alignment.TOP_CENTER);
    }


    private void setPropertiesField() {

        txtUserName.setRequiredIndicatorVisible(true);
        txtPassword.setRequiredIndicatorVisible(true);
        txtFirstName.setRequiredIndicatorVisible(true);
        txtLastName.setRequiredIndicatorVisible(true);
        txtIdentityDocument.setRequiredIndicatorVisible(true);
        txtAge.setRequiredIndicatorVisible(true);
        txtPhoneNumber.setRequiredIndicatorVisible(true);
        txtEmail.setRequiredIndicatorVisible(true);
        cmbRol.setRequiredIndicatorVisible(true);

    }

    private void setLeftPanel() {
        principalLayout.setSizeFull();
        leftLayout.setSizeFull();
        createGrid();
        leftLayout.addComponent(grid);
        principalLayout.addComponent(leftLayout);
    }

    private void createGrid() {
        collectionUsers = controllerUser.findAllUsers();
        dataProvider = DataProvider.ofCollection(collectionUsers);

        grid.setEnabled(true);
        grid.addColumn(UsersEntity::getUsername).setCaption(EnumLabel.USERNAME_LABEL.getLabel());
        grid.addColumn(UsersEntity::getFirstname).setCaption(EnumLabel.FIRST_NAME_LABEL.getLabel());
        grid.addColumn(UsersEntity::getLastname).setCaption(EnumLabel.LAST_NAME_LABEL.getLabel());
        grid.addColumn(UsersEntity::getIdentitydocument).setCaption(EnumLabel.IDENTITY_DOCUMENT_LABEL.getLabel());
        grid.setDataProvider(dataProvider);
        grid.addItemClickListener(new ItemClickListener<UsersEntity>() {
            @Override
            public void itemClick(Grid.ItemClick<UsersEntity> event) {
                usersEntitySelect = event.getItem();
                txtUserName.setValue(usersEntitySelect.getUsername());
                txtPassword.setValue(usersEntitySelect.getPassword());
                txtFirstName.setValue(usersEntitySelect.getFirstname());
                txtLastName.setValue(usersEntitySelect.getLastname());
                txtIdentityDocument.setValue(usersEntitySelect.getIdentitydocument());
                txtAge.setValue(String.valueOf(usersEntitySelect.getAge()));
                txtPhoneNumber.setValue(usersEntitySelect.getPhonenumber());
                txtEmail.setValue(usersEntitySelect.getEmail());
                for (RolesEntity rol : collectionRoles) {
                    if (rol.getIdrol() == event.getItem().getIdrol()) {
                        cmbRol.setSelectedItem(rol.getNamerole());
                    }
                }
            }
        });
    }

    private void setRightPanel() {

        rightLayout.setSizeFull();
        loadInformationCombox();
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
                    processAddUser();
                } else if (action.equalsIgnoreCase("edit")) {
                    processUpdateUser();
                } else if (action.equalsIgnoreCase("delete")) {
                    processDeleteUser();
                }
            }
        });

        buttonsSecondaryLayout.addComponents(btnCancel, btnAccept);
        rightLayout.addComponent(buttonsSecondaryLayout);
    }

    private void buildFields() {
        fieldsLayout.addComponents(txtUserName, txtPassword, txtFirstName, txtLastName, txtIdentityDocument, txtAge, txtPhoneNumber, txtEmail, cmbRol);
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

    private void clearFields() {

        action = "";
        txtAge.clear();
        txtEmail.clear();
        txtFirstName.clear();
        txtIdentityDocument.clear();
        txtLastName.clear();
        txtPassword.clear();
        txtUserName.clear();
        txtPhoneNumber.clear();
        cmbRol.clear();
    }

    private void enableFields(boolean value) {

        txtAge.setEnabled(value);
        txtEmail.setEnabled(value);
        txtFirstName.setEnabled(value);
        txtIdentityDocument.setEnabled(value);
        txtLastName.setEnabled(value);
        txtPassword.setEnabled(value);
        txtUserName.setEnabled(value);
        txtPhoneNumber.setEnabled(value);
        cmbRol.setEnabled(value);

    }

    private void processDeleteUser() {

        try {
            controllerUser.deleteUser(usersEntitySelect);
            Notification.show(EnumMessages.MESSAGE_SUCESS_DELETE.getMessage(), Notification.Type.HUMANIZED_MESSAGE);
            refreshInformationGrid();
            clearFields();
            showFields(false);
            enableFields(true);
        } catch (Exception e) {
            Notification.show(EnumMessages.MESSAGES_ERROR_SAVE.getMessage(), Notification.Type.ERROR_MESSAGE);
        }
    }

    private void processUpdateUser() {
        try {
            if ((txtUserName.getValue().equalsIgnoreCase(usersEntitySelect.getUsername())) || (controllerUser.validateUserByUserName(txtUserName.getValue()))) {
                if ((txtIdentityDocument.getValue().equalsIgnoreCase(usersEntitySelect.getIdentitydocument())) || (controllerUser.validateUserByIdentityDocument(txtIdentityDocument.getValue()))) {
                    usersEntitySelect.setUsername(txtUserName.getValue());
                    usersEntitySelect.setPassword(txtPassword.getValue());
                    usersEntitySelect.setFirstname(txtFirstName.getValue());
                    usersEntitySelect.setLastname(txtLastName.getValue());
                    usersEntitySelect.setIdentitydocument(txtIdentityDocument.getValue());
                    usersEntitySelect.setAge(Integer.valueOf(txtAge.getValue()));
                    usersEntitySelect.setPhonenumber(txtPhoneNumber.getValue());
                    usersEntitySelect.setEmail(txtEmail.getValue());
                    for (RolesEntity rolesEntity : collectionRoles) {
                        if (rolesEntity.getNamerole().equalsIgnoreCase(cmbRol.getValue())) {
                            usersEntitySelect.setIdrol(rolesEntity.getIdrol());
                            usersEntitySelect.setIdrol(rolesEntity.getIdrol());
                            usersEntitySelect.setRolesByIdrol(rolesEntity);
                            break;
                        }
                    }
                    controllerUser.updateUser(usersEntitySelect);
                    Notification.show(EnumMessages.MESSAGE_SUCESS_UPDATE.getMessage(), Notification.Type.HUMANIZED_MESSAGE);
                    refreshInformationGrid();
                    clearFields();
                    showFields(false);
                } else {
                    Notification.show(EnumMessages.EXIST_IDENTITYDOCUMENT.getMessage(), Notification.Type.ERROR_MESSAGE);
                }
            } else {
                Notification.show(EnumMessages.EXIST_USERNAME.getMessage(), Notification.Type.ERROR_MESSAGE);
            }
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
                if (controllerUser.validateUserByUserName(entityUser.getUsername())) {
                    if (controllerUser.validateUserByIdentityDocument(entityUser.getIdentitydocument())) {
                        controllerUser.save(entityUser);
                        Notification.show(EnumMessages.MESSAGES_SUCESS_SAVE.getMessage(), Notification.Type.HUMANIZED_MESSAGE);
                        refreshInformationGrid();
                        clearFields();
                        showFields(false);
                    } else {

                        Notification.show(EnumMessages.EXIST_IDENTITYDOCUMENT.getMessage(), Notification.Type.ERROR_MESSAGE);
                    }
                } else {
                    Notification.show(EnumMessages.EXIST_USERNAME.getMessage(), Notification.Type.ERROR_MESSAGE);

                }
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


    private void refreshInformationGrid() {

        collectionUsers = controllerUser.findAllUsers();
        dataProvider = DataProvider.ofCollection(collectionUsers);
        grid.setDataProvider(dataProvider);
    }

    private void visibleGridLayout(boolean visible) {
        fieldsLayout.setVisible(visible);

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

    private void loadInformationCombox() {
        collectionRoles = controllerRol.findAllRoles();
        for (RolesEntity rolesEntity : collectionRoles) {
            nameRoles.add(rolesEntity.getNamerole());
        }
        cmbRol.setItems(nameRoles);
    }

    private void showFields(Boolean value) {
        fieldsLayout.setVisible(value);
        buttonsSecondaryLayout.setVisible(value);
    }


}

