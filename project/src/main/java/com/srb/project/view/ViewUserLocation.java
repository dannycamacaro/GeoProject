package com.srb.project.view;

import com.srb.project.controller.*;
import com.srb.project.model.RolesEntity;
import com.srb.project.model.RoutedetailEntity;
import com.srb.project.model.RoutesEntity;
import com.srb.project.model.UsersEntity;
import com.vaadin.annotations.Widgetset;
import com.vaadin.data.provider.ListDataProvider;
import com.vaadin.navigator.View;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.Collection;
import java.util.List;

@UIScope
@Widgetset(value = "WidgetSet")
@SpringView(name = ViewUserLocation.VIEW_NAME)
public class ViewUserLocation extends VerticalLayout implements View {
    public static final String  VIEW_NAME = "UserLocation";


    @Autowired
    ControllerRoutesDetail controllerRoutesDetail;
    @Autowired
    ControllerRoutes controllerRoutes;
    @Autowired
    ControllerDevice controllerDevice;
    @Autowired
    ControllerLocation controllerLocation;
    @Autowired
    ControllerUser controllerUser;

    UsersEntity usersEntity;
    RoutesEntity routesEntity;
    List<RoutedetailEntity> routedetailEntity;


    //
    private ComboBox<UsersEntity> cmbUser;
    private ComboBox<RoutesEntity> cmbRoutes;
    private DateTimeField txtDateTime;
    private Button btnSear;


    //Layouts
    private HorizontalLayout menuLayout = new HorizontalLayout();
    private HorizontalLayout principalLayout = new HorizontalLayout();
    private VerticalLayout leftlayout = new VerticalLayout();
    private VerticalLayout rightLayout = new VerticalLayout();
    private HorizontalLayout buttonsPrincipalLayout = new HorizontalLayout();

    private String action;
    private RolesEntity roles;
    private Grid<RoutedetailEntity> grid = new Grid<>();
    private ListDataProvider<RoutedetailEntity> dataProvider;
    private Collection<RoutedetailEntity> collectionDetailRoutes;
    private MenuBar menuBar;


    @PostConstruct
    public void createForm(){
        this.setWidth("100%");
        this.setHeightUndefined();

        if (menuBar == null)
            menuBar = ViewMenu.buildMenu();
        menuLayout.addComponent(menuBar);

        createLeftLayout();

        this.addComponents(menuLayout, principalLayout);
        this.setComponentAlignment(menuLayout, Alignment.TOP_CENTER);
    }

    private void createLeftLayout() {
        leftlayout.setSizeFull();
        leftlayout.addComponent(cmbUser);
        leftlayout.addComponent(cmbRoutes);
        leftlayout.addComponent(txtDateTime);


    }

    private void loadData(){
//        collectionDevice = controllerDevice.findAllDevice();
//        collectionVehicles = controllerVehicle.findAllVehicle();
    }

}
