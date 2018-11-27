package com.srb.project.view;

import com.srb.project.controller.ControllerConsultReport;
import com.srb.project.controller.ControllerRoutes;
import com.srb.project.enumConstans.EnumLabel;
import com.srb.project.model.RoutesEntity;
import com.srb.project.model.VehicleEntity;
import com.vaadin.data.provider.DataProvider;
import com.vaadin.data.provider.ListDataProvider;
import com.vaadin.navigator.View;
import com.vaadin.server.SerializableSupplier;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.*;
import com.vaadin.ui.components.grid.ItemClickListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.reports.PrintPreviewReport;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@UIScope
@SpringView(name = ViewConsultReportVehicleActive.VIEW_NAME)
public class ViewConsultReportVehicleActive extends VerticalLayout implements View {

    public static final String VIEW_NAME = "consultReportVehicleActive";
    @Autowired
    ControllerConsultReport controllerConsultReport;


    //Layouts
    private HorizontalLayout menuLayout = new HorizontalLayout();
    private MenuBar menuBar;
    private PrintPreviewReport<VehicleEntity> reportVehicleActive = new PrintPreviewReport<>(VehicleEntity.class, "idvehicle", "mark", "licenseplate");
    private Button buttonDownload = new Button(EnumLabel.DOWNLOAD.getLabel());;
    private Grid<VehicleEntity> grid = new Grid<>();
    private VerticalLayout principalLayout = new VerticalLayout();
    private HorizontalLayout gridLayout = new HorizontalLayout();
    private HorizontalLayout buttonLayout = new HorizontalLayout();
    private ListDataProvider<VehicleEntity> dataProvider;


    public ViewConsultReportVehicleActive() {

    }

    @PostConstruct
    private void buildForm() {

        this.setWidth("100%");
        this.setHeightUndefined();

        if (menuBar == null)
            menuBar = ViewMenu.buildMenu();
        menuLayout.addComponent(menuBar);
        generateReportVehicleActive();
        generateGrid();
        buttonLayout.addComponent(buttonDownload);
        principalLayout.setHeightUndefined();
        principalLayout.setWidth("100%");
        gridLayout.setHeight("90%");
        gridLayout.setWidth("100%");
        principalLayout.addComponent(gridLayout);
        principalLayout.addComponent(buttonLayout);
        this.addComponents(menuLayout, principalLayout);
        this.setComponentAlignment(menuLayout, Alignment.TOP_CENTER);

    }

    private void generateGrid() {
        principalLayout.setSizeFull();
        gridLayout.addComponent(grid);
        grid.setSizeFull();
        grid.addColumn(VehicleEntity::getIdvehicle).setCaption("ID_Vehiculo");
        grid.addColumn(VehicleEntity::getMark).setCaption("Marca");
        grid.addColumn(VehicleEntity::getLicenseplate).setCaption("Matricula");
        grid.setDataProvider(dataProvider);
        grid.setDataProvider(dataProvider);

    }

    private void generateReportVehicleActive() {
        Collection collection = controllerConsultReport.findAllVehicleActive();
        dataProvider = DataProvider.ofCollection(collection);
        List list = new ArrayList(collection);
        SerializableSupplier<List<? extends VehicleEntity>> itemsSupplier = () -> list;
        reportVehicleActive.setItems(list);
        reportVehicleActive.downloadPdfOnClick(buttonDownload, "VehiculosActivos.pdf", itemsSupplier);

    }

}
