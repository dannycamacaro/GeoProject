package com.srb.project.view;

import com.srb.project.controller.ControllerConsultReport;
import com.srb.project.controller.ControllerRoutes;
import com.srb.project.model.RoutesEntity;
import com.srb.project.model.VehicleEntity;
import com.srb.project.pojo.ConsultReportAssignedDevice;
import com.srb.project.pojo.ConsultReportAudit;
import com.srb.project.pojo.ConsultReportDetailRoute;
import com.vaadin.data.provider.DataProvider;
import com.vaadin.data.provider.ListDataProvider;
import com.vaadin.navigator.View;
import com.vaadin.server.Extension;
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
import java.util.Iterator;
import java.util.List;

@UIScope
@SpringView(name = ViewConsultReport.VIEW_NAME)
public class ViewConsultReport extends VerticalLayout implements View {

    public static final String VIEW_NAME = "consultReport";
    @Autowired
    ControllerConsultReport controllerConsultReport;

    @Autowired
    ControllerRoutes controllerRoutes;

    private VerticalLayout menuLayout = new VerticalLayout();
    private PrintPreviewReport<VehicleEntity> reportVehicleActive = new PrintPreviewReport<>(VehicleEntity.class, "idvehicle", "mark", "licenseplate");
    private PrintPreviewReport<ConsultReportAssignedDevice> reportAssignedDevice = new PrintPreviewReport<>(ConsultReportAssignedDevice.class, "imei", "numeroTelefono", "marca", "placa");
    private PrintPreviewReport<ConsultReportAudit> reportAudit = new PrintPreviewReport<>(ConsultReportAudit.class);
    private PrintPreviewReport<ConsultReportDetailRoute> reportDetailRute = new PrintPreviewReport<>(ConsultReportDetailRoute.class, "nombreRuta", "nombreDetalleRuta", "coordenadaLatitud", "coordenadaLongitud");
    private Button buttonVehicleActive = new Button("Consulta vehiculos activos");
    private Button buttonAssignedDevice = new Button("Consulta de dispositivos asignados");
    private Button buttonAudit = new Button("Consulta de auditoria");
    private Button buttonRute = new Button("Consulta de rutas");
    private Button buttonGenerateRute = new Button("Generar reporte de rutas");
    private Grid<RoutesEntity> grid = new Grid<>();
    private VerticalLayout rightLayout = new VerticalLayout();
    private HorizontalLayout principalLayout = new HorizontalLayout();
    private HorizontalLayout gridLayout = new HorizontalLayout();
    private ListDataProvider<RoutesEntity> dataProvider;
    private RoutesEntity routesEntitySelected;
    private Collection collectionRoutes;
    private MenuBar menuBar;

    public ViewConsultReport() {

    }

    @PostConstruct
    private void buildForm() {
        if (menuBar == null)
            menuBar = ViewMenu.buildMenu();

        menuLayout.addComponent(menuBar);
        menuLayout.setHeightUndefined();
        menuLayout.setWidth("100%");
        this.addComponent(menuLayout);
        this.setComponentAlignment(menuLayout, Alignment.MIDDLE_CENTER);

        this.setSizeFull();
        createLeftLayout();
        createRightLayout();
        showFields(false);
        generateReportVehicleActive();
        generateReportAudit();
        generateReportAssignedDevice();
        this.addComponent(principalLayout);

    }

    private void createLeftLayout() {
        menuLayout.addComponent(buttonVehicleActive);
        menuLayout.addComponent(buttonAssignedDevice);
        menuLayout.addComponent(buttonAudit);
        menuLayout.addComponent(buttonRute);
        buildActionRute();
        principalLayout.addComponent(menuLayout);

    }

    private void buildActionRute() {

        buttonRute.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                showFields(true);
                if (buttonGenerateRute == null) {
                    buttonGenerateRute = new Button("Generar reporte de rutas");
                }
            }
        });
    }

    private void createRightLayout() {
        rightLayout.setSizeFull();
        gridLayout.addComponent(grid);
        grid.setSizeFull();
        refreshInformationGrid();
        grid.addColumn(RoutesEntity::getNameroutes).setCaption("Nombre de Ruta");
        grid.addColumn(RoutesEntity::getDescription).setCaption("Descripcion de Ruta");
        grid.setDataProvider(dataProvider);
        grid.addItemClickListener(new ItemClickListener<RoutesEntity>() {
            @Override
            public void itemClick(Grid.ItemClick<RoutesEntity> event) {
                routesEntitySelected = event.getItem();
                collectionRoutes.clear();
                generateReportRute();
            }
        });
        grid.setDataProvider(dataProvider);
        rightLayout.addComponent(buttonGenerateRute);
        rightLayout.addComponent(gridLayout);
        principalLayout.addComponent(rightLayout);
    }

    private void generateReportVehicleActive() {
        Collection collection = controllerConsultReport.findAllVehicleActive();
        List list = new ArrayList(collection);
        SerializableSupplier<List<? extends VehicleEntity>> itemsSupplier = () -> list;
        reportVehicleActive.setItems(list);
        reportVehicleActive.downloadPdfOnClick(buttonVehicleActive, "VehiculosActivos.pdf", itemsSupplier);

    }

    private void generateReportAssignedDevice() {
        Collection collection = controllerConsultReport.findAssignedDevice();
        List list = new ArrayList(collection);
        SerializableSupplier<List<? extends ConsultReportAssignedDevice>> itemsSupplier = () -> list;
        reportAssignedDevice.setItems(list);
        reportAssignedDevice.downloadPdfOnClick(buttonAssignedDevice, "ReporteDispositivosAsignados.pdf", itemsSupplier);

    }

    private void generateReportAudit() {
        Collection collection = controllerConsultReport.loadAudit();
        List list = new ArrayList(collection);
        SerializableSupplier<List<? extends ConsultReportAudit>> itemsSupplier = () -> list;
        reportAudit.setItems(list);
        reportAudit.downloadPdfOnClick(buttonAudit, "ReporteDeAuditoria.pdf", itemsSupplier);

    }

    private void generateReportRute() {
        Iterator extensions = buttonGenerateRute.getExtensions().iterator();
        int size = buttonGenerateRute.getExtensions().size();
        while (extensions.hasNext()) {
            try {
                Extension extension = (Extension) extensions.next();
                buttonGenerateRute.removeExtension(extension);
                extensions = buttonGenerateRute.getExtensions().iterator();

            } catch (Exception e) {
                e.printStackTrace();
            }

        }


        reportDetailRute = null;
        if (reportDetailRute == null) {
            reportDetailRute = new PrintPreviewReport<>(ConsultReportDetailRoute.class, "nombreRuta", "nombreDetalleRuta", "coordenadaLatitud", "coordenadaLongitud");
        }
        collectionRoutes = controllerConsultReport.loadRoute(routesEntitySelected);
        List list = new ArrayList(collectionRoutes);
        SerializableSupplier<List<? extends ConsultReportDetailRoute>> itemsSupplier = () -> list;

        reportDetailRute.setItems(list);
        reportDetailRute.downloadPdfOnClick(buttonGenerateRute, "ReporteDetalleRuta.pdf", itemsSupplier);
    }


    private void refreshInformationGrid() {
        collectionRoutes = controllerRoutes.findAllRoutes();
        dataProvider = DataProvider.ofCollection(collectionRoutes);
        grid.setDataProvider(dataProvider);
    }

    private void showFields(Boolean value) {
        rightLayout.setVisible(value);
    }
}
