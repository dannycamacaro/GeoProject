package com.srb.project;


import com.google.gwt.resources.client.ImageResource;
import com.srb.project.controller.ControllerLogin;
import com.srb.project.enumConstans.EnumMessages;
import com.srb.project.model.UsersEntity;
import com.srb.project.navigator.UniverseNavigator;
import com.srb.project.persister.ServicesLogin;
import com.srb.project.view.ViewDriverDemo;
import com.srb.project.view.ViewMenu;
import com.vaadin.annotations.Title;
import com.vaadin.annotations.Widgetset;
import com.vaadin.navigator.View;
import com.vaadin.server.ExternalResource;
import com.vaadin.server.Resource;
import com.vaadin.server.ThemeResource;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.navigator.SpringViewProvider;
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
        HorizontalLayout root = new HorizontalLayout();
        VerticalLayout visitantes = new VerticalLayout();
        ExternalResource externalResource = new ExternalResource("VAADIN/img/logo.png");
        visitantes.setIcon(externalResource);
        VerticalLayout login = new VerticalLayout();
        Resource resource= new ThemeResource("VAADIN/img/logo.png");
        visitantes.setHeight("100%");
        visitantes.setWidth("50%");

        login.setHeight("100%");
        login.setWidth("50%");

        root.addStyleName(ValoTheme.PANEL_BORDERLESS);
        //Formulario de login
        FormLayout loginForm = new FormLayout();
        loginForm.addStyleName(ValoTheme.LABEL_COLORED);
        Label lblTitle = new Label(EnumMessages.MESSAGES_LOGIN.getMessage());
        lblTitle.addStyleName(ValoTheme.LABEL_H1);
        lblTitle.setSizeFull();
        loginForm.setWidth("600px");
        loginForm.setHeight("200px");
        loginForm.addStyleNames(ValoTheme.PANEL_BORDERLESS);
        loginForm.setStyleName("Background-color: white");

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
                UsersEntity entity = controllerLogin.validateLogin(txtUser.getValue(), txtPassword.getValue());
                if (entity!=null) {
                    iniNavigator(entity);
                } else {
                    Notification.show("Verificar sus datos.", Notification.Type.ERROR_MESSAGE);
                }
            }
        });
        btnEntrar.addStyleName(ValoTheme.BUTTON_PRIMARY);
        btnEntrar.setWidth("200px");

        loginForm.addComponent(lblTitle);
        loginForm.addComponent(txtUser);
        loginForm.addComponent(txtPassword);
        loginForm.addComponent(btnEntrar);
        login.addComponent(loginForm);
        login.setStyleName("Background-color: white");
        root.addComponent(visitantes);
        root.addComponent(login);
        root.setComponentAlignment(login, Alignment.MIDDLE_CENTER);
        root.setSizeFull();
        root.setStyleName("Background-color: white");

        //Alineamiento de los componentes
        loginForm.setComponentAlignment(lblTitle, Alignment.MIDDLE_CENTER);
//        root.setComponentAlignment(loginForm, Alignment.MIDDLE_CENTER);
        root.addStyleNames(ValoTheme.LAYOUT_WELL);
        panelPrincipal.setContent(root);
        panelPrincipal.setStyleName("Background-color: white");
        setContent(panelPrincipal);

    }

    private void iniNavigator(UsersEntity entity) {
        UniverseNavigator navigator = new UniverseNavigator(this,this);
        applicationContext.getAutowireCapableBeanFactory().autowireBean(navigator);
        navigator.addProvider(viewProvider);
        if (this.getUI() != null && entity!= null)
            this.getUI().getSession().setAttribute(UsersEntity.class, entity);

        if (entity.getIdrol()==2){
            navigator.navigateTo(ViewDriverDemo.VIEW_NAME);
        }else {
            navigator.navigateTo(ViewMenu.VIEW_NAME);
        }
    }
}
