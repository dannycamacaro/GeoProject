package com.srb.project.view;


import com.srb.project.controller.ControllerRoutesDetail;
import com.srb.project.enumConstans.EnumLabel;
import com.srb.project.model.RoutedetailEntity;
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
@SpringView(name = ViewMaintenanceRoutesDetail.VIEW_NAME)
public class ViewMaintenanceRoutesDetail extends VerticalLayout implements View {

    public static final String VIEW_NAME = "detalleRuta";

    @Autowired
    ControllerRoutesDetail controllerRoutesDetail;

    public ViewMaintenanceRoutesDetail() {
        buildForm();

    }

    private void buildForm() {
        this.setSizeFull();
        this.setWidth("100%");

        FormLayout formRoutes = new FormLayout();
        GridCrud<RoutedetailEntity> crud = new GridCrud<>(RoutedetailEntity.class, new HorizontalSplitCrudLayout());
        GridLayoutCrudFormFactory<RoutedetailEntity> formFactory = new GridLayoutCrudFormFactory<>(RoutedetailEntity.class, 2, 2);

        crud.getGrid().setColumns("description", "routelatitude","routelength");
        crud.getGrid().getColumn("description").setCaption("Descriocion");
        crud.getGrid().getColumn("routelatitude").setCaption("Latitud");
        crud.getGrid().getColumn("routelength").setCaption("Longitud");


        formFactory.setVisibleProperties(CrudOperation.READ, "description", "routelatitude","routelength");
        formFactory.setVisibleProperties(CrudOperation.ADD, "description", "routelatitude","routelength");
        formFactory.setVisibleProperties(CrudOperation.UPDATE, "description", "routelatitude","routelength");
        formFactory.setVisibleProperties(CrudOperation.DELETE, "description", "routelatitude","routelength");
        formFactory.setFieldCaptions(CrudOperation.ADD, "Descripcion","Latitud","Longitud");
        formFactory.setFieldCaptions(CrudOperation.UPDATE, "Descripcion","Latitud","Longitud");
        formFactory.setFieldCaptions(CrudOperation.DELETE, "Descripcion","Latitud","Longitud");
//        formFactory.setFieldCaptions(CrudOperation.UPDATE, "Nombre del usuario","Password", "Nombres","Apellidos","Documento de identidad","Edad","Numero de telefono","Correo electronico");
//        formFactory.setFieldCaptions(CrudOperation.DELETE, "Nombre del usuario", "Nombres","Apellidos");
        formFactory.setButtonCaption(CrudOperation.ADD, EnumLabel.REGISTRAR_LABEL.getLabel());
        formFactory.setButtonCaption(CrudOperation.UPDATE, EnumLabel.EDITAR_LABEL.getLabel());
        formFactory.setButtonCaption(CrudOperation.DELETE, EnumLabel.ELIMINAR_LABEL.getLabel());
//        formFactory.setFieldType("password", PasswordField.class);


        crud.setCrudListener(new CrudListener<RoutedetailEntity>() {
            @Override
            public Collection<RoutedetailEntity> findAll() {
                return controllerRoutesDetail.findAllRoutesDetail();
            }

            @Override
            public RoutedetailEntity add(RoutedetailEntity routes) {
                controllerRoutesDetail.save(routes);
                return routes;
            }

            @Override
            public RoutedetailEntity update(RoutedetailEntity routes) {

                controllerRoutesDetail.updateRoutes(routes);
                return routes;
            }

            @Override
            public void delete(RoutedetailEntity routes) {
                controllerRoutesDetail.deleteRoutes(routes);
            }
        });

        crud.setCrudFormFactory(formFactory);
        this.addComponent(crud);


    }


}
