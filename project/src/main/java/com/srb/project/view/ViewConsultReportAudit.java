package com.srb.project.view;

import com.srb.project.controller.ControllerConsultReport;
import com.srb.project.enumConstans.EnumLabel;
import com.srb.project.model.AuditsEntity;
import com.srb.project.model.VehicleEntity;
import com.srb.project.pojo.ConsultReportAudit;
import com.vaadin.data.provider.DataProvider;
import com.vaadin.data.provider.ListDataProvider;
import com.vaadin.navigator.View;
import com.vaadin.server.SerializableSupplier;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.reports.PrintPreviewReport;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@UIScope
@SpringView(name = ViewConsultReportAudit.VIEW_NAME)
public class ViewConsultReportAudit extends VerticalLayout implements View {

    public static final String VIEW_NAME = "consultReportAudit";
    @Autowired
    ControllerConsultReport controllerConsultReport;

    //Layouts
    private HorizontalLayout menuLayout = new HorizontalLayout();
    private MenuBar menuBar;
    private PrintPreviewReport<ConsultReportAudit> reportAudit = new PrintPreviewReport<>(ConsultReportAudit.class);
    private Button buttonDownload = new Button(EnumLabel.DOWNLOAD.getLabel());;
    private Grid<ConsultReportAudit> grid = new Grid<>();
    private VerticalLayout principalLayout = new VerticalLayout();
    private Panel principalPanel = new Panel("Reporte de Auditoria de Operaciones");
    private HorizontalLayout gridLayout = new HorizontalLayout();
    private HorizontalLayout buttonLayout = new HorizontalLayout();
    private ListDataProvider<ConsultReportAudit> dataProvider;


    public ViewConsultReportAudit() {

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
        grid.addColumn(ConsultReportAudit::getNombreUsuario).setCaption("Nombre usuario");
        grid.addColumn(ConsultReportAudit::getIp).setCaption("Direccion Ip");
        grid.addColumn(ConsultReportAudit::getTipoOperacion).setCaption("Tipo operacion");
        grid.addColumn(ConsultReportAudit::getContenido).setCaption("Contenido");
        grid.addColumn(ConsultReportAudit::getFechaOperacion).setCaption("Fecha");
        grid.setDataProvider(dataProvider);
        grid.setDataProvider(dataProvider);

    }

    private void generateReportAudit() {
        Collection collection = controllerConsultReport.loadAudit();
        dataProvider = DataProvider.ofCollection(collection);
        List list = new ArrayList(collection);
        SerializableSupplier<List<? extends ConsultReportAudit>> itemsSupplier = () -> list;
        reportAudit.setItems(list);
        reportAudit.downloadPdfOnClick(buttonDownload, "ReporteDeAuditoria.pdf", itemsSupplier);

    }

}
