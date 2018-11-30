package com.srb.project.navigator;

import com.srb.project.ViewLogin;
import com.vaadin.navigator.Navigator;
import com.vaadin.ui.SingleComponentContainer;
import com.vaadin.ui.UI;
import org.apache.logging.log4j.util.Strings;

public class UniverseNavigator extends Navigator {

    public UniverseNavigator(UI ui, SingleComponentContainer componentContainer){
        super(ui,componentContainer);
    }

    public static UniverseNavigator getNavigator(){
        UI ui = UI.getCurrent();
        Navigator navigator = ui.getNavigator();
        return (UniverseNavigator) navigator;
    }

    public static void navigate(String path){
        try {
            UniverseNavigator.getNavigator().navigateTo(path);
        }catch (Exception e){
            UniverseNavigator.getNavigator().addView("", ViewLogin.class);
            e.printStackTrace();
        }
    }

    @Override
    public void navigateTo(String navigationState) {
        super.navigateTo(Strings.trimToNull(navigationState));
    }
}
