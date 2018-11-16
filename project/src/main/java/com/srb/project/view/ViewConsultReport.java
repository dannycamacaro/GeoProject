package com.srb.project.view;

import com.srb.project.controller.ControllerConsultReport;
import com.srb.project.model.VehicleEntity;
import com.srb.project.pojo.ConsultReportAssignedDevice;
import com.vaadin.navigator.View;
import com.vaadin.server.SerializableSupplier;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;

import com.vaadin.ui.Button;
import com.vaadin.ui.VerticalLayout;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.reports.PrintPreviewReport;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@UIScope
@SpringView(name = ViewConsultReport.VIEW_NAME)
public class ViewConsultReport extends VerticalLayout implements View {

    public static final String VIEW_NAME = "consultReport";
    @Autowired
    ControllerConsultReport controllerConsultReport;

    private VerticalLayout menuLayout = new VerticalLayout();
    private PrintPreviewReport<VehicleEntity>  reportVehicleActive = new PrintPreviewReport<>(VehicleEntity.class, "idvehicle", "mark","licenseplate");
    private PrintPreviewReport<ConsultReportAssignedDevice>  reportAssignedDevice = new PrintPreviewReport<>(ConsultReportAssignedDevice.class);
    private Button buttonVehicleActive = new Button("Consulta vehiculos activos");
    private Button buttonAssignedDevice = new Button("Consulta de vehiculos asignados");


    public ViewConsultReport() {

    }

    @PostConstruct
    private void buildForm() {
        generateReportVehicleActive();
        menuLayout.addComponent(buttonVehicleActive);
        this.addComponent(menuLayout);

    }

    private void generateReportVehicleActive(){
        Collection collection = controllerConsultReport.findAllVehicleActive();
        List list = new ArrayList(collection);
        SerializableSupplier<List<? extends VehicleEntity>> itemsSupplier = () -> list;
        reportVehicleActive.setItems(list);
        reportVehicleActive.downloadPdfOnClick(buttonVehicleActive, "VehiculosActivos.pdf", itemsSupplier);

    }

    private void generateReportAssignedDevice(){


    }
}
