package com.srb.project.view;

import com.srb.project.controller.*;
import com.srb.project.model.*;
import com.vaadin.annotations.Widgetset;
import com.vaadin.data.provider.DataProvider;
import com.vaadin.data.provider.ListDataProvider;
import com.vaadin.event.selection.SingleSelectionEvent;
import com.vaadin.event.selection.SingleSelectionListener;
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
import com.vaadin.ui.themes.ValoTheme;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@UIScope
@Widgetset(value = "WidgetSet")
@SpringView(name = ViewUserLocation.VIEW_NAME)
public class ViewUserLocation extends VerticalLayout implements View {
    public static final String VIEW_NAME = "UserLocation";


    @Autowired
    ControllerRoutesDetail controllerRoutesDetail;
    @Autowired
    ControllerVehicle controllerVehicle;
    @Autowired
    ControllerRoutes controllerRoutes;
    @Autowired
    ControllerDevice controllerDevice;
    @Autowired
    ControllerLocation controllerLocation;
    @Autowired
    ControllerAssignedVehicle controllerAssignedVehicle;
    @Autowired
    ControllerAssignedRoutes controllerAssignedRoutes;
    @Autowired
    ControllerUser controllerUser;

    UsersEntity usersEntity;
    RoutesEntity routesEntity;
    List<RoutedetailEntity> routedetailEntity;


    //
    private ComboBox<VehicleEntity> cmbVehicle = new ComboBox<>();
    private ComboBox<RoutesEntity> cmbRoutes = new ComboBox<>();
    private DateField txtDateTime = new DateField();
    private Button btnSearch = new Button("Ubicar");


    //Layouts
    private HorizontalLayout menuLayout = new HorizontalLayout();
    private HorizontalLayout principalLayout = new HorizontalLayout();
    private Panel principalPanel = new Panel("Ubicar Vehiculo");
    private VerticalLayout leftlayout = new VerticalLayout();
    private VerticalLayout rightLayout = new VerticalLayout();
    private HorizontalLayout buttonsPrincipalLayout = new HorizontalLayout();

    private String action;
    private RolesEntity roles;
    private Grid<RoutedetailEntity> grid = new Grid<>();
    private ListDataProvider<RoutedetailEntity> dataProvider;
    private ListDataProvider<RoutesEntity> routesProvider;
    private Collection<RoutedetailEntity> collectionDetailRoutes;
    private GoogleMap googleMap = new GoogleMap("AIzaSyB4I-w7Yl9c69j-tP2p-0XTqFusc8snvvc", null, "spanish");
    GoogleMapPolyline lineRoute = new GoogleMapPolyline();
    private MenuBar menuBar;
    private Collection<RoutesEntity> collectionRoutes = new ArrayList<>();
    private ArrayList<LatLon> detailLatLon = new ArrayList<>();
    ;


    @PostConstruct
    public void createForm() {
        this.setWidth("100%");
        this.setHeightUndefined();

        if (menuBar == null)
            menuBar = ViewMenu.buildMenu();
        menuLayout.addComponent(menuBar);

        principalLayout.setWidthUndefined();
        principalLayout.setHeight("100%");
        createLeftLayout();
        principalLayout.addComponent(rightLayout);
        principalPanel.setSizeFull();
        principalPanel.setContent(principalLayout);
        this.addComponents(menuLayout, principalPanel);
        this.setComponentAlignment(menuLayout, Alignment.TOP_CENTER);
    }

    private void createLeftLayout() {
        leftlayout.setHeight("100%");
        leftlayout.setWidthUndefined();
        cmbVehicle.setCaption("Vehiculos");
        cmbRoutes.setCaption("Rutas");
        defineBehavior();
        loadData();
        leftlayout.addComponents(cmbVehicle, cmbRoutes, txtDateTime, btnSearch);
        principalLayout.addComponents(leftlayout);
    }

    private void defineBehavior() {
        cmbVehicle.addSelectionListener(new SingleSelectionListener<VehicleEntity>() {
            @Override
            public void selectionChange(SingleSelectionEvent<VehicleEntity> event) {
                if (event.getValue() != null) {
                    findDetailRoutes(event.getValue());
                }
            }
        });

        cmbRoutes.addSelectionListener(new SingleSelectionListener<RoutesEntity>() {
            @Override
            public void selectionChange(SingleSelectionEvent<RoutesEntity> event) {
                if (event.getValue() != null) {
                    collectionDetailRoutes = controllerRoutesDetail.findRoutesDetailByIdRoute(event.getValue().getIdroutes());
                    if (!collectionDetailRoutes.isEmpty()) {
                        buildMap(rightLayout);
                    } else {
                        Notification.show("No se Encontro el detalle de la ruta", Notification.Type.HUMANIZED_MESSAGE);
                    }
                }
            }
        });
        btnSearch.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                LocalDate fecha = txtDateTime.getValue();
                VehicleEntity vehicleEntity = cmbVehicle.getValue();
                if (fecha !=null){
                    Collection<LocationEntity> entityCollection = controllerLocation.findByVehicleAndDate(vehicleEntity, fecha);
                    if (entityCollection!= null && !entityCollection.isEmpty()){
                        googleMap.clearMarkers();
                        createMarkersLocation(entityCollection);
                    }else {
                        Notification.show("No se encontraron ubicaciones con los datos suministrados", Notification.Type.HUMANIZED_MESSAGE);
                    }

                }else {
                    Notification.show("Debe Ingregar una fecha valida", Notification.Type.HUMANIZED_MESSAGE);
                }

            }
        });
    }

    private void createMarkersLocation(Collection<LocationEntity> entityCollection) {
        detailLatLon.clear();
        googleMap.clearMarkers();
        for (LocationEntity coordenas : entityCollection) {
            detailLatLon.add(new LatLon(Double.valueOf(coordenas.getLocationlatitude()), Double.valueOf(coordenas.getLocationlength())));
        }
        for (LatLon mark : detailLatLon) {
            GoogleMapMarker marker = new GoogleMapMarker();
            marker.setPosition(mark);
            marker.setAnimationEnabled(true);
            googleMap.addMarker(marker);
        }
    }

    private void buildMap(VerticalLayout rightPanel) {
        rightPanel.setSizeFull();
        rightPanel.setMargin(new MarginInfo(true, false, false, false));
        rightPanel.setSpacing(false);
        LatLon latLon;
        detailLatLon.clear();
        if (collectionDetailRoutes.size() > 0) {
            RoutedetailEntity objects = (RoutedetailEntity) ((ArrayList) collectionDetailRoutes).get(0);
            latLon = new LatLon(Double.valueOf(objects.getRoutelatitude()), Double.valueOf(objects.getRoutelength()));

            for (RoutedetailEntity coordenadas : collectionDetailRoutes) {
                detailLatLon.add(new LatLon(Double.valueOf(coordenadas.getRoutelatitude()), Double.valueOf(coordenadas.getRoutelength())));
            }

            if (collectionDetailRoutes.size() > 1) {
                lineRoute = new GoogleMapPolyline();
                lineRoute.setCoordinates(detailLatLon);
                googleMap.addPolyline(lineRoute);
            }

            dataProvider = DataProvider.ofCollection(collectionDetailRoutes);
            grid.setDataProvider(dataProvider);

        } else {
            latLon = new LatLon(10.4159194, -66.9022335);
        }
        googleMap.setCenter(latLon);
        googleMap.setWidth("800px");
        googleMap.setHeight("500px");
        googleMap.setVisible(true);
        googleMap.setZoom(14);

        rightPanel.addComponent(googleMap);

    }

    private void findRoutes(VehicleEntity vehicleEntity) {
        controllerRoutes.findAllRoutes();
    }

    private void findDetailRoutes(VehicleEntity vehicleEntity) {
        DeviceEntity deviceEntity = controllerDevice.findDeviceByIdVehicle(vehicleEntity.getIdvehicle());
        Collection<AssignedroutesEntity> assignedroutesEntities = controllerAssignedRoutes.findAllAssignedRoutesByIdDevice(deviceEntity.getIddevice());
        if (collectionRoutes!= null){
            collectionRoutes.clear();
        }

        for (AssignedroutesEntity assignes : assignedroutesEntities) {
            collectionRoutes.add(controllerRoutes.findRouteById(assignes.getIdroutes()));
        }
        routesProvider = new ListDataProvider<>(collectionRoutes);
        cmbRoutes.setDataProvider(routesProvider);
    }

    private void loadData() {
        cmbVehicle.setDataProvider(new ListDataProvider<VehicleEntity>(controllerVehicle.findAllVehicle()));
        cmbVehicle.setItemCaptionGenerator(VehicleEntity::getLicenseplate);
        cmbVehicle.setWidth("300px");
        cmbVehicle.setEmptySelectionCaption("Seleccione Uno");
        cmbRoutes.setDataProvider(new ListDataProvider<RoutesEntity>(controllerRoutes.findAllRoutes()));
        cmbRoutes.setItemCaptionGenerator(RoutesEntity::getNameroutes);
        cmbRoutes.setWidth("300px");
        cmbRoutes.setEmptySelectionCaption("Seleccione Uno");
    }


}
