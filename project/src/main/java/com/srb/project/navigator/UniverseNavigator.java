package com.srb.project.navigator;

import com.vaadin.navigator.Navigator;
import com.vaadin.ui.SingleComponentContainer;
import com.vaadin.ui.UI;
import org.apache.logging.log4j.util.Strings;

public class UniverseNavigator extends Navigator {

    public UniverseNavigator(UI ui, SingleComponentContainer componentContainer){
        super(ui,componentContainer);
    }

    private static UniverseNavigator getNavigator(){
        UI ui = UI.getCurrent();
        Navigator navigator = ui.getNavigator();
        return (UniverseNavigator) navigator;
    }

    public static void navigate(String path){
        try {
            UniverseNavigator.getNavigator().navigateTo(path);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void navigateTo(String navigationState) {
        super.navigateTo(Strings.trimToNull(navigationState));
    }
}
