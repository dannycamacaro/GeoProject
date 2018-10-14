package com.srb.project.view;


import com.vaadin.navigator.View;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.*;

import javax.annotation.PostConstruct;

@UIScope
@SpringView(name = ViewMaintenanceUser.VIEW_NAME)
public class ViewMaintenanceUser extends VerticalLayout implements View{
    public static final String VIEW_NAME="maintenanceuser";

    @PostConstruct
    protected void init() {
        BuildForm();
    }
    public ViewMaintenanceUser() {
        BuildForm();

    }

    private void BuildForm() {
        this.setSizeFull();
        this.setWidth("100%");

        FormLayout formUser =  new FormLayout();
        setWidth("40%");
        setHeight("50");

        TextField txtIdDocument = new TextField();
        TextField txtFirstName = new TextField();
        TextField txtLastName = new TextField();
        TextField txtAge = new TextField();
        TextField txtPhoneNumber = new TextField();
        TextField txtEmail = new TextField();
        ComboBox cmbRol = new ComboBox();
        TextField txtUserName = new TextField();
        TextField txtPassword = new TextField();
        ComboBox cmbVehicle = new ComboBox();

        formUser.addComponents(txtIdDocument,txtFirstName,txtLastName,txtAge,txtPhoneNumber,
                txtEmail,cmbRol,txtUserName,txtPassword,cmbVehicle);

        addComponent(formUser);
        setComponentAlignment(formUser, Alignment.MIDDLE_CENTER);
    }
}
