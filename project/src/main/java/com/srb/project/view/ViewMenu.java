package com.srb.project.view;

import com.srb.project.ViewLogin;
import com.srb.project.navigator.UniverseNavigator;
import com.vaadin.annotations.Title;
import com.vaadin.annotations.Widgetset;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.sass.internal.util.StringUtil;
import com.vaadin.server.ThemeResource;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.*;

import javax.annotation.PostConstruct;

@UIScope

@Widgetset("com.srb.project.widgetset.VaadinmapsWidgetset")
@Title("Menu")
@SpringView(name = ViewMenu.VIEW_NAME, ui = ViewLogin.class)
public class ViewMenu extends VerticalLayout implements View {
    public static final String VIEW_NAME = "menu";

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {

        MenuBar menuBarRol = new MenuBar();
        MenuBar menuBarUser = new MenuBar();
        MenuBar menuBarRoutes = new MenuBar();
        MenuBar menuBarRoutesDetail = new MenuBar();
        MenuBar menuBarVehicle = new MenuBar();
        MenuBar menuBarDevice = new MenuBar();

        // A top-level menu item that opens a submenu
        MenuBar.MenuItem drinks = menuBarRol.addItem("Roles", null, new MenuBar.Command() {
            @Override
            public void menuSelected(MenuBar.MenuItem selectedItem) {
                UniverseNavigator.navigate(ViewMaintenanceRol.VIEW_NAME);
            }
        });

        menuBarUser.addItem("Usuario", null, new MenuBar.Command() {
            @Override
            public void menuSelected(MenuBar.MenuItem selectedItem) {
                UniverseNavigator.navigate(ViewMaintenanceUser.VIEW_NAME);
            }
        });
        menuBarVehicle.addItem("Vehiculos", null, new MenuBar.Command() {
            @Override
            public void menuSelected(MenuBar.MenuItem selectedItem) {
                UniverseNavigator.navigate(ViewMaintenanceVehicle.VIEW_NAME);
            }
        });

        menuBarRoutes.addItem("Rutas", null, new MenuBar.Command() {
            @Override
            public void menuSelected(MenuBar.MenuItem selectedItem) {
                UniverseNavigator.navigate(ViewMaintenanceRoutes.VIEW_NAME);
            }
        });

        menuBarRoutesDetail.addItem("Detalle de rutas", null, new MenuBar.Command() {
            @Override
            public void menuSelected(MenuBar.MenuItem selectedItem) {
                UniverseNavigator.navigate(ViewMaintenanceRoutesDetail.VIEW_NAME);
            }
        });

        menuBarDevice.addItem("Dispositivos", null, new MenuBar.Command() {
            @Override
            public void menuSelected(MenuBar.MenuItem selectedItem) {
                UniverseNavigator.navigate(ViewMaintenanceDevice.VIEW_NAME);
            }
        });

//// Submenu item with a sub-submenu
//        MenuBar.MenuItem hots = drinks.addItem("Hot", null, null);
//        hots.addItem("Tea",    mycommand);
//        hots.addItem("Coffee", mycommand);
//
//// Another submenu item with a sub-submenu
//        MenuBar.MenuItem colds = drinks.addItem("Cold", null, null);
//        colds.addItem("Milk", null, new MenuBar.Command() {
//            @Override
//            public void menuSelected(MenuBar.MenuItem selectedItem) {
//                UniverseNavigator.navigate(ViewMaintenanceRol.VIEW_NAME);
//            }
//        });
//        colds.addItem("Weissbier", null, mycommand);
//
//// Another top-level item
//        MenuBar.MenuItem snacks = barmenu.addItem("Snacks", null, null);
//        snacks.addItem("Weisswurst", null, mycommand);
//        snacks.addItem("Bratwurst",  null, mycommand);
//        snacks.addItem("Currywurst", null, mycommand);
//
//// Yet another top-level item
//        MenuBar.MenuItem servs = barmenu.addItem("Services", null, null);
//        servs.addItem("Car Service", null, mycommand);


        addComponent(menuBarRol);
        addComponent(menuBarUser);
        addComponent(menuBarVehicle);
        addComponent(menuBarRoutes);
        addComponent(menuBarRoutesDetail);
        addComponent(menuBarDevice);
    }
}
