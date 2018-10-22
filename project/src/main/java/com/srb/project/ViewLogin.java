package com.srb.project;


import com.srb.project.persister.ServicesLogin;
import com.srb.project.view.ViewMenu;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.spring.annotation.SpringViewDisplay;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import org.springframework.beans.factory.annotation.Autowired;

@SpringUI(path = ViewLogin.APP_ROOT)
@SpringViewDisplay
public class ViewLogin extends UI {
    public static final String APP_ROOT = "/project";
    public static final String MENU_VIEW = "Menu";
    @Autowired
    ServicesLogin loginServices;

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        Navigator navigator = new Navigator(this, this);
        navigator.addView(ViewMenu.VIEW_NAME, new ViewMenu());

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

        txtUser.setValue("Ericka");
        txtPassword.setValue("123456");
        Button btnEntrar = new Button();
        btnEntrar.setCaption("Iniciar sesion");
        btnEntrar.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                if (loginServices.validateInfo(txtUser.getValue(), txtPassword.getValue())) {
                    navigator.navigateTo(ViewMenu.VIEW_NAME);
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
        loginForm.setComponentAlignment(lblTitle, Alignment.MIDDLE_CENTER);

        root.addComponent(loginForm);
        root.setSizeFull();
        root.setComponentAlignment(loginForm, Alignment.MIDDLE_CENTER);
        root.addStyleNames(ValoTheme.LAYOUT_WELL);
        setContent(root);

    }
}
