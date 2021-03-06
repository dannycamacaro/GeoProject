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
import com.vaadin.ui.*;
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

    private MenuBar menuBar;
    private Grid<RoutesEntity> grid = new Grid<>();
    private TextField txtFilter = new TextField("Filtro por nombre de ruta");
    private HorizontalLayout menuLayout = new HorizontalLayout();
    private HorizontalLayout principalLayout= new HorizontalLayout();
    private Panel principalPanel = new Panel("Seleccione una Ruta");
    private VerticalLayout leftLayout = new VerticalLayout();
    private VerticalLayout rightLayout = new VerticalLayout();

    public ViewSelectRoute() {

    }

    @PostConstruct
    private void buildForm() {

        this.setHeightUndefined();
        this.setWidth("100%");

        if (menuBar == null)
            menuBar = ViewMenu.buildMenu();
        menuLayout.addComponent(menuBar);


        createGrid();

        this.setMargin(new MarginInfo(true,true,true,true));
        this.setSpacing(false);
        principalPanel.setSizeFull();
        principalPanel.setContent(principalLayout);
        this.addComponents(menuLayout,principalPanel);
        this.setComponentAlignment(menuLayout, Alignment.MIDDLE_CENTER);
    }

    private void createGrid() {
        Collection<RoutesEntity> routes = controllerRoutes.findAllRoutes();
        grid.addColumn(RoutesEntity::getNameroutes).setCaption("Ruta");
        grid.addColumn(RoutesEntity::getDescription).setCaption("Descripcion de la ruta");
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


        txtFilter.setPlaceholder("Nombre filtro");
        txtFilter.addValueChangeListener(event -> {
            dataProvider.setFilter(RoutesEntity::getNameroutes, name -> {
                String nameLower = name == null ? "" : name.toLowerCase(Locale.ENGLISH);
                String filterLower = event.getValue()
                        .toLowerCase(Locale.ENGLISH);
                return nameLower.contains(filterLower);
            });
        });
        grid.setSizeFull();
        txtFilter.setStyleName("width:300px");
        leftLayout.addComponent(txtFilter);
        leftLayout.addComponent(grid);
        principalLayout.addComponents(leftLayout,rightLayout);
    }

}
