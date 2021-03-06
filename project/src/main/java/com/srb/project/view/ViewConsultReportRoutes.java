package com.srb.project.view;

import com.srb.project.controller.ControllerConsultReport;
import com.srb.project.enumConstans.EnumLabel;
import com.srb.project.pojo.ConsultReportAudit;
import com.srb.project.pojo.ConsultReportRoutes;
import com.vaadin.data.provider.DataProvider;
import com.vaadin.data.provider.ListDataProvider;
import com.vaadin.navigator.View;
import com.vaadin.server.SerializableSupplier;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.reports.PrintPreviewReport;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@UIScope
@SpringView(name = ViewConsultReportRoutes.VIEW_NAME)
public class ViewConsultReportRoutes extends VerticalLayout implements View {

    public static final String VIEW_NAME = "consultReportRutes";
    @Autowired
    ControllerConsultReport controllerConsultReport;


    //Layouts
    private HorizontalLayout menuLayout = new HorizontalLayout();
    private MenuBar menuBar;
    private PrintPreviewReport<ConsultReportRoutes> reportRoutes = new PrintPreviewReport<>(ConsultReportRoutes.class,"nombreRuta","descripcion");
    private Button buttonDownload = new Button(EnumLabel.DOWNLOAD.getLabel());;
    private Grid<ConsultReportRoutes> grid = new Grid<>();
    private VerticalLayout principalLayout = new VerticalLayout();
    private Panel principalPanel = new Panel("Reporte de Rutas");
    private HorizontalLayout gridLayout = new HorizontalLayout();
    private HorizontalLayout buttonLayout = new HorizontalLayout();
    private ListDataProvider<ConsultReportRoutes> dataProvider;


    public ViewConsultReportRoutes() {

    }

    @PostConstruct
    private void buildForm() {

        this.setWidth("100%");
        this.setHeightUndefined();

        if (menuBar == null)
            menuBar = ViewMenu.buildMenu();
        menuLayout.addComponent(menuBar);
        generateReportAudit();
        generateGrid();
        buttonLayout.addComponent(buttonDownload);
        principalLayout.setHeightUndefined();
        principalLayout.setWidth("100%");
        gridLayout.setHeight("90%");
        gridLayout.setWidth("100%");
        principalLayout.addComponent(gridLayout);
        principalLayout.addComponent(buttonLayout);
        principalPanel.setSizeFull();
        principalPanel.setContent(principalLayout);

        this.addComponents(menuLayout, principalPanel);
        this.setComponentAlignment(menuLayout, Alignment.TOP_CENTER);

    }

    private void generateGrid() {
        principalLayout.setSizeFull();
        gridLayout.addComponent(grid);
        grid.setSizeFull();
        grid.addColumn(ConsultReportRoutes::getNombreRuta).setCaption("Nombre de la ruta");
        grid.addColumn(ConsultReportRoutes::getDescripcion).setCaption("Descripcion");
        grid.setDataProvider(dataProvider);
        grid.setDataProvider(dataProvider);

    }

    private void generateReportAudit() {
        Collection collection = controllerConsultReport.loadAllRoute();
        dataProvider = DataProvider.ofCollection(collection);
        List list = new ArrayList(collection);
        SerializableSupplier<List<? extends ConsultReportRoutes>> itemsSupplier = () -> list;
        reportRoutes.setItems(list);
        reportRoutes.downloadPdfOnClick(buttonDownload, "ReporteDeRutas.pdf", itemsSupplier);

    }

}
