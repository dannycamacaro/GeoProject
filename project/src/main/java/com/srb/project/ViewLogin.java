package com.srb.project;


import com.srb.project.persister.LoginServices;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.spring.annotation.SpringViewDisplay;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import org.springframework.beans.factory.annotation.Autowired;

@SpringUI (path = ViewLogin.APP_ROOT)
@SpringViewDisplay
public class ViewLogin extends UI {
    public static final String  APP_ROOT = "/project";
    public static final String  MENU_VIEW = "Menu";
    @Autowired
    LoginServices loginServices;

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        Navigator navigator= new Navigator(this,this);
        navigator.addView(ViewMenu.VIEW_NAME,new ViewUsers());

        VerticalLayout root = new VerticalLayout();
        //Formulario de login
        FormLayout loginForm = new FormLayout();
        loginForm.setCaption("Login");
        loginForm.setWidth("600px");
        loginForm.setHeight("200px");
        loginForm.addStyleNames(ValoTheme.LAYOUT_CARD,ValoTheme.LABEL_H3,ValoTheme.LAYOUT_HORIZONTAL_WRAPPING);


        TextField txtUser = new TextField();
        txtUser.addStyleName(ValoTheme.TEXTFIELD_LARGE);
        txtUser.setCaption("Usuario");
        PasswordField txtPassword = new PasswordField();
        txtUser.addStyleName(ValoTheme.TEXTFIELD_LARGE);
        txtPassword.setCaption("Contraseña");

        Button btnEntrar = new Button();
        btnEntrar.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                if (loginServices.validateInfo(txtUser.getValue(),txtPassword.getValue())){
                    getUI().getNavigator().navigateTo(ViewMenu.VIEW_NAME);
                }else {
                    Notification.show("Verificar sus datos.", Notification.Type.ERROR_MESSAGE);
                }
            }
        });
        btnEntrar.addStyleName(ValoTheme.BUTTON_PRIMARY);
        btnEntrar.setWidth("200px");

        loginForm.addComponent(txtUser);
        loginForm.addComponent(txtPassword);
        loginForm.addComponent(btnEntrar);

        root.addComponent(loginForm);
        root.setSizeFull();
        root.setComponentAlignment(loginForm, Alignment.MIDDLE_CENTER);
        root.addStyleNames(ValoTheme.LAYOUT_WELL);
        setContent(root);


    }
}
