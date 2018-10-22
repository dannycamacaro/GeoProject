package com.srb.project.view;


import com.srb.project.enumConstans.EnumLabel;
import com.vaadin.navigator.View;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.*;

import javax.annotation.PostConstruct;

@UIScope
@SpringView(name = ViewMaintenanceUser.VIEW_NAME)
public class ViewMaintenanceUser extends VerticalLayout implements View {
    public static final String VIEW_NAME = "user";

    public ViewMaintenanceUser() {
        buildForm();

    }

    private void buildForm() {
        this.setSizeFull();
        this.setWidth("100%");

        FormLayout formUser = new FormLayout();
        HorizontalLayout botones = new HorizontalLayout();
        setWidth("40%");
        setHeight("50");

        TextField txtIdDocument = new TextField();
        TextField txtFirstName = new TextField();
        TextField txtLastName = new TextField();
        TextField txtAge = new TextField();
        TextField txtPhoneNumber = new TextField();
        TextField txtEmail = new TextField();
        TextField txtUserName = new TextField();
        TextField txtPassword = new TextField();
        ComboBox cmbRol = new ComboBox();
        ComboBox cmbVehicle = new ComboBox();
        Button buttonCreate = new Button(EnumLabel.REGISTRAR_LABEL.getLabel());
        Button buttonDelete = new Button(EnumLabel.ELIMINAR_LABEL.getLabel());
        Button buttonEdit = new Button(EnumLabel.EDITAR_LABEL.getLabel());
        Button buttonCancel = new Button(EnumLabel.CANCELAR_LABEL.getLabel());

        formUser.addComponents(txtIdDocument, txtFirstName, txtLastName, txtAge, txtPhoneNumber,
                txtEmail, cmbRol, txtUserName, txtPassword, cmbVehicle);


        botones.addComponent(buttonCreate);
        botones.addComponent(buttonDelete);
        botones.addComponent(buttonEdit);
        botones.addComponent(buttonCancel);

        this.addComponent(formUser);
        this.setComponentAlignment(formUser, Alignment.MIDDLE_CENTER);
//        addComponent(botones);
//        this.setComponentAlignment(botones, Alignment.MIDDLE_CENTER);
    }
}
