package com.srb.project;


import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.spring.annotation.SpringViewDisplay;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;

@SpringUI (path = ViewLogin.APP_ROOT)
@SpringViewDisplay
public class ViewLogin extends UI {
    static final String  APP_ROOT = "/project";
    static final String  MENU_VIEW = "Menu";
    @Override
    protected void init(VaadinRequest vaadinRequest) {
        getUI().getNavigator().addView(ViewMenu.VIEW_NAME,new ViewUsers());
        Label title = new Label("Login");
        title.addStyleName(ValoTheme.MENU_TITLE);

        //Formulario de login
        FormLayout loginForm = new FormLayout();

        //users
        Label lblUser = new Label("Usuario");
        lblUser.addStyleName(ValoTheme.LABEL_H3);
        TextField txtUser = new TextField();

        Label lblPassword = new Label("Password");
        lblPassword.addStyleName(ValoTheme.LABEL_H3);
        TextField txtPassword = new TextField();

        Button btnEntrar0 = new Button("Entrar", e -> getUI().getNavigator().navigateTo(ViewMenu.VIEW_NAME));
        Button btnEntrar = new Button();
        btnEntrar.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                if (txtUser.getValue()!= null && txtPassword.getValue() != null)
                if(txtUser.getValue().equalsIgnoreCase("Danny") && txtPassword.getValue().equalsIgnoreCase("danny")){
                    getUI().getNavigator().navigateTo(ViewMenu.VIEW_NAME);
                }
            }
        });
        btnEntrar.addStyleName(ValoTheme.BUTTON_FRIENDLY);
        btnEntrar.setWidth("150px");

        loginForm.addComponent(lblUser);
        loginForm.addComponent(txtUser);
        loginForm.addComponent(lblPassword);
        loginForm.addComponent(txtPassword);

        loginForm.addComponent(btnEntrar);
        loginForm.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
        loginForm.setSizeFull();

        setContent(loginForm);

    }
}
