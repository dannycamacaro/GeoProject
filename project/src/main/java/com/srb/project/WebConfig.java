package com.srb.project;

import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinServlet;

import javax.servlet.annotation.WebServlet;


public class WebConfig {

    @WebServlet(urlPatterns = "/*", asyncSupported = true)
    @VaadinServletConfiguration(ui = ViewLogin.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }

}
