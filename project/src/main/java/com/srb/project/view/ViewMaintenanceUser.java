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
import org.vaadin.crudui.form.impl.form.factory.GridLayoutCrudFormFactory;
import org.vaadin.crudui.layout.impl.HorizontalSplitCrudLayout;

import javax.annotation.PostConstruct;

import java.util.*;

@UIScope
@SpringView(name = ViewMaintenanceUser.VIEW_NAME)
public class ViewMaintenanceUser extends VerticalLayout implements View {

    public static final String VIEW_NAME = "user";
    private HorizontalSplitCrudLayout horizontalSplitCrudLayout;
    private GridCrud<UsersEntity> crud;
    private GridLayoutCrudFormFactory<UsersEntity> formFactory;
//    private GridCrud<UsersEntity> crud = new GridCrud<>(UsersEntity.class, new HorizontalSplitCrudLayout());
//    private GridLayoutCrudFormFactory<UsersEntity> formFactory = new GridLayoutCrudFormFactory<>(UsersEntity.class, 2, 2);

    @Autowired
    ControllerUser controllerUser;

    @Autowired
    ControllerRol controllerRol;

    @Autowired
    ServicesRol servicesRol;

    Collection roles;

    @PostConstruct
    void init() {

        formFactory.setFieldType("listRoles", com.vaadin.ui.ComboBox.class);
        roles = servicesRol.findAllRoles();
        ArrayList<String> stringArrayList = new ArrayList<>();

        formFactory.setFieldProvider("listRoles", () -> new ComboBox("listRoles", stringArrayList));
        formFactory.setFieldCreationListener("listRoles", field -> {
            com.vaadin.ui.ComboBox comboBox = (com.vaadin.ui.ComboBox) field;
            Iterator iterator = roles.iterator();

            while (iterator.hasNext()) {
                RolesEntity rolesEntity = (RolesEntity) iterator.next();
                stringArrayList.add(rolesEntity.getDescriptionrole());

            }
            comboBox.setItems(stringArrayList);
        });

    }

    public ViewMaintenanceUser() {
        horizontalSplitCrudLayout = new HorizontalSplitCrudLayout();
        horizontalSplitCrudLayout.setFormCaption(CrudOperation.DELETE,EnumLabel.ELIMINAR_REGISTRO_LABEL.getLabel());
        crud = new GridCrud<>(UsersEntity.class, horizontalSplitCrudLayout);
        formFactory = new GridLayoutCrudFormFactory<>(UsersEntity.class, 2, 2);
        buildForm();

    }

    private void buildForm() {
        this.setSizeFull();
        this.setWidth("100%");

        loadSetColumns();
        loadSetVisibleProperties();
        loadSetFieldCaptions();
        loadSetButtonCaption();
        loadMessagesForm();
        actionButtons();
        formFactory.setFieldType("password", PasswordField.class);
        crud.setCrudFormFactory(formFactory);
        this.addComponent(crud);


    }


    private void loadSetColumns() {

        crud.getGrid().setColumns("username", "firstname", "lastname", "nameRol");
        crud.getGrid().getColumn("username").setCaption("Nombre del usuario");
        crud.getGrid().getColumn("firstname").setCaption("Nombres");
        crud.getGrid().getColumn("lastname").setCaption("Apellido");
        crud.getGrid().getColumn("nameRol").setCaption("Nombre del rol");
    }

    private void loadSetVisibleProperties() {
        formFactory.setVisibleProperties(CrudOperation.READ, "username", "firstname", "lastname");
        formFactory.setVisibleProperties(CrudOperation.ADD, "username", "password", "firstname", "lastname", "identitydocument", "age", "phonenumber", "email", "listRoles");
        formFactory.setVisibleProperties(CrudOperation.UPDATE, "username", "password", "firstname", "lastname", "identitydocument", "age", "phonenumber", "email");
        formFactory.setVisibleProperties(CrudOperation.DELETE, "username", "firstname", "lastname");
    }

    private void loadSetFieldCaptions() {
        formFactory.setFieldCaptions(CrudOperation.ADD, "Nombre del usuario", "Password", "Nombres", "Apellidos", "Documento de identidad", "Edad", "Numero de telefono", "Correo electronico", "Roles");
        formFactory.setFieldCaptions(CrudOperation.UPDATE, "Nombre del usuario", "Password", "Nombres", "Apellidos", "Documento de identidad", "Edad", "Numero de telefono", "Correo electronico");
        formFactory.setFieldCaptions(CrudOperation.DELETE, "Nombre del usuario", "Nombres", "Apellidos");
    }

    private void loadSetButtonCaption() {
        formFactory.setButtonCaption(CrudOperation.READ,EnumLabel.ACEPTAR_LABEL.getLabel());
        formFactory.setButtonCaption(CrudOperation.ADD, EnumLabel.REGISTRAR_LABEL.getLabel());
        formFactory.setButtonCaption(CrudOperation.UPDATE, EnumLabel.EDITAR_LABEL.getLabel());
        formFactory.setButtonCaption(CrudOperation.DELETE, EnumLabel.ELIMINAR_LABEL.getLabel());
        formFactory.setCancelButtonCaption(EnumLabel.CANCELAR_LABEL.getLabel());
    }

    private void loadMessagesForm() {

        crud.setSavedMessage(EnumLabel.REGISTRO_SALVADO_LABEL.getLabel());
        crud.setDeletedMessage(EnumLabel.REGISTRO_ELIMINADO_LABEL.getLabel());
        crud.setRowCountCaption(EnumLabel.ROW_COUNT_CAPTION_LABEL.getLabel());
        crud.getFindAllButton().setDescription(EnumLabel.REFRESCAR_LABEL.getLabel());
        crud.getAddButton().setDescription(EnumLabel.REGISTRAR_LABEL.getLabel());
        crud.getUpdateButton().setDescription(EnumLabel.EDITAR_LABEL.getLabel());
        crud.getDeleteButton().setDescription(EnumLabel.ELIMINAR_LABEL.getLabel());
    }

    private void actionButtons() {
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
                getLoadIdRol(user);
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
    }
    private void getLoadIdRol(UsersEntity user) {
        Iterator iterator = roles.iterator();
        while (iterator.hasNext()) {
            RolesEntity rolesEntity = (RolesEntity) iterator.next();

            if (rolesEntity.getDescriptionrole().equalsIgnoreCase(user.getListRoles())) {
                user.setIdrol(rolesEntity.getIdrol());
                break;
            }


        }
    }

}

