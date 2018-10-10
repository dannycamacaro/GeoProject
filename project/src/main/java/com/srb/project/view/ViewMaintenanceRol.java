package com.srb.project.view;

import com.srb.project.ViewUsers;
import com.srb.project.model.RolesEntity;
import com.vaadin.navigator.View;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;

import javax.annotation.PostConstruct;
import javax.swing.*;

@UIScope
@SpringView(name = ViewMaintenanceRol.VIEW_NAME)

public class ViewMaintenanceRol extends VerticalLayout implements View {
    public static final String  VIEW_NAME = "rol";
    private static final String FIELD_WIDTH = "250px";

    @PostConstruct
    protected void init() {
        Label title = new Label("Rol");
        title.addStyleName(ValoTheme.MENU_ITEM);

        Button view1 = new Button("Main", e -> getUI().getPage().setLocation("http://localhost:8080/project"));
        view1.addStyleName(ValoTheme.BUTTON_LINK);
        this.addComponent(view1);
        Label users = new Label("Users");
        title.addStyleName(ValoTheme.MENU_TITLE);

        Button viewUsers = new Button("Users", e -> getUI().getNavigator().navigateTo(ViewUsers.VIEW_NAME));
        viewUsers.addStyleName(ValoTheme.BUTTON_LINK);
        this.addComponent(viewUsers);

        FormLayout nameLayout = new FormLayout();

        TextField nameRolField = new TextField();
        nameRolField.setCaption("Nombre del rol:");
        nameRolField.setPlaceholder("administrador");
        nameRolField.setWidth(FIELD_WIDTH);
        TextField descriptionField = new TextField();
        descriptionField.setCaption("Descripcion: ");
        descriptionField.setPlaceholder("Administrador del sistema");
        descriptionField.setWidth(FIELD_WIDTH);
        Button createButton = new Button("Registrar");
        Button cancelButton = new Button("Cancelar");

        createButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                nameLayout.addComponents(nameRolField, descriptionField,createButton,cancelButton);
                RolesEntity rolesEntity  = new RolesEntity();
                rolesEntity.setNamerole(nameRolField.getValue());
                rolesEntity.setDescriptionrole(descriptionField.getValue());
                rolesEntity.setIdrol(3);
            }
        });




        this.addComponent(nameLayout);




    }
}
