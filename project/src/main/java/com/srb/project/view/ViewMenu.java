package com.srb.project.view;

import com.srb.project.ViewLogin;
import com.srb.project.model.UsersEntity;
import com.srb.project.navigator.UniverseNavigator;
import com.vaadin.annotations.Title;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.ExternalResource;
import com.vaadin.server.ThemeResource;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Image;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.VerticalLayout;

@UIScope

@Title("Menu")
@SpringView(name = ViewMenu.VIEW_NAME, ui = ViewLogin.class)
public class ViewMenu extends VerticalLayout implements View {
    public static final String VIEW_NAME = "menu";
    MenuBar mainMenu;

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        if (mainMenu == null) {
            mainMenu = buildMenu();
            addComponent(mainMenu);
        } else {
            addComponent(mainMenu);
        }
        this.setWidth("100%");
        this.setHeightUndefined();
        ExternalResource externalResource = new ExternalResource("VAADIN/img/menuLogo.jpeg");
        Image image = new Image();
        image.setSource(externalResource);
        image.setResponsive(true);
        image.setSizeFull();
        addComponent(image);
        this.setComponentAlignment(mainMenu, Alignment.TOP_CENTER);
    }

    public static MenuBar buildMenu() {
        MenuBar mainMenu = new MenuBar();

        // A top-level menu item that opens a submenu
        MenuBar.MenuItem mantenimientoDeUsuario = mainMenu.addItem("Mantenimiento de Usuario", null, null);
        // Submenu item with a sub-submenu
        MenuBar.MenuItem roles = mantenimientoDeUsuario.addItem("Roles", null, new MenuBar.Command() {
            @Override
            public void menuSelected(MenuBar.MenuItem selectedItem) {
                UniverseNavigator.navigate(ViewMaintenanceRol.VIEW_NAME);
            }
        });
        MenuBar.MenuItem usuario = mantenimientoDeUsuario.addItem("Usuario", null, new MenuBar.Command() {
            @Override
            public void menuSelected(MenuBar.MenuItem selectedItem) {
                UniverseNavigator.navigate(ViewMaintenanceUser.VIEW_NAME);
            }
        });

            MenuBar.MenuItem mantenimientoDeVehiculo = mainMenu.addItem("Mantenimiento de Vehiculo", null, null);

            MenuBar.MenuItem vehiculo = mantenimientoDeVehiculo.addItem("Vehiculo", null, new MenuBar.Command() {
                @Override
                public void menuSelected(MenuBar.MenuItem selectedItem) {
                    UniverseNavigator.navigate(ViewMaintenanceVehicle.VIEW_NAME);
                }
            });

            MenuBar.MenuItem asignarVehiculo = mantenimientoDeVehiculo.addItem("Asignar Vehiculo", null, new MenuBar.Command() {
                @Override
                public void menuSelected(MenuBar.MenuItem selectedItem) {
                    UniverseNavigator.navigate(ViewMaintenanceAssignedVehicle.VIEW_NAME);
                }
            });

            MenuBar.MenuItem dispositivo = mantenimientoDeVehiculo.addItem("Dispositivo", null, new MenuBar.Command() {
                @Override
                public void menuSelected(MenuBar.MenuItem selectedItem) {
                    UniverseNavigator.navigate(ViewMaintenanceDevice.VIEW_NAME);
                }
            });

            MenuBar.MenuItem mantenimientoDeRutas = mainMenu.addItem("Mantenimiento de Rutas", null, null);

            MenuBar.MenuItem rutas = mantenimientoDeRutas.addItem("Rutas", null, new MenuBar.Command() {
                @Override
                public void menuSelected(MenuBar.MenuItem selectedItem) {
                    UniverseNavigator.navigate(ViewMaintenanceRoutes.VIEW_NAME);
                }
            });

            MenuBar.MenuItem detalleRutas = mantenimientoDeRutas.addItem("Detalle de Rutas", null, new MenuBar.Command() {
                @Override
                public void menuSelected(MenuBar.MenuItem selectedItem) {
                    UniverseNavigator.navigate(ViewSelectRoute.VIEW_NAME);
                }
            });

            MenuBar.MenuItem asignarRutas = mantenimientoDeRutas.addItem("Asignar  Rutas", null, new MenuBar.Command() {
                @Override
                public void menuSelected(MenuBar.MenuItem selectedItem) {
                    UniverseNavigator.navigate(ViewMaintenanceAssignedRoutes.VIEW_NAME);
                }
            });

        MenuBar.MenuItem consulta = mainMenu.addItem("Consulta", null, null);

        MenuBar.MenuItem reportVehicleActive = consulta.addItem("Consulta de vehiculos activos", null, new MenuBar.Command() {
            @Override
            public void menuSelected(MenuBar.MenuItem selectedItem) {
                UniverseNavigator.navigate(ViewConsultReportVehicleActive.VIEW_NAME);
            }
        });

        MenuBar.MenuItem reportAudit = consulta.addItem("Consulta de auditoria", null, new MenuBar.Command() {
            @Override
            public void menuSelected(MenuBar.MenuItem selectedItem) {
                UniverseNavigator.navigate(ViewConsultReportAudit.VIEW_NAME);
            }
        });

        MenuBar.MenuItem reportAssignedDevice = consulta.addItem("Consulta de dispositivos asignados", null, new MenuBar.Command() {
            @Override
            public void menuSelected(MenuBar.MenuItem selectedItem) {
                UniverseNavigator.navigate(ViewConsultReportAssignedDevice.VIEW_NAME);
            }
        });

        MenuBar.MenuItem reportRoutes = consulta.addItem("Consulta de rutas", null, new MenuBar.Command() {
            @Override
            public void menuSelected(MenuBar.MenuItem selectedItem) {
                UniverseNavigator.navigate(ViewConsultReportRoutes.VIEW_NAME);
            }
        });

        MenuBar.MenuItem ubicarVehiculo = consulta.addItem("Ubicar Vehiculo", null, new MenuBar.Command() {
            @Override
            public void menuSelected(MenuBar.MenuItem selectedItem) {
                UniverseNavigator.navigate(ViewUserLocation.VIEW_NAME);
            }
        });
        MenuBar.MenuItem  salir = mainMenu.addItem("Salir", null, new MenuBar.Command() {
            @Override
            public void menuSelected(MenuBar.MenuItem selectedItem) {
                UniverseNavigator.navigate(ViewLogin.APP_ROOT);
            }
        });

        return mainMenu;
    }

}
