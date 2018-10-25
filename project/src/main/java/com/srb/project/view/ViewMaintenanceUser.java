package com.srb.project.view;


import com.srb.project.controller.ControllerRol;
import com.srb.project.controller.ControllerUser;
import com.srb.project.enumConstans.EnumLabel;
import com.srb.project.model.UsersEntity;
import com.vaadin.navigator.View;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.crudui.crud.CrudListener;
import org.vaadin.crudui.crud.CrudOperation;
import org.vaadin.crudui.crud.impl.GridCrud;
import org.vaadin.crudui.form.impl.form.factory.GridLayoutCrudFormFactory;
import org.vaadin.crudui.layout.impl.HorizontalSplitCrudLayout;

import java.util.Collection;

@UIScope
@SpringView(name = ViewMaintenanceUser.VIEW_NAME)
public class ViewMaintenanceUser extends VerticalLayout implements View {

    public static final String VIEW_NAME = "user";
    @Autowired
    ControllerUser controllerUser;

    @Autowired
    ControllerRol controllerRol;



    public ViewMaintenanceUser() {
        buildForm();

    }

    private void buildForm() {
        this.setSizeFull();
        this.setWidth("100%");

        FormLayout formUser = new FormLayout();
        GridCrud<UsersEntity> crud = new GridCrud<>(UsersEntity.class, new HorizontalSplitCrudLayout());
        GridLayoutCrudFormFactory<UsersEntity> formFactory = new GridLayoutCrudFormFactory<>(UsersEntity.class, 2, 2);

        crud.getGrid().setColumns("username","firstname","lastname");
        crud.getGrid().getColumn("username").setCaption("Nombre del usuario");
        crud.getGrid().getColumn("firstname").setCaption("Nombres");
        crud.getGrid().getColumn("lastname").setCaption("Apellido");


        formFactory.setVisibleProperties(CrudOperation.READ, "username","firstname","lastname");
        formFactory.setVisibleProperties(CrudOperation.ADD, "username","password","firstname","lastname","identitydocument","age","phonenumber","email" );
        formFactory.setVisibleProperties(CrudOperation.UPDATE, "username","password","firstname","lastname","identitydocument","age","phonenumber","email");
        formFactory.setVisibleProperties(CrudOperation.DELETE, "username","firstname","lastname");
        formFactory.setFieldCaptions(CrudOperation.ADD, "Nombre del usuario","Password", "Nombres","Apellidos","Documento de identidad","Edad","Numero de telefono","Correo electronico");
        formFactory.setFieldCaptions(CrudOperation.UPDATE, "Nombre del usuario","Password", "Nombres","Apellidos","Documento de identidad","Edad","Numero de telefono","Correo electronico");
        formFactory.setFieldCaptions(CrudOperation.DELETE, "Nombre del usuario", "Nombres","Apellidos");
        formFactory.setButtonCaption(CrudOperation.ADD, EnumLabel.REGISTRAR_LABEL.getLabel());
        formFactory.setButtonCaption(CrudOperation.UPDATE, EnumLabel.EDITAR_LABEL.getLabel());
        formFactory.setButtonCaption(CrudOperation.DELETE, EnumLabel.ELIMINAR_LABEL.getLabel());
        formFactory.setFieldType("password", PasswordField.class);



        /*formFactory.setFieldType("gender", com.vaadin.ui.ComboBox.class);
        String[] gender = {"mail", "femail"};
        formFactory.setFieldProvider("gender", () -> new ComboBox("gender", Arrays.asList(gender)));
        formFactory.setFieldCreationListener("gender", field -> {
            com.vaadin.ui.ComboBox comboBox = ( com.vaadin.ui.ComboBox) field;
            comboBox.addItem(gender[0]);
            comboBox.addItem(gender[1]);
        });*/
//        formFactory.setFieldProvider("mainGroup",new ComboBoxProvider<RolesEntity>("Main Group", controllerRol.findAllRoles()));

        crud.setCrudListener(new CrudListener<UsersEntity>() {
            @Override
            public Collection<UsersEntity> findAll() {
                return controllerUser.findAllUsers();
            }

            @Override
            public UsersEntity add(UsersEntity user) {
                controllerUser.save(user);
                return user;
            }

            @Override
            public UsersEntity update(UsersEntity user) {

                controllerUser.updateUser(user);
                return user;
            }

            @Override
            public void delete(UsersEntity user) {
                controllerUser.deleteUser(user);
            }
        });

        crud.setCrudFormFactory(formFactory);
        this.addComponent(crud);


    }

}

