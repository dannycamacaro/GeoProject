package com.srb.project.view;

import com.vaadin.navigator.View;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

import javax.annotation.PostConstruct;

@UIScope
@SpringView (name = ViewUsers.VIEW_NAME)
public class ViewUsers extends VerticalLayout implements View {
    public static final String VIEW_NAME = "users";
    @PostConstruct
    protected void init() {
        Label title = new Label("Principal Menu");
        title.addStyleName(ValoTheme.MENU_TITLE);

        Button view1 = new Button("Main", e -> getUI().getPage().setLocation("http://localhost:8080/project"));
        view1.addStyleName(ValoTheme.BUTTON_LINK);
        this.addComponent(view1);

    }
}
