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
    private HorizontalSplitCrudLayout horizontalSplitCrudLayout;
    private GridCrud<RoutedetailEntity> crud;
    private GridLayoutCrudFormFactory<RoutedetailEntity> formFactory;

    @Autowired
    ControllerRoutesDetail controllerRoutesDetail;

    public ViewMaintenanceRoutesDetail() {

        horizontalSplitCrudLayout = new HorizontalSplitCrudLayout();
        horizontalSplitCrudLayout.setFormCaption(CrudOperation.DELETE,EnumLabel.ELIMINAR_REGISTRO_LABEL.getLabel());
        crud = new GridCrud<>(RoutedetailEntity.class, horizontalSplitCrudLayout);
        formFactory = new GridLayoutCrudFormFactory<>(RoutedetailEntity.class, 2, 2);
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

        crud.setCrudFormFactory(formFactory);
        this.addComponent(crud);


    }

    private void loadSetColumns() {

        crud.getGrid().setColumns("description", "routelatitude", "routelength");
        crud.getGrid().getColumn("description").setCaption("Descriocion");
        crud.getGrid().getColumn("routelatitude").setCaption("Latitud");
        crud.getGrid().getColumn("routelength").setCaption("Longitud");

    }

    private void loadSetVisibleProperties() {
        formFactory.setVisibleProperties(CrudOperation.READ, "description", "routelatitude", "routelength");
        formFactory.setVisibleProperties(CrudOperation.ADD, "description", "routelatitude", "routelength");
        formFactory.setVisibleProperties(CrudOperation.UPDATE, "description", "routelatitude", "routelength");
        formFactory.setVisibleProperties(CrudOperation.DELETE, "description", "routelatitude", "routelength");
    }

    private void loadSetFieldCaptions() {
        formFactory.setFieldCaptions(CrudOperation.READ, "Descripcion", "Latitud", "Longitud");
        formFactory.setFieldCaptions(CrudOperation.ADD, "Descripcion", "Latitud", "Longitud");
        formFactory.setFieldCaptions(CrudOperation.UPDATE, "Descripcion", "Latitud", "Longitud");
        formFactory.setFieldCaptions(CrudOperation.DELETE, "Descripcion", "Latitud", "Longitud");
    }

    private void loadSetButtonCaption() {
        formFactory.setButtonCaption(CrudOperation.READ, EnumLabel.ACEPTAR_LABEL.getLabel());
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
    }


}
