package com.srb.project.view;

import com.vaadin.annotations.Widgetset;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.spring.navigator.SpringNavigator;
import com.vaadin.tapio.googlemaps.GoogleMap;
import com.vaadin.tapio.googlemaps.client.LatLon;
import com.vaadin.ui.*;

import javax.annotation.PostConstruct;

@UIScope

@Widgetset("com.srb.project.widgetset.VaadinmapsWidgetset")
@SpringView(name = ViewMenu.VIEW_NAME)
public class ViewMenu extends VerticalLayout implements View {
    private final MenuBar.Command menuCommand = selectedItem -> Notification.show("Action " + selectedItem.getText(), Notification.Type.TRAY_NOTIFICATION);

    public static final String VIEW_NAME = "menu";

    public ViewMenu() {
        buildContainer();
    }

    private void buildContainer() {
        buildMenu();
    }

    @PostConstruct
    protected void init() {
        buildMenu();
    }

    private void buildMenu() {
        Navigator navigator = new SpringNavigator();
        navigator.addView("Roles", new ViewMaintenanceRol());
        navigator.addView("Usuarios", new ViewMaintenanceUser());


        UI.getCurrent().getPage().setTitle(String.format("Crawler Admin | %s", "Menu"));
        setWidth(100, Unit.PERCENTAGE);
        setSpacing(true);
        setMargin(true);

        HorizontalLayout actionBarLayout = new HorizontalLayout();
        actionBarLayout.setWidth(100, Unit.PERCENTAGE);

        MenuBar menu = new MenuBar();


        MenuBar.MenuItem dataItem = menu.addItem("Configuration", null, null);
        dataItem.addItem("Roles", menuItem -> navigator.navigateTo("Roles"));
        dataItem.addItem("Usuarios", menuItem -> navigator.navigateTo("Usuarios"));

        actionBarLayout.addComponent(menu);
        GoogleMap googleMap = new GoogleMap("",null,null);
        LatLon latLon = new LatLon();
        latLon.setLat(60.50691090821668);
        latLon.setLon(22.163543701171875);
        googleMap.setCenter(latLon);
        googleMap.setHeight("20%");
        googleMap.setWidth("20%");

        addComponent(actionBarLayout);
        addComponent(googleMap);

    }
}
