package com.srb.project.view;


import com.srb.project.controller.ControllerRoutes;
import com.srb.project.enumConstans.EnumLabel;
import com.srb.project.enumConstans.EnumMessages;
import com.srb.project.model.RolesEntity;
import com.srb.project.model.RoutesEntity;
import com.srb.project.model.RoutesEntity;
import com.vaadin.data.provider.DataProvider;
import com.vaadin.data.provider.ListDataProvider;
import com.vaadin.navigator.View;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.*;
import com.vaadin.ui.components.grid.ItemClickListener;
import com.vaadin.ui.declarative.DesignContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.crudui.crud.CrudListener;
import org.vaadin.crudui.crud.CrudOperation;
import org.vaadin.crudui.crud.impl.GridCrud;
import org.vaadin.crudui.form.impl.form.factory.GridLayoutCrudFormFactory;
import org.vaadin.crudui.layout.impl.HorizontalSplitCrudLayout;

import javax.annotation.PostConstruct;
import java.util.Collection;

import static com.srb.project.view.ViewMenu.buildMenu;

@UIScope
@SpringView(name = ViewMaintenanceRoutes.VIEW_NAME)
public class ViewMaintenanceRoutes extends VerticalLayout implements View {
    @Autowired
    ControllerRoutes controllerRoutes;

    Collection<RoutesEntity> collectionRoutes;

    public static final String VIEW_NAME = "routes";
    private GridCrud<RoutesEntity> crud;
    private GridLayoutCrudFormFactory<RoutesEntity> formFactory;
    private Button btnNew = new Button("Registrar");
    private Button btnAccept = new Button("Aceptar");
    private Button btnEdit = new Button("Editar");
    private Button btnDelete = new Button("Eliminar");
    private Button btnCancel = new Button("Cancelar");
    private HorizontalLayout menuLayout = new HorizontalLayout();
    private HorizontalLayout principalLayout = new HorizontalLayout();
    private VerticalLayout leftLayout = new VerticalLayout();
    private VerticalLayout rightLayout = new VerticalLayout();
    private GridLayout fieldsLayout = new GridLayout(1, 1);
    private HorizontalLayout principalButtonsLayout = new HorizontalLayout();
    private HorizontalLayout secondaryButtonsLayout = new HorizontalLayout();
    private Grid<RoutesEntity> grid = new Grid<>();
    private ListDataProvider<RoutesEntity> dataProvider;
    private RoutesEntity routesEntitySelected;
    private TextField txtNameRoute = new TextField("Nombre de Ruta");
    private TextField txtDescription = new TextField("Descripcion de Ruta");
    private String action;

    public ViewMaintenanceRoutes() {

    }

    private void createMenu() {
        MenuBar menuBar = ViewMenu.buildMenu();
        menuLayout.addComponent(menuBar);
        menuLayout.setWidth("100%");
        menuLayout.setHeightUndefined();
        menuLayout.setComponentAlignment(menuBar, Alignment.TOP_CENTER);
        this.addComponent(menuLayout);
    }

    @PostConstruct
    private void buildForm() {
        this.setWidth("100%");
        this.setHeightUndefined();
        this.setSpacing(false);
        createMenu();
        hideFields();
        createLeftLayout();
        createRightLayout();
        principalLayout.setSizeFull();
        this.addComponent(principalLayout);
    }

    private void createLeftLayout() {
        leftLayout.setSizeFull();
        leftLayout.addComponent(grid);
        refreshInformationGrid();
        grid.addColumn(RoutesEntity::getNameroutes).setCaption("Nombre de Ruta");
        grid.addColumn(RoutesEntity::getDescription).setCaption("Descripcion de Ruta");
        grid.setDataProvider(dataProvider);
        grid.addItemClickListener(new ItemClickListener<RoutesEntity>() {
            @Override
            public void itemClick(Grid.ItemClick<RoutesEntity> event) {
                routesEntitySelected = event.getItem();
                txtNameRoute.setValue(routesEntitySelected.getNameroutes());
                txtDescription.setValue(routesEntitySelected.getDescription());
            }
        });
        grid.setDataProvider(dataProvider);
        grid.setSizeFull();
        principalLayout.addComponent(leftLayout);
    }

    private void createRightLayout() {
        principalButtonsLayout.addComponents(btnNew, btnEdit, btnDelete);

        btnNew.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                clearFields();
                showFields();
                action = "new";
            }
        });

        btnEdit.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                showFields();
                action = "edit";
            }
        });

        btnDelete.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                showFields();
                action = "delete";
            }
        });

        rightLayout.addComponent(principalButtonsLayout);
        fieldsLayout.addComponents(txtNameRoute, txtDescription);
        rightLayout.addComponents(fieldsLayout);
        rightLayout.setComponentAlignment(fieldsLayout, Alignment.MIDDLE_LEFT);

        secondaryButtonsLayout.addComponents(btnCancel, btnAccept);
        
        btnCancel.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                clearFields();
                hideFields();
            }
        });
        
        btnAccept.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                if (action.equalsIgnoreCase("new")) {
                    processAddRoute();
                } else if (action.equalsIgnoreCase("edit")) {
                    processUpdateRoute();
                } else if (action.equalsIgnoreCase("delete")) {
                    processDeleteRoute();
                }
            }
        });
        rightLayout.addComponent(secondaryButtonsLayout);

        principalLayout.addComponent(rightLayout);
    }

    private void processDeleteRoute() {
        try {
            controllerRoutes.deleteRoutes(routesEntitySelected);
            hideContent();
            clearFields();
            refreshInformationGrid();
            showMessage("Registro eliminado", Notification.Type.HUMANIZED_MESSAGE);
        }catch (Exception e){
            showMessage("No se pudo eliminar el registro", Notification.Type.HUMANIZED_MESSAGE);
        }
    }

    private void processUpdateRoute() {
        routesEntitySelected.setStatedelete(Byte.valueOf("1"));
        routesEntitySelected.setNameroutes(txtNameRoute.getValue());
        routesEntitySelected.setDescription(txtDescription.getValue());

        try {
            controllerRoutes.updateRoutes(routesEntitySelected);
            hideContent();
            clearFields();
            refreshInformationGrid();
            showMessage("Edicion Exitosa", Notification.Type.HUMANIZED_MESSAGE);
        } catch (Exception e) {
            showMessage("Ocurrio un Error", Notification.Type.HUMANIZED_MESSAGE);
            hideContent();
        }
    }

    private void processAddRoute() {
        RoutesEntity routesEntity = new RoutesEntity();
        routesEntity.setStatedelete(Byte.valueOf("1"));
        routesEntity.setNameroutes(txtNameRoute.getValue());
        routesEntity.setDescription(txtDescription.getValue());

        try {
            controllerRoutes.save(routesEntity);
            hideContent();
            clearFields();
            refreshInformationGrid();
            showMessage("Guardado Exitoso", Notification.Type.HUMANIZED_MESSAGE);
        } catch (Exception e) {
            showMessage("Ocurrio un Error", Notification.Type.HUMANIZED_MESSAGE);
            hideContent();
        }
    }

    private void hideContent() {
        fieldsLayout.setVisible(false);
        secondaryButtonsLayout.setVisible(false);
    }

    private void clearFields() {
        txtNameRoute.clear();
        txtDescription.clear();
    }

    private void showFields() {
        fieldsLayout.setVisible(true);
        secondaryButtonsLayout.setVisible(true);
    }

    private void hideFields() {
        fieldsLayout.setVisible(false);
        secondaryButtonsLayout.setVisible(false);
    }

    private void loadInformationGrid() {
        collectionRoutes = controllerRoutes.findAllRoutes();
        dataProvider = DataProvider.ofCollection(collectionRoutes);

        grid.setEnabled(true);
        grid.addColumn(RoutesEntity::getNameroutes).setCaption(EnumLabel.NAME_ROL_LABEL.getLabel());
        grid.addColumn(RoutesEntity::getDescription).setCaption(EnumLabel.DESCRIPTION_ROL_LABEL.getLabel());
        grid.setDataProvider(dataProvider);
        grid.addItemClickListener(new ItemClickListener<RoutesEntity>() {
            @Override
            public void itemClick(Grid.ItemClick<RoutesEntity> event) {
                grid.getUI().setData(event.getItem());
                routesEntitySelected = event.getItem();
                txtNameRoute.setValue(event.getItem().getNameroutes());
                txtDescription.setValue(event.getItem().getDescription());
            }
        });
    }


    private void refreshInformationGrid() {
        collectionRoutes = controllerRoutes.findAllRoutes();
        dataProvider = DataProvider.ofCollection(collectionRoutes);
        grid.setDataProvider(dataProvider);
    }

    private void showMessage(String mensaje, Notification.Type type){
        Notification.show(mensaje, type);
    }
}
