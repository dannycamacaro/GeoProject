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
import com.vaadin.ui.MenuBar;
import org.springframework.context.annotation.Bean;

import javax.annotation.PostConstruct;
import java.awt.*;

@UIScope

@Widgetset("com.srb.project.widgetset.DemoWidgetSet")
@Title("Menu")
@SpringView(name = ViewMenu.VIEW_NAME, ui = ViewLogin.class)
public class ViewMenu extends VerticalLayout implements View {
    public static final String VIEW_NAME = "menu";

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        addComponent(buildMenu());
    }

    public static MenuBar buildMenu(){
        MenuBar mainMenu = new MenuBar();

        mainMenu.addItem("Roles",new MenuBar.Command() {
            @Override
            public void menuSelected(MenuBar.MenuItem selectedItem) {
                UniverseNavigator.navigate(ViewMaintenanceRol.VIEW_NAME);
            }
        });

        mainMenu.addItem("Usuario", null, new MenuBar.Command() {
            @Override
            public void menuSelected(MenuBar.MenuItem selectedItem) {
                UniverseNavigator.navigate(ViewMaintenanceUser.VIEW_NAME);
            }
        });
        mainMenu.addItem("Vehiculos", null, new MenuBar.Command() {
            @Override
            public void menuSelected(MenuBar.MenuItem selectedItem) {
                UniverseNavigator.navigate(ViewMaintenanceVehicle.VIEW_NAME);
            }
        });

        mainMenu.addItem("Rutas", null, new MenuBar.Command() {
            @Override
            public void menuSelected(MenuBar.MenuItem selectedItem) {
                UniverseNavigator.navigate(ViewMaintenanceRoutes.VIEW_NAME);
            }
        });

        mainMenu.addItem("Detalle de rutas", null, new MenuBar.Command() {
            @Override
            public void menuSelected(MenuBar.MenuItem selectedItem) {
                UniverseNavigator.navigate(ViewMaintenanceRoutesDetail.VIEW_NAME);
            }
        });

        mainMenu.addItem("Dispositivos", null, new MenuBar.Command() {
            @Override
            public void menuSelected(MenuBar.MenuItem selectedItem) {
                UniverseNavigator.navigate(ViewMaintenanceDevice.VIEW_NAME);
            }
        });

        return mainMenu;
    }

}
