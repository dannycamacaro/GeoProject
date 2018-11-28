package com.srb.project.view;

import com.vaadin.annotations.Widgetset;
import com.vaadin.navigator.View;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.tapio.googlemaps.GoogleMap;
import com.vaadin.tapio.googlemaps.client.LatLon;
import com.vaadin.tapio.googlemaps.client.events.MapClickListener;
import com.vaadin.tapio.googlemaps.client.events.MarkerClickListener;
import com.vaadin.tapio.googlemaps.client.overlays.GoogleMapMarker;
import com.vaadin.tapio.googlemaps.client.overlays.GoogleMapPolyline;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.VerticalLayout;

import java.util.ArrayList;

@UIScope
@Widgetset(value = "WidgetSet")
@SpringView(name = ViewMapsUbication.VIEW_NAME)
public class ViewMapsUbication extends VerticalLayout implements View {
    public static final String  VIEW_NAME = "mapa";
    private ArrayList<LatLon> detailRoute = new ArrayList<>();
    GoogleMap googleMap =  new GoogleMap("AIzaSyB4I-w7Yl9c69j-tP2p-0XTqFusc8snvvc",null,"spanish");



    public ViewMapsUbication() {
        HorizontalLayout main = new HorizontalLayout();

        LatLon latLon = new LatLon();
        latLon.setLat(10.455647);
        latLon.setLon(-66.8481963);
        googleMap.setCenter(latLon);
        googleMap.setZoom(16);
        googleMap.setSizeFull();
        googleMap.setHeight("400px");
        main.addComponent(googleMap);
        main.setWidth("100%");
        main.setHeight("100%");

        //codigo para trazar una ruta
        ArrayList<LatLon> points = new ArrayList<LatLon>();
        points.add(new LatLon(10.455647 , -66.8481963));
        points.add(new LatLon(10.4544759 , -66.8495696));
        points.add(new LatLon(10.4524185 , -66.8500202));
        points.add(new LatLon(10.4522497 , -66.854183));
        GoogleMapPolyline overlay = new GoogleMapPolyline(
                points, "#d31717", 0.8, 10);
        googleMap.addPolyline(overlay);

        googleMap.addMarkerClickListener(new MarkerClickListener() {
            @Override
            public void markerClicked(GoogleMapMarker clickedMarker) {
                clickedMarker.getPosition();
            }
        });
        googleMap.addMapClickListener(new MapClickListener() {
            @Override
            public void mapClicked(LatLon position) {
                detailRoute.add(position);
                GoogleMapMarker googleMapMarker = new GoogleMapMarker();
                googleMapMarker.setPosition(position);
                googleMap.addMarker(googleMapMarker);
                if (!detailRoute.isEmpty() && detailRoute.size() >1){
                    googleMap.clearMarkers();
                    GoogleMapPolyline route = new GoogleMapPolyline(
                            detailRoute, "#d31717", 0.8, 10);
                    googleMap.addPolyline(route);
                }
            }
        });



        this.addComponent(main);
    }


}
