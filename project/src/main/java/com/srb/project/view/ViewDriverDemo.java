package com.srb.project.view;

import com.srb.project.controller.ControllerAssignedVehicle;
import com.srb.project.controller.ControllerLocation;
import com.srb.project.controller.ControllerVehicle;
import com.srb.project.model.AssignedvehicleEntity;
import com.srb.project.model.LocationEntity;
import com.srb.project.model.UsersEntity;
import com.srb.project.model.VehicleEntity;
import com.vaadin.annotations.Widgetset;
import com.vaadin.navigator.View;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.tapio.googlemaps.GoogleMap;
import com.vaadin.tapio.googlemaps.client.LatLon;
import com.vaadin.tapio.googlemaps.client.events.MapClickListener;
import com.vaadin.tapio.googlemaps.client.events.MarkerClickListener;
import com.vaadin.tapio.googlemaps.client.overlays.GoogleMapMarker;
import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Date;

@UIScope
@Widgetset(value = "WidgetSet")
@SpringView(name = ViewDriverDemo.VIEW_NAME)
public class ViewDriverDemo extends VerticalLayout implements View {
    public static final String VIEW_NAME = "driverView";

    @Autowired
    ControllerLocation controllerLocation;
    @Autowired
    ControllerVehicle controllerVehicle;
    @Autowired
    ControllerAssignedVehicle controllerAssignedVehicle;

    UsersEntity usersEntity;

    private ArrayList<LatLon> detailLatLon = new ArrayList<>();
    GoogleMap googleMap = new GoogleMap("AIzaSyB4I-w7Yl9c69j-tP2p-0XTqFusc8snvvc", null, "spanish");
    private HorizontalLayout menuLayout = new HorizontalLayout();
    private VerticalLayout rightPanel = new VerticalLayout();
    private MenuBar menuBar;

    public ViewDriverDemo() {


    }

    @PostConstruct
    private void buildForm() {
        this.setHeightUndefined();
        this.setWidth("100%");

        if (menuBar == null)
            menuBar = ViewMenu.buildMenu();
        menuLayout.addComponent(menuBar);

        // creacion formulario de mapa
        rightPanel.setSizeFull();
        buildMap(rightPanel);
        this.addComponents(menuLayout, rightPanel);
        this.setComponentAlignment(menuLayout, Alignment.MIDDLE_CENTER);
        this.setComponentAlignment(rightPanel, Alignment.MIDDLE_CENTER);
    }


    private void buildMap(VerticalLayout rightPanel) {
        LatLon latLon;
        detailLatLon.clear();
        googleMap.setWidth("800px");
        googleMap.setHeight("500px");
        googleMap.setVisible(true);
        googleMap.setZoom(14);

        for (LatLon mark : detailLatLon) {
            GoogleMapMarker marker = new GoogleMapMarker();
            marker.setPosition(mark);
            marker.setAnimationEnabled(true);
            googleMap.addMarker(marker);
        }
        latLon = new LatLon(10.4159194, -66.9022335);

        googleMap.setCenter(latLon);

        googleMap.addMarkerClickListener(new MarkerClickListener() {
            @Override
            public void markerClicked(GoogleMapMarker clickedMarker) {
                AssignedvehicleEntity assignedvehicleEntity = controllerAssignedVehicle.findUserByUserID(usersEntity);

                VehicleEntity vehicleEntity = controllerVehicle.findAllVehicleById(assignedvehicleEntity.getIdvehicle());
                LocationEntity entity = new LocationEntity();
                entity.setLocationdate( new Date());
                entity.setLocationlatitude(String.valueOf(clickedMarker.getPosition().getLat()));
                entity.setLocationlength(String.valueOf(clickedMarker.getPosition().getLon()));
                entity.setStatedelete((byte)1);
                entity.setVehicleByIdvehicle(vehicleEntity);
                controllerLocation.save(entity);
            }
        });
        googleMap.addMapClickListener(new MapClickListener() {
            @Override
            public void mapClicked(LatLon position) {
                if (usersEntity!= null){
                    AssignedvehicleEntity assignedvehicleEntity = controllerAssignedVehicle.findUserByUserID(usersEntity);

                    VehicleEntity vehicleEntity = controllerVehicle.findAllVehicleById(assignedvehicleEntity.getIdvehicle());
                    LocationEntity entity = new LocationEntity();
                    entity.setLocationdate( new Date());
                    entity.setLocationlatitude(String.valueOf(position.getLat()));
                    entity.setLocationlength(String.valueOf(position.getLon()));
                    entity.setStatedelete((byte)1);
                    entity.setVehicleByIdvehicle(vehicleEntity);
                    entity.setIdvehicle(vehicleEntity.getIdvehicle());
                    controllerLocation.save(entity);
                    LatLon mark = new LatLon(position.getLat(),position.getLon());
                    GoogleMapMarker marker = new GoogleMapMarker();
                    marker.setPosition(mark);
                    marker.setAnimationEnabled(true);
                    googleMap.addMarker(marker);
                }

            }
        });

        rightPanel.addComponent(googleMap);
        rightPanel.setComponentAlignment(googleMap, Alignment.MIDDLE_CENTER);
    }
    @Override
    public void attach() {
        if (this.getUI() != null && this.getUI().getSession().getAttribute(UsersEntity.class) != null) {
            usersEntity = this.getUI().getSession().getAttribute(UsersEntity.class);
            buildForm();
        }
    }
}
