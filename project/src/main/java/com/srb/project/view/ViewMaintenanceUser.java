package com.srb.project.view;


import com.srb.project.controller.ControllerRol;
import com.srb.project.controller.ControllerUser;
import com.srb.project.enumConstans.EnumLabel;
import com.srb.project.model.RolesEntity;
import com.srb.project.model.UsersEntity;
import com.srb.project.persister.ServicesRol;
import com.vaadin.navigator.View;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.crudui.crud.CrudListener;
import org.vaadin.crudui.crud.CrudOperation;
import org.vaadin.crudui.crud.impl.GridCrud;
import org.vaadin.crudui.form.impl.field.provider.ComboBoxProvider;
import org.vaadin.crudui.form.impl.form.factory.GridLayoutCrudFormFactory;
import org.vaadin.crudui.layout.impl.HorizontalSplitCrudLayout;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.*;

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

        GridCrud<UsersEntity> crud = new GridCrud<>(UsersEntity.class, new HorizontalSplitCrudLayout());
        GridLayoutCrudFormFactory<UsersEntity> formFactory = new GridLayoutCrudFormFactory<>(UsersEntity.class, 2, 2);
        GridLayoutCrudFormFactory<RolesEntity>  formFactory2= new GridLayoutCrudFormFactory<>(RolesEntity.class, 2, 2);


        crud.getGrid().setColumns("username","firstname","lastname","nameRol");
        crud.getGrid().getColumn("username").setCaption("Nombre del usuario");
        crud.getGrid().getColumn("firstname").setCaption("Nombres");
        crud.getGrid().getColumn("lastname").setCaption("Apellido");
        crud.getGrid().getColumn("nameRol").setCaption("Nombre del rol");


        formFactory.setVisibleProperties(CrudOperation.READ, "username","firstname","lastname");
        formFactory.setVisibleProperties(CrudOperation.ADD, "username","password","firstname","lastname","identitydocument","age","phonenumber","email","listRoles" );
        formFactory.setVisibleProperties(CrudOperation.UPDATE, "username","password","firstname","lastname","identitydocument","age","phonenumber","email");
        formFactory.setVisibleProperties(CrudOperation.DELETE, "username","firstname","lastname");
        formFactory.setFieldCaptions(CrudOperation.ADD, "Nombre del usuario","Password", "Nombres","Apellidos","Documento de identidad","Edad","Numero de telefono","Correo electronico","Roles");
        formFactory.setFieldCaptions(CrudOperation.UPDATE, "Nombre del usuario","Password", "Nombres","Apellidos","Documento de identidad","Edad","Numero de telefono","Correo electronico");
        formFactory.setFieldCaptions(CrudOperation.DELETE, "Nombre del usuario", "Nombres","Apellidos");
        formFactory.setButtonCaption(CrudOperation.ADD, EnumLabel.REGISTRAR_LABEL.getLabel());
        formFactory.setButtonCaption(CrudOperation.UPDATE, EnumLabel.EDITAR_LABEL.getLabel());
        formFactory.setButtonCaption(CrudOperation.DELETE, EnumLabel.ELIMINAR_LABEL.getLabel());
        formFactory.setFieldType("password", PasswordField.class);



        formFactory.setFieldType("listRoles", com.vaadin.ui.ComboBox.class);
        ArrayList<String> roles = new ArrayList<>();
        roles.add("Adminitrador");
        roles.add("Chofer");
        formFactory.setFieldProvider("listRoles", () -> new ComboBox("listRoles",roles));
        formFactory.setFieldCreationListener("listRoles", field -> {
            com.vaadin.ui.ComboBox comboBox = ( com.vaadin.ui.ComboBox) field;
            comboBox.setItems(roles.get(0));
            comboBox.setItems(roles.get(1));
        });
//        formFactory.setFieldProvider("mainGroup",new ComboBoxProvider<RolesEntity>("Main Group", controllerRol.findAllRoles()));
//
        crud.setCrudListener(new CrudListener<UsersEntity>() {
            @Override
            public Collection<UsersEntity> findAll() {
                Collection<UsersEntity> usersEntities = controllerUser.findAllUsers();
                Collection<UsersEntity> usersEntitiesView = new ArrayList<>();
                Iterator<UsersEntity> entityIterator = usersEntities.iterator();

                if (usersEntities != null) {
                    while (entityIterator.hasNext()) {
                        UsersEntity usersEntity = entityIterator.next();
                        if (usersEntity.getRolesByIdrol() != null && usersEntity.getRolesByIdrol().getNamerole() != null) {

                            usersEntity.setNameRol(usersEntity.getRolesByIdrol().getNamerole());
                        }

                        usersEntitiesView.add(usersEntity);
                    }
                }
                return usersEntitiesView;
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

