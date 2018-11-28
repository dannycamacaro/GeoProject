package com.srb.project.view;

import com.srb.project.controller.ControllerConsultReport;
import com.srb.project.enumConstans.EnumLabel;
import com.srb.project.pojo.ConsultReportAssignedDevice;
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
@SpringView(name = ViewConsultReportAssignedDevice.VIEW_NAME)
public class ViewConsultReportAssignedDevice extends VerticalLayout implements View {

    public static final String VIEW_NAME = "consultReportAssignedDevice";
    @Autowired
    ControllerConsultReport controllerConsultReport;


    //Layouts
    private HorizontalLayout menuLayout = new HorizontalLayout();
    private MenuBar menuBar;
    private PrintPreviewReport<ConsultReportAssignedDevice> reportAssignedDevice = new PrintPreviewReport<>(ConsultReportAssignedDevice.class, "imei", "numeroTelefono", "marca", "placa");
    private Button buttonDownload = new Button(EnumLabel.DOWNLOAD.getLabel());;
    private Grid<ConsultReportAssignedDevice> grid = new Grid<>();
    private VerticalLayout principalLayout = new VerticalLayout();
    private Panel principalPanel = new Panel("Reporte de Dispositivos");
    private HorizontalLayout gridLayout = new HorizontalLayout();
    private HorizontalLayout buttonLayout = new HorizontalLayout();
    private ListDataProvider<ConsultReportAssignedDevice> dataProvider;


    public ViewConsultReportAssignedDevice() {

    }

    @PostConstruct
    private void buildForm() {

        this.setWidth("100%");
        this.setHeightUndefined();

        if (menuBar == null)
            menuBar = ViewMenu.buildMenu();
        menuLayout.addComponent(menuBar);
        generateReportAssignedDevice();
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
        grid.addColumn(ConsultReportAssignedDevice::getImei).setCaption("Imei");
        grid.addColumn(ConsultReportAssignedDevice::getNumeroTelefono).setCaption("Numero de telefono");
        grid.addColumn(ConsultReportAssignedDevice::getMarca).setCaption("Marca");
        grid.addColumn(ConsultReportAssignedDevice::getPlaca).setCaption("Matricula");
        grid.setDataProvider(dataProvider);
        grid.setDataProvider(dataProvider);

    }

    private void generateReportAssignedDevice() {
        Collection collection = controllerConsultReport.findAssignedDevice();
        dataProvider = DataProvider.ofCollection(collection);
        List list = new ArrayList(collection);
        SerializableSupplier<List<? extends ConsultReportAssignedDevice>> itemsSupplier = () -> list;
        reportAssignedDevice.setItems(list);
        reportAssignedDevice.downloadPdfOnClick(buttonDownload, "ReporteDispositivosAsignados.pdf", itemsSupplier);

    }

}
