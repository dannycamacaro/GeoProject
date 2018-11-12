package com.srb.project.view;


import com.srb.project.controller.ControllerRoutes;
import com.srb.project.enumConstans.EnumLabel;
import com.srb.project.enumConstans.EnumMessages;
import com.srb.project.model.RoutesEntity;
import com.srb.project.model.RoutesEntity;
import com.vaadin.navigator.View;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.VerticalLayout;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.crudui.crud.CrudListener;
import org.vaadin.crudui.crud.CrudOperation;
import org.vaadin.crudui.crud.impl.GridCrud;
import org.vaadin.crudui.form.impl.form.factory.GridLayoutCrudFormFactory;
import org.vaadin.crudui.layout.impl.HorizontalSplitCrudLayout;

import java.util.Collection;

@UIScope
@SpringView(name = ViewMaintenanceRoutes.VIEW_NAME)
public class ViewMaintenanceRoutes extends VerticalLayout implements View {

    public static final String VIEW_NAME = "routes";
    private HorizontalSplitCrudLayout horizontalSplitCrudLayout;
    private GridCrud<RoutesEntity> crud;
    private GridLayoutCrudFormFactory<RoutesEntity> formFactory;

    @Autowired
    ControllerRoutes controllerRoutes;

    public ViewMaintenanceRoutes() {
        horizontalSplitCrudLayout = new HorizontalSplitCrudLayout();
        horizontalSplitCrudLayout.setFormCaption(CrudOperation.DELETE,EnumLabel.ELIMINAR_REGISTRO_LABEL.getLabel());
        crud = new GridCrud<>(RoutesEntity.class, horizontalSplitCrudLayout);
        formFactory = new GridLayoutCrudFormFactory<>(RoutesEntity.class, 2, 2);
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
        formFactory.setUseBeanValidation(true);
        crud.setCrudFormFactory(formFactory);
        this.addComponent(crud);


    }

    private void loadSetColumns() {

        crud.getGrid().setColumns("nameroutes", "description");
        crud.getGrid().getColumn("nameroutes").setCaption("Nombre ruta");
        crud.getGrid().getColumn("description").setCaption("Descripcion");

    }

    private void loadSetVisibleProperties() {
        formFactory.setVisibleProperties(CrudOperation.READ, "nameroutes", "description");
        formFactory.setVisibleProperties(CrudOperation.ADD, "nameroutes", "description");
        formFactory.setVisibleProperties(CrudOperation.UPDATE, "nameroutes", "description");
        formFactory.setVisibleProperties(CrudOperation.DELETE, "nameroutes", "description");
    }

    private void loadSetFieldCaptions() {
        formFactory.setFieldCaptions(CrudOperation.READ, "Nombre ruta", "Descripcion");
        formFactory.setFieldCaptions(CrudOperation.ADD, "Nombre ruta", "Descripcion");
        formFactory.setFieldCaptions(CrudOperation.UPDATE, "Nombre ruta", "Descripcion");
        formFactory.setFieldCaptions(CrudOperation.DELETE, "Nombre ruta", "Descripcion");
    }

    private void loadSetButtonCaption() {
        formFactory.setButtonCaption(CrudOperation.READ, EnumLabel.ACEPTAR_LABEL.getLabel());
        formFactory.setButtonCaption(CrudOperation.ADD, EnumLabel.REGISTRAR_LABEL.getLabel());
        formFactory.setButtonCaption(CrudOperation.UPDATE, EnumLabel.EDITAR_LABEL.getLabel());
        formFactory.setButtonCaption(CrudOperation.DELETE, EnumLabel.ELIMINAR_LABEL.getLabel());
        formFactory.setCancelButtonCaption(EnumLabel.CANCELAR_LABEL.getLabel());
        formFactory.setValidationErrorMessage(EnumMessages.MESSAGE_REQUIRED_FIELD.getMessage());
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
        crud.setCrudListener(new CrudListener<RoutesEntity>() {
            @Override
            public Collection<RoutesEntity> findAll() {
                return controllerRoutes.findAllRoutes();
            }

            @Override
            public RoutesEntity add(RoutesEntity routes) {
                controllerRoutes.save(routes);
                return routes;
            }

            @Override
            public RoutesEntity update(RoutesEntity routes) {

                controllerRoutes.updateRoutes(routes);
                return routes;
            }

            @Override
            public void delete(RoutesEntity routes) {
                controllerRoutes.deleteRoutes(routes);
            }
        });
    }


}
