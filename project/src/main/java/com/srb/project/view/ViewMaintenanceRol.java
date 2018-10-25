package com.srb.project.view;

import com.srb.project.controller.ControllerRol;
import com.srb.project.enumConstans.EnumLabel;
import com.srb.project.model.RolesEntity;
import com.vaadin.navigator.View;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.VerticalLayout;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.crudui.crud.CrudListener;
import org.vaadin.crudui.crud.CrudOperation;
import org.vaadin.crudui.crud.impl.GridCrud;
import org.vaadin.crudui.form.impl.form.factory.GridLayoutCrudFormFactory;
import org.vaadin.crudui.layout.impl.HorizontalSplitCrudLayout;

import java.util.ArrayList;
import java.util.Collection;

@UIScope
@SpringView(name = ViewMaintenanceRol.VIEW_NAME)
public class ViewMaintenanceRol extends VerticalLayout implements View {
    public static final String VIEW_NAME = "rol";
    private static final String FIELD_WIDTH = "250px";

    @Autowired
    ControllerRol controllerRol;

    public ViewMaintenanceRol() {

        GridCrud<RolesEntity> crud = new GridCrud<>(RolesEntity.class, new HorizontalSplitCrudLayout());
        GridLayoutCrudFormFactory<RolesEntity> formFactory = new GridLayoutCrudFormFactory<>(RolesEntity.class, 2, 2);

        formFactory.setVisibleProperties(CrudOperation.READ, "namerole", "descriptionrole");
        formFactory.setVisibleProperties(CrudOperation.ADD, "namerole", "descriptionrole");
        formFactory.setVisibleProperties(CrudOperation.UPDATE, "namerole", "descriptionrole");
        formFactory.setVisibleProperties(CrudOperation.DELETE, "namerole", "descriptionrole");
        formFactory.setFieldCaptions(CrudOperation.ADD, "Nombre del rol", "Descripcion del rol");
        formFactory.setFieldCaptions(CrudOperation.UPDATE, "Nombre del rol", "Descripcion del rol");
        formFactory.setFieldCaptions(CrudOperation.DELETE, "Nombre del rol", "Descripcion del rol");
        formFactory.setButtonCaption(CrudOperation.ADD, EnumLabel.REGISTRAR_LABEL.getLabel());
        formFactory.setButtonCaption(CrudOperation.UPDATE, EnumLabel.EDITAR_LABEL.getLabel());
        formFactory.setButtonCaption(CrudOperation.DELETE, EnumLabel.ELIMINAR_LABEL.getLabel());
        crud.setRowCountCaption("%d roles(s) encontrados");
        crud.getGrid().setColumns("namerole", "descriptionrole");
        crud.getGrid().getColumn("namerole").setCaption("Nombre del rol");
        crud.getGrid().getColumn("descriptionrole").setCaption("Descripcion");
        crud.setCrudListener(new CrudListener<RolesEntity>() {
            @Override
            public Collection<RolesEntity> findAll() {
                return controllerRol.findAllRoles();
            }

            @Override
            public RolesEntity add(RolesEntity rol) {
                controllerRol.save(rol);
                return rol;
            }

            @Override
            public RolesEntity update(RolesEntity user) {
                RolesEntity rol = new RolesEntity();
                controllerRol.updateRol(user);
                return rol;
            }

            @Override
            public void delete(RolesEntity user) {
                controllerRol.deleteRol(user);
            }
        });
        crud.setCrudFormFactory(formFactory);
        this.addComponent(crud);


    }

    private RolesEntity objectDummy() {
        RolesEntity rolesEntity = new RolesEntity();
        rolesEntity.setIdrol(1);
        rolesEntity.setNamerole("Administrador");
        rolesEntity.setDescriptionrole("Administrador del sistema");
        return rolesEntity;
    }
}
