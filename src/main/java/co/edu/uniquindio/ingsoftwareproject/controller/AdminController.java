package co.edu.uniquindio.ingsoftwareproject.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuButton;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

public class AdminController {

    @FXML
    private TableColumn<?, ?> callDiaValidacionIO;

    @FXML
    private TableColumn<?, ?> callHoraEntradaValidacionIO;

    @FXML
    private TableColumn<?, ?> callHoraSalidaValidacionIO;

    @FXML
    private TableColumn<?, ?> callJustificacionNotificacion;

    @FXML
    private TableColumn<?, ?> callNombreNotificacion;

    @FXML
    private TableColumn<?, ?> callNombreValidacionIO;

    @FXML
    private TableColumn<?, ?> callRutNotificacion;

    @FXML
    private TableColumn<?, ?> callRutValidacionIO;

    @FXML
    private MenuButton cmbCargoEmpleado;

    @FXML
    private TableView<?> tblValidacionIO;

    @FXML
    private TableView<?> tblValidacionIO1;

    @FXML
    private TextField txtActualizarHoraEntrada;

    @FXML
    private TextField txtActualizarHoraSalida;

    @FXML
    private TextField txtDireccionEmpleado;

    @FXML
    private TextField txtNombreCompletoEmpleado;

    @FXML
    private TextField txtPasswordEmpleado;

    @FXML
    private TextField txtRePasswordEmpleado;

    @FXML
    private TextField txtRegistroUnicoTributarioEmpleado;

    @FXML
    private TextField txtRutUnEditable;

    @FXML
    private TextField txtTelefonoEmpleado;

    @FXML
    private TextField txtUserNameEmpleado;

    @FXML
    private TextField txtValidacionRUTControlTimeX;

    @FXML
    private Tab ventanaRegistroEmpleado;

    @FXML
    private Tab ventanaValidacionControlTimeX;

    @FXML
    private Tab ventanaValidacionControlTimeX1;

    @FXML
    void SeleccionarRegistro(KeyEvent event) {

    }

    @FXML
    void btnActualizarValidacionControlTimeX(ActionEvent event) {

    }

    @FXML
    void btnConsultarValidacion(ActionEvent event) {

    }

    @FXML
    void btnGenerarReporteValidacionControlTimeX(ActionEvent event) {

    }

    @FXML
    void filtroCargoEmpleado(ActionEvent event) {

    }

    @FXML
    void registrarEmpleado(ActionEvent event) {

    }

    @FXML
    void registrarHuellaEmpleado(ActionEvent event) {

    }

}
