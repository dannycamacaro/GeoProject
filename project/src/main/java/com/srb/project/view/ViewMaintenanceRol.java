package com.srb.project.view;

import com.srb.project.controller.ControllerRol;
import com.srb.project.enumConstans.EnumMessages;
import com.srb.project.model.RolesEntity;
import com.srb.project.util.ValidationsString;
import com.vaadin.navigator.View;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import org.springframework.beans.factory.annotation.Autowired;

@UIScope
@SpringView(name = ViewMaintenanceRol.VIEW_NAME)

public class ViewMaintenanceRol extends VerticalLayout implements View {
    public static final String VIEW_NAME = "rol";
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
        Button deleteButton = new Button("Eliminar");
        Button editButton = new Button("Editar");
        Button cancelButton = new Button("Cancelar");

        rolLayout.addComponents(nameRolField, descriptionField, createButton, cancelButton);
        createButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                RolesEntity rolEntity = new RolesEntity();
                rolEntity.setNamerole(nameRolField.getValue());
                rolEntity.setDescriptionrole(descriptionField.getValue());
                rolEntity.setStatedelete((byte) 0);

                if (controllerRol.saveRol(rolEntity)) {
                    Notification.show("Guardado exitosamente!", Notification.Type.HUMANIZED_MESSAGE);
                } else {
                    Notification.show("Error al Guardar el Rol!", Notification.Type.ERROR_MESSAGE);
                }
            }
        });
        editButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                //TODO AJUSTAR LA VISTA
                RolesEntity rolEntity = new RolesEntity();
                if (!ValidationsString.isEmptyOrNull(descriptionField.getValue())) {
                    rolEntity = objectDummy();
                }

                if (controllerRol.updateRol(rolEntity)) {
                    Notification.show(EnumMessages.MESSAGE_SUCESS_DELETE.getMessage(), Notification.Type.HUMANIZED_MESSAGE);
                } else {
                    Notification.show(EnumMessages.MESSAGES_ERROR_SAVE.getMessage(), Notification.Type.ERROR_MESSAGE);
                }
            }
        });
        deleteButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                //TODO AJUSTAR LA VISTA
                RolesEntity rolEntity = new RolesEntity();
                if (!ValidationsString.isEmptyOrNull(descriptionField.getValue())) {
                    rolEntity.setDescriptionrole(descriptionField.getValue());
                }

                if (controllerRol.deleteRol(rolEntity.getDescriptionrole())) {
                    Notification.show(EnumMessages.MESSAGE_SUCESS_DELETE.getMessage(), Notification.Type.HUMANIZED_MESSAGE);
                } else {
                    Notification.show(EnumMessages.MESSAGES_ERROR_SAVE.getMessage(), Notification.Type.ERROR_MESSAGE);
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
        botones.addComponent(deleteButton);
        botones.addComponent(editButton);
        botones.addComponent(cancelButton);
        this.addComponent(rolLayout);
        this.setComponentAlignment(rolLayout, Alignment.MIDDLE_CENTER);
        this.addComponent(botones);
        this.setComponentAlignment(botones, Alignment.MIDDLE_CENTER);
    }
    private RolesEntity objectDummy(){
        RolesEntity rolesEntity = new RolesEntity();
        rolesEntity.setIdrol(1);
        rolesEntity.setNamerole("Administrador");
        rolesEntity.setDescriptionrole("Administrador del sistema");
        return rolesEntity;
    }
}
