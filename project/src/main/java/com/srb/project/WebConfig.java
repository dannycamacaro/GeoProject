package com.srb.project;

import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinServlet;

import javax.servlet.annotation.WebServlet;


public class WebConfig {

    @WebServlet(urlPatterns = "/project", asyncSupported = true)
    @VaadinServletConfiguration(ui = ViewLogin.class, productionMode = false, widgetset = "WidgetSet")
    public static class MyUIServlet extends VaadinServlet {
    }

}
