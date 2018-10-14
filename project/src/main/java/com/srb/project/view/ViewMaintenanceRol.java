package com.srb.project.view;

import com.srb.project.controller.ControllerRol;
import com.srb.project.model.RolesEntity;
import com.srb.project.persister.ServicesRol;
import com.vaadin.navigator.View;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

@UIScope
@SpringView(name = ViewMaintenanceRol.VIEW_NAME)

public class ViewMaintenanceRol extends VerticalLayout implements View {
    public static final String  VIEW_NAME = "rol";
    private static final String FIELD_WIDTH = "250px";

    @Autowired
    ControllerRol controllerRol;

    public ViewMaintenanceRol() {

        FormLayout rolLayout = new FormLayout();

        TextField nameRolField = new TextField();
        nameRolField.setCaption("Nombre del rol:");
        nameRolField.setPlaceholder("administrador");
        nameRolField.setStyleName(ValoTheme.DATEFIELD_LARGE);
        TextField descriptionField = new TextField();
        descriptionField.setCaption("Descripcion: ");
        descriptionField.setPlaceholder("Administrador del sistema");
        descriptionField.setStyleName(ValoTheme.TEXTFIELD_LARGE);
        Button createButton = new Button("Registrar");
        Button cancelButton = new Button("Cancelar");

        rolLayout.addComponents(nameRolField, descriptionField,createButton,cancelButton);
        createButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                RolesEntity rolEntity  = new RolesEntity();
                rolEntity.setNamerole(nameRolField.getValue());
                rolEntity.setDescriptionrole(descriptionField.getValue());
                rolEntity.setStatedelete((byte)0);
                if (controllerRol.saveRol(rolEntity)){
                    Notification.show("Guardado exitosamente!", Notification.Type.HUMANIZED_MESSAGE);
                }else {
                    Notification.show("Error al Guardar el Rol!", Notification.Type.ERROR_MESSAGE);
                }
            }
        });
        cancelButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                nameRolField.clear();
                descriptionField.clear();
            }
        });

        HorizontalLayout botones = new HorizontalLayout();

        botones.addComponent(createButton);
        botones.addComponent(cancelButton);
        this.addComponent(rolLayout);
        this.setComponentAlignment(rolLayout, Alignment.MIDDLE_CENTER);
        this.addComponent(botones);
        this.setComponentAlignment(botones, Alignment.MIDDLE_CENTER);
    }
}
