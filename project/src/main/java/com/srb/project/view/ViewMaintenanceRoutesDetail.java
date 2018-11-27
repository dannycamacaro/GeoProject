package com.srb.project.view;


import com.srb.project.controller.ControllerRoutesDetail;
import com.srb.project.model.RoutedetailEntity;
import com.srb.project.model.RoutesEntity;
import com.srb.project.navigator.UniverseNavigator;
import com.srb.project.util.ValidationsString;
import com.vaadin.annotations.Widgetset;
import com.vaadin.data.provider.DataProvider;
import com.vaadin.data.provider.ListDataProvider;
import com.vaadin.navigator.View;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.tapio.googlemaps.GoogleMap;
import com.vaadin.tapio.googlemaps.client.LatLon;
import com.vaadin.tapio.googlemaps.client.events.MapClickListener;
import com.vaadin.tapio.googlemaps.client.events.MarkerClickListener;
import com.vaadin.tapio.googlemaps.client.overlays.GoogleMapMarker;
import com.vaadin.tapio.googlemaps.client.overlays.GoogleMapPolyline;
import com.vaadin.ui.*;
import com.vaadin.ui.components.grid.ItemClickListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.crudui.crud.impl.GridCrud;
import org.vaadin.crudui.form.impl.form.factory.GridLayoutCrudFormFactory;
import org.vaadin.crudui.layout.impl.HorizontalSplitCrudLayout;

import java.util.ArrayList;
import java.util.Collection;

@UIScope
@Widgetset(value = "WidgetSet")
@SpringView(name = ViewMaintenanceRoutesDetail.VIEW_NAME)
public class ViewMaintenanceRoutesDetail extends VerticalLayout implements View {

    public static final String VIEW_NAME = "detalleRuta";
    private HorizontalSplitCrudLayout horizontalSplitCrudLayout;
    private GridCrud<RoutedetailEntity> crud;
    private GridLayoutCrudFormFactory<RoutedetailEntity> formFactory;
    public RoutesEntity route;
    private ArrayList<LatLon> detailLatLon = new ArrayList<>();
    private ArrayList<RoutedetailEntity> detailRoute = new ArrayList<>();
    GoogleMap googleMap = new GoogleMap("AIzaSyB4I-w7Yl9c69j-tP2p-0XTqFusc8snvvc", null, "spanish");
    Collection<RoutedetailEntity> routes = new ArrayList<>();
    Grid<RoutedetailEntity> grid = new Grid<>();
    ListDataProvider<RoutedetailEntity> dataProvider;
    GoogleMapPolyline lineRoute = new GoogleMapPolyline();

    TextField txtDescription = new TextField("Descripcion");
    TextField txtLatitud = new TextField("Latitud");
    TextField txtLongitud = new TextField("Longitud");

    Button btnSaveDetails = new Button("Guardar Detalles");
    Button btnDeleteDetails = new Button("Eliminar Detalles");

    @Autowired
    ControllerRoutesDetail controllerRoutesDetail;


    private HorizontalLayout menuLayout = new HorizontalLayout();
    private HorizontalLayout principalLayout = new HorizontalLayout();
    private VerticalLayout leftPanel = new VerticalLayout();
    private VerticalLayout rightPanel = new VerticalLayout();
    private MenuBar menuBar;

    public ViewMaintenanceRoutesDetail() {


    }

    private void buildForm() {
        cleanComponents();
        this.setWidth("100%");
        this.setHeightUndefined();

        if (menuBar == null)
            menuBar = ViewMenu.buildMenu();
        menuLayout.addComponent(menuBar);
        googleMap = new GoogleMap("AIzaSyB4I-w7Yl9c69j-tP2p-0XTqFusc8snvvc", null, "spanish");
        // verificar si existen detalles de rutas
        if (route != null & route.getIdroutes() > 0) {
            routes.clear();
            routes = controllerRoutesDetail.findRoutesDetailByIdRoute(route.getIdroutes());
        }

        if (routes.size() > 0) {

        }
        // creacion de formuladrio de detalle
        leftPanel.setWidth("100%");
        leftPanel.setMargin(new MarginInfo(true, false, false, true));
        leftPanel.setSpacing(false);
        buildFormDetail(leftPanel);

        // creacion formulario de mapa
        rightPanel.setHeight("100%");
        rightPanel.setWidth("100%");
        buildMap(rightPanel);

        principalLayout.addComponents(leftPanel,rightPanel);
        this.addComponents(menuLayout,principalLayout);
        this.setComponentAlignment(menuLayout, Alignment.MIDDLE_CENTER);
    }

    private void cleanComponents() {
        routes.clear();
        this.removeAllComponents();
        principalLayout.removeAllComponents();
        leftPanel.removeAllComponents();
        rightPanel.removeAllComponents();
    }

    private void buildFormDetail(VerticalLayout leftPanel) {
        Collection<RoutedetailEntity> routes = detailRoute;
        loadOrReplaceTable(leftPanel);
    }

    private void loadOrReplaceTable(VerticalLayout leftPanel) {
        dataProvider = DataProvider.ofCollection(routes);
        grid = new Grid<>();
        grid.setEnabled(true);
        grid.addColumn(RoutedetailEntity::getDescription).setCaption("Ruta");
        grid.setDataProvider(dataProvider);
        grid.addItemClickListener(new ItemClickListener<RoutedetailEntity>() {
            @Override
            public void itemClick(Grid.ItemClick<RoutedetailEntity> event) {
                grid.getUI().setData(event.getItem());
                txtDescription.setValue(event.getItem().getDescription());
                txtLatitud.setValue(event.getItem().getRoutelatitude());
                txtLongitud.setValue(event.getItem().getRoutelength());
            }
        });

        txtDescription = new TextField("Descripcion");
        txtLatitud = new TextField("Latitud");
        txtLatitud.setEnabled(false);
        txtLongitud = new TextField("Longitud");
        txtLongitud.setEnabled(false);

        HorizontalLayout latlonLayout = new HorizontalLayout();
        latlonLayout.addComponents(txtLatitud, txtLongitud);

        leftPanel.addComponent(txtDescription);
        leftPanel.addComponent(latlonLayout);

        Button btnAgregar = new Button("Agregar Detalle");
        btnAgregar.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                if (!ValidationsString.isEmptyOrNull(txtDescription.getValue())
                        && !ValidationsString.isEmptyOrNull(txtLatitud.getValue())
                        && !ValidationsString.isEmptyOrNull(txtLongitud.getValue())) {

                    LatLon position = new LatLon(Double.valueOf(txtLatitud.getValue()), Double.valueOf(txtLongitud.getValue()));
                    detailLatLon.add(position);
                    RoutedetailEntity detailRoute = new RoutedetailEntity();
                    detailRoute.setIdroutes(route.getIdroutes());
                    detailRoute.setDescription(txtDescription.getValue());
                    detailRoute.setRoutelatitude(String.valueOf(position.getLat()));
                    detailRoute.setRoutelength(String.valueOf(position.getLon()));

                    GoogleMapMarker marker = new GoogleMapMarker();
                    marker.setPosition(position);
                    marker.setAnimationEnabled(true);
                    googleMap.addMarker(marker);
                    routes.add(detailRoute);

                    if (routes.size() > 1) {
                        googleMap.removePolyline(lineRoute);
                        lineRoute = new GoogleMapPolyline();
                        lineRoute.setCoordinates(detailLatLon);
                        googleMap.addPolyline(lineRoute);
                    }

                    dataProvider = DataProvider.ofCollection(routes);
                    grid.setDataProvider(dataProvider);
                    cleanFields();
                }
            }
        });
        Button btnClean = new Button("Limpiar");
        btnClean.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                cleanFields();
            }
        });

        btnSaveDetails.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                for (RoutedetailEntity detail : routes) {
                    try {
                        controllerRoutesDetail.save(detail);
                        Notification.show("Ruta Guardada con Exito.", Notification.Type.HUMANIZED_MESSAGE);
                        UniverseNavigator.navigate(ViewSelectRoute.VIEW_NAME);
                    } catch (Exception e) {
                        Notification.show("No se pudo guardar la ruta.", Notification.Type.HUMANIZED_MESSAGE);
                    }
                }
            }
        });

        btnDeleteDetails.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                routes.clear();
                googleMap.clearMarkers();
            }
        });

        HorizontalLayout botonera = new HorizontalLayout();
        botonera.addComponents(btnAgregar, btnClean);

        leftPanel.addComponent(botonera);
        leftPanel.addComponent(grid);

        HorizontalLayout botonera2 = new HorizontalLayout();
        botonera2.addComponents(btnSaveDetails, btnDeleteDetails);
        leftPanel.addComponent(botonera2);
    }

    private void cleanFields() {
        txtDescription.clear();
        txtLongitud.clear();
        txtLatitud.clear();
    }

    private void buildMap(VerticalLayout rightPanel) {
        rightPanel.setWidth("50%");
        rightPanel.setHeight("100%");
        rightPanel.setMargin(new MarginInfo(true, false, false, false));
        rightPanel.setSpacing(false);
        LatLon latLon;
        detailLatLon.clear();
        if (routes.size() > 0) {
            RoutedetailEntity objects = (RoutedetailEntity) ((ArrayList) routes).get(0);
            latLon = new LatLon(Double.valueOf(objects.getRoutelatitude()), Double.valueOf(objects.getRoutelength()));

            for (RoutedetailEntity coordenadas : routes) {
                detailLatLon.add(new LatLon(Double.valueOf(coordenadas.getRoutelatitude()), Double.valueOf(coordenadas.getRoutelength())));
            }

            for (LatLon mark : detailLatLon) {
                GoogleMapMarker marker = new GoogleMapMarker();
                marker.setPosition(mark);
                marker.setAnimationEnabled(true);
                googleMap.addMarker(marker);
            }

            if (routes.size() > 1) {
                lineRoute = new GoogleMapPolyline();
                lineRoute.setCoordinates(detailLatLon);
                googleMap.addPolyline(lineRoute);
            }

            dataProvider = DataProvider.ofCollection(routes);
            grid.setDataProvider(dataProvider);

        } else {
            latLon = new LatLon(10.4159194,-66.9022335);
        }
        googleMap.setCenter(latLon);
        googleMap.setWidth("600px");
        googleMap.setHeight("600px");
        googleMap.setVisible(true);
        googleMap.setZoom(10);

        googleMap.addMarkerClickListener(new MarkerClickListener() {
            @Override
            public void markerClicked(GoogleMapMarker clickedMarker) {
                detailLatLon.add(clickedMarker.getPosition());
                RoutedetailEntity detailRoute = new RoutedetailEntity();
                detailRoute.setRoutelatitude(String.valueOf(clickedMarker.getPosition().getLat()));
                detailRoute.setRoutelength(String.valueOf(clickedMarker.getPosition().getLon()));
                routes.add(detailRoute);
            }
        });
        googleMap.addMapClickListener(new MapClickListener() {
            @Override
            public void mapClicked(LatLon position) {
                cleanFields();
                txtLatitud.setValue(String.valueOf(position.getLat()));
                txtLongitud.setValue(String.valueOf(position.getLon()));

            }
        });
        rightPanel.addComponent(googleMap);
    }

    @Override
    public void attach() {
        if (this.getUI() != null && this.getUI().getSession().getAttribute(RoutesEntity.class) != null) {
            route = this.getUI().getSession().getAttribute(RoutesEntity.class);
            buildForm();
        }
    }

}
