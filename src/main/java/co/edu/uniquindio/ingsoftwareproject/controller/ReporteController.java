package co.edu.uniquindio.ingsoftwareproject.controller;

import co.edu.uniquindio.ingsoftwareproject.dto.userDto.ReporteDto;
import co.edu.uniquindio.ingsoftwareproject.model.enums.TipoAlerta;
import co.edu.uniquindio.ingsoftwareproject.user.Alertas;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class ReporteController {


    @FXML
    private TextField txtDescuentoPensionReporte;

    @FXML
    private TextField txtDescuentoSaludReporte;

    @FXML
    private TextField txtNombreReporte;

    @FXML
    private TextField txtRutReporte;

    @FXML
    private TextField txtSalarioBrutoReporte;

    @FXML
    private TextField txtSalarioNetoReporte;

    @FXML
    void btnImprimirReporte(ActionEvent event) {

        Alertas.alertar(TipoAlerta.CONFIRMATION,"Imprimir","Imprimiendo reporte...");

    }

    public void settearReporte(ReporteDto reporteDto){

        this.txtNombreReporte.setText(reporteDto.nombre());
        this.txtDescuentoPensionReporte.setText(reporteDto.descuentoPension());
        this.txtDescuentoSaludReporte.setText(reporteDto.descuentoSalud());
        this.txtRutReporte.setText(reporteDto.rut());
        this.txtSalarioBrutoReporte.setText(reporteDto.salarioBruto());
        this.txtSalarioNetoReporte.setText(reporteDto.salarioNeto());

    }




}
