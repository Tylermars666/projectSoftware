package co.edu.uniquindio.ingsoftwareproject.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import lombok.Setter;

@Setter
public class UserController {

    private Stage userStage;

    @FXML
    private Button btnConsultar;

    @FXML
    private TableColumn<?, ?> callFechaLaborada;

    @FXML
    private TableColumn<?, ?> callHoraEntradaLaborada;

    @FXML
    private TableColumn<?, ?> callHoraSalidaLaborada;

    @FXML
    private TableColumn<?, ?> callTotalHorasLaboradas;

    @FXML
    private ComboBox<?> cmbMesLaborado;

    @FXML
    private TableView<?> horasTable;

    @FXML
    private TextField txtJustificacionNotificacion;

    @FXML
    void btnEnviarNotificacionEmpleado(ActionEvent event) {

    }

    @FXML
    void btnGenerarReporteHorasLaboradas(ActionEvent event) {

    }

    @FXML
    void filtroSeleccionMesLaborados(ActionEvent event) {

    }

}
