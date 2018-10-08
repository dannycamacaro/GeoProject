package com.srb.project;


import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.spring.annotation.SpringViewDisplay;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.themes.ValoTheme;

@SpringUI (path = ViewLogin.APP_ROOT)
@SpringViewDisplay
public class ViewLogin extends UI {
    static final String  APP_ROOT = "/project";
    static final String  MENU_VIEW = "Menu";
    @Override
    protected void init(VaadinRequest vaadinRequest) {
        Label title = new Label("Menu");
        title.addStyleName(ValoTheme.MENU_TITLE);

        Button view1 = new Button("View 1", e -> getUI().getNavigator().navigateTo(ViewMenu.VIEW_NAME));
        view1.addStyleName(ValoTheme.BUTTON_LINK);
        setContent(view1);

    }
}
