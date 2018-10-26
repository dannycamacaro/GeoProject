package com.srb.project.view;


import com.srb.project.controller.ControllerRoutes;
import com.srb.project.enumConstans.EnumLabel;
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

    @Autowired
    ControllerRoutes controllerRoutes;

    public ViewMaintenanceRoutes() {
        buildForm();

    }

    private void buildForm() {
        this.setSizeFull();
        this.setWidth("100%");

        FormLayout formRoutes = new FormLayout();
        GridCrud<RoutesEntity> crud = new GridCrud<>(RoutesEntity.class, new HorizontalSplitCrudLayout());
        GridLayoutCrudFormFactory<RoutesEntity> formFactory = new GridLayoutCrudFormFactory<>(RoutesEntity.class, 2, 2);

        crud.getGrid().setColumns("nameroutes","description");
        crud.getGrid().getColumn("nameroutes").setCaption("Nombre ruta");
        crud.getGrid().getColumn("description").setCaption("Descripcion");


        formFactory.setVisibleProperties(CrudOperation.READ, "nameroutes","description");
        formFactory.setVisibleProperties(CrudOperation.ADD, "nameroutes","description");
        formFactory.setVisibleProperties(CrudOperation.UPDATE, "nameroutes","description");
        formFactory.setVisibleProperties(CrudOperation.DELETE, "nameroutes","description");
        formFactory.setFieldCaptions(CrudOperation.ADD, "Nombre ruta","Descripcion");
        formFactory.setFieldCaptions(CrudOperation.UPDATE, "Nombre ruta","Descripcion");
        formFactory.setFieldCaptions(CrudOperation.DELETE, "Nombre ruta","Descripcion");
//        formFactory.setFieldCaptions(CrudOperation.UPDATE, "Nombre del usuario","Password", "Nombres","Apellidos","Documento de identidad","Edad","Numero de telefono","Correo electronico");
//        formFactory.setFieldCaptions(CrudOperation.DELETE, "Nombre del usuario", "Nombres","Apellidos");
        formFactory.setButtonCaption(CrudOperation.ADD, EnumLabel.REGISTRAR_LABEL.getLabel());
        formFactory.setButtonCaption(CrudOperation.UPDATE, EnumLabel.EDITAR_LABEL.getLabel());
        formFactory.setButtonCaption(CrudOperation.DELETE, EnumLabel.ELIMINAR_LABEL.getLabel());
//        formFactory.setFieldType("password", PasswordField.class);


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

        crud.setCrudFormFactory(formFactory);
        this.addComponent(crud);


    }


}
