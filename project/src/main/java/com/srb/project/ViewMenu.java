package com.srb.project;

import com.srb.project.view.ViewMaintenanceRol;
import com.vaadin.navigator.View;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

import javax.annotation.PostConstruct;

@UIScope
@SpringView (name = ViewMenu.VIEW_NAME)
public class ViewMenu  extends VerticalLayout implements View {
    public static final String VIEW_NAME = "menu";
    @PostConstruct
    protected void init() {
        Label title = new Label("Principal Menu");
        title.addStyleName(ValoTheme.MENU_TITLE);

        Button view1 = new Button("Main", e -> getUI().getPage().setLocation("http://localhost:8080/project"));
        view1.addStyleName(ValoTheme.BUTTON_LINK);
        this.addComponent(view1);
        Label users = new Label("Users");
        title.addStyleName(ValoTheme.MENU_TITLE);

        Button buttonUser = new Button("Users", e -> getUI().getNavigator().navigateTo(ViewUsers.VIEW_NAME));
        buttonUser.addStyleName(ValoTheme.BUTTON_LINK);
        this.addComponent(buttonUser);

        Button buttonRol = new Button("Roles", e -> getUI().getNavigator().navigateTo(ViewMaintenanceRol.VIEW_NAME));
        buttonRol.addStyleName(ValoTheme.BUTTON_LINK);
        this.addComponent(buttonRol);

    }
}
