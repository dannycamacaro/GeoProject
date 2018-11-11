package com.srb.project;


import com.srb.project.controller.ControllerLogin;
import com.srb.project.model.UsersEntity;
import com.srb.project.navigator.UniverseNavigator;
import com.srb.project.persister.ServicesLogin;
import com.srb.project.view.ViewMenu;
import com.vaadin.annotations.Title;
import com.vaadin.annotations.Widgetset;
import com.vaadin.navigator.View;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.navigator.SpringViewProvider;
import com.vaadin.tapio.googlemaps.GoogleMap;
import com.vaadin.tapio.googlemaps.client.LatLon;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

@SpringUI(path = ViewLogin.APP_ROOT)
@Title("Geo Baruta")
@Widgetset(value = "WidgetSet")
@SpringView
public class ViewLogin extends UI implements View{
    public static final String APP_ROOT = "/project";

    @Autowired
    ServicesLogin loginServices;

    @Autowired
    ApplicationContext applicationContext;

    @Autowired
    private SpringViewProvider viewProvider;



    @Autowired
    private ControllerLogin controllerLogin;

    Panel panelPrincipal = new Panel();

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        panelPrincipal.setSizeFull();

        VerticalLayout root = new VerticalLayout();
        root.addStyleName(ValoTheme.PANEL_BORDERLESS);
        //Formulario de login
        FormLayout loginForm = new FormLayout();
        loginForm.addStyleName(ValoTheme.LABEL_COLORED);
        Label lblTitle = new Label("Login");
        lblTitle.addStyleName(ValoTheme.LABEL_H1);
        lblTitle.setSizeFull();
        loginForm.setWidth("600px");
        loginForm.setHeight("200px");
        loginForm.addStyleNames(ValoTheme.PANEL_BORDERLESS);

        TextField txtUser = new TextField();
        txtUser.addStyleName(ValoTheme.TEXTFIELD_LARGE);
        txtUser.setCaption("Usuario");
        PasswordField txtPassword = new PasswordField();
        txtUser.addStyleName(ValoTheme.TEXTFIELD_LARGE);
        txtPassword.setCaption("Contraseña");

        txtUser.setValue("");
        txtPassword.setValue("");
        Button btnEntrar = new Button();
        btnEntrar.setCaption("Iniciar sesion");
        btnEntrar.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
//                if (loginServices.validateInfo(txtUser.getValue(), txtPassword.getValue(),usersEntity)) {
                if (controllerLogin.validateLogin(txtUser.getValue(), txtPassword.getValue())) {
                    iniNavigator();
                } else {
                    Notification.show("Verificar sus datos.", Notification.Type.ERROR_MESSAGE);
                }
            }
        });
        btnEntrar.addStyleName(ValoTheme.BUTTON_PRIMARY);
        btnEntrar.setWidth("200px");
       /* GoogleMap googleMap = new GoogleMap("AIzaSyB4I-w7Yl9c69j-tP2p-0XTqFusc8snvvc",null,"spanish");
        LatLon latLon = new LatLon();
        latLon.setLon(60.654654);
        latLon.setLat(51.000000);
        googleMap.setCenter(latLon);
        googleMap.setSizeFull();
        loginForm.addComponent(googleMap);*/

        loginForm.addComponent(lblTitle);
        loginForm.addComponent(txtUser);
        loginForm.addComponent(txtPassword);
        loginForm.addComponent(btnEntrar);
        loginForm.setComponentAlignment(lblTitle, Alignment.MIDDLE_CENTER);
        root.addComponent(loginForm);
        root.setSizeFull();
        root.setComponentAlignment(loginForm, Alignment.MIDDLE_CENTER);
        root.addStyleNames(ValoTheme.LAYOUT_WELL);
        panelPrincipal.setContent(root);
        setContent(panelPrincipal);

    }

    private void iniNavigator() {
        UniverseNavigator navigator = new UniverseNavigator(this,this);
        applicationContext.getAutowireCapableBeanFactory().autowireBean(navigator);
        navigator.addProvider(viewProvider);
        navigator.navigateTo(ViewMenu.VIEW_NAME);
    }
}
