package com.srb.project.view;

import com.vaadin.annotations.Widgetset;
import com.vaadin.navigator.View;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.tapio.googlemaps.GoogleMap;
import com.vaadin.tapio.googlemaps.client.LatLon;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.VerticalLayout;

@UIScope
@Widgetset(value = "WidgetSet")
@SpringView(name = ViewMapsUbication.VIEW_NAME)
public class ViewMapsUbication extends VerticalLayout implements View {
    public static final String  VIEW_NAME = "mapa";
    public ViewMapsUbication() {
        HorizontalLayout main = new HorizontalLayout();
        GoogleMap googleMap = new GoogleMap("AIzaSyB4I-w7Yl9c69j-tP2p-0XTqFusc8snvvc",null,"spanish");

        LatLon latLon = new LatLon();
        latLon.setLon(60.654654);
        latLon.setLat(51.000000);
        googleMap.setCenter(latLon);
        googleMap.setHeight("50%");
        googleMap.setWidth("50%");
        main.addComponent(googleMap);
        main.setWidth("100%");
        main.setHeight("100%");
        this.addComponent(main);
    }


}
