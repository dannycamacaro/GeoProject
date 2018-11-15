package com.srb.project.view;

import com.srb.project.controller.ControllerRoutes;
import com.srb.project.controller.ControllerRoutesDetail;
import com.srb.project.model.RoutesEntity;
import com.srb.project.navigator.UniverseNavigator;
import com.vaadin.data.provider.DataProvider;
import com.vaadin.data.provider.ListDataProvider;
import com.vaadin.navigator.View;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Grid;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.components.grid.ItemClickListener;
import com.vaadin.ui.themes.ValoTheme;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.Collection;
import java.util.Locale;

@UIScope
@SpringView(name = ViewSelectRoute.VIEW_NAME)
public class ViewSelectRoute extends VerticalLayout implements View {
    public static final String VIEW_NAME = "SelectRoute";
    @Autowired
    ControllerRoutes controllerRoutes;

    public ViewSelectRoute() {

    }

    @PostConstruct
    private void buildForm() {

        this.setHeightUndefined();
        this.setWidth("100%");
        Grid<RoutesEntity> grid = new Grid<>();
        grid.addColumn(RoutesEntity::getNameroutes).setCaption("Ruta");
        grid.addColumn(RoutesEntity::getDescription).setCaption("Descripcion de la ruta");

        Collection<RoutesEntity> routes = controllerRoutes.findAllRoutes();
        ListDataProvider<RoutesEntity> dataProvider = DataProvider.ofCollection(routes);
        grid.setDataProvider(dataProvider);
        grid.addItemClickListener(new ItemClickListener<RoutesEntity>() {
            @Override
            public void itemClick(Grid.ItemClick<RoutesEntity> event) {
                grid.getUI().setData(event.getItem());
                grid.getUI().getSession().setAttribute(RoutesEntity.class,event.getItem());
                UniverseNavigator.navigate(ViewMaintenanceRoutesDetail.VIEW_NAME);
            }
        });

        TextField txtFilter = new TextField("Filtro por nombre de ruta");
        txtFilter.setPlaceholder("Nombre filtro");
        txtFilter.addValueChangeListener(event -> {
            dataProvider.setFilter(RoutesEntity::getNameroutes, name -> {
                String nameLower = name == null ? ""
                        : name.toLowerCase(Locale.ENGLISH);
                String filterLower = event.getValue()
                        .toLowerCase(Locale.ENGLISH);
                return nameLower.contains(filterLower);
            });
        });

        txtFilter.setStyleName("width:100px");
        grid.setSizeFull();
        this.setMargin(new MarginInfo(true,true,true,true));
        this.setSpacing(false);
        this.addComponent(txtFilter);
        this.addComponent(grid);

    }

}
