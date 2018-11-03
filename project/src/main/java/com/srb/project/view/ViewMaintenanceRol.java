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
    private HorizontalSplitCrudLayout horizontalSplitCrudLayout;
    private GridCrud<RolesEntity> crud;
    private GridLayoutCrudFormFactory<RolesEntity> formFactory;

    @Autowired
    ControllerRol controllerRol;

    public ViewMaintenanceRol() {
        horizontalSplitCrudLayout = new HorizontalSplitCrudLayout();
        horizontalSplitCrudLayout.setFormCaption(CrudOperation.DELETE,EnumLabel.ELIMINAR_REGISTRO_LABEL.getLabel());
        crud = new GridCrud<>(RolesEntity.class, horizontalSplitCrudLayout);
        formFactory = new GridLayoutCrudFormFactory<>(RolesEntity.class, 2, 2);
        buildForm();

    }

    public void buildForm() {
        this.setSizeFull();
        this.setWidth("100%");

        loadSetColumns();
        loadSetVisibleProperties();
        loadSetFieldCaptions();
        loadSetButtonCaption();
        loadMessagesForm();
        actionButtons();

        crud.setCrudFormFactory(formFactory);
        this.addComponent(crud);


    }

    private void loadSetColumns() {

        crud.getGrid().setColumns("namerole", "descriptionrole");
        crud.getGrid().getColumn("namerole").setCaption("Nombre del rol");
        crud.getGrid().getColumn("descriptionrole").setCaption("Descripcion");
    }

    private void loadSetVisibleProperties() {
        formFactory.setVisibleProperties(CrudOperation.READ, "namerole", "descriptionrole");
        formFactory.setVisibleProperties(CrudOperation.ADD, "namerole", "descriptionrole");
        formFactory.setVisibleProperties(CrudOperation.UPDATE, "namerole", "descriptionrole");
        formFactory.setVisibleProperties(CrudOperation.DELETE, "namerole", "descriptionrole");
    }

    private void loadSetFieldCaptions() {
        formFactory.setFieldCaptions(CrudOperation.READ, "Nombre del rol", "Descripcion del rol");
        formFactory.setFieldCaptions(CrudOperation.ADD, "Nombre del rol", "Descripcion del rol");
        formFactory.setFieldCaptions(CrudOperation.UPDATE, "Nombre del rol", "Descripcion del rol");
        formFactory.setFieldCaptions(CrudOperation.DELETE, "Nombre del rol", "Descripcion del rol");
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
    }
}
