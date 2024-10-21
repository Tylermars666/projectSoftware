package co.edu.uniquindio.ingsoftwareproject.controller;

import co.edu.uniquindio.ingsoftwareproject.dto.adminDto.ActualizarEmpleadoDTO;
import co.edu.uniquindio.ingsoftwareproject.dto.adminDto.ControlTimexDTO;
import co.edu.uniquindio.ingsoftwareproject.dto.adminDto.NotificacionDTO;
import co.edu.uniquindio.ingsoftwareproject.dto.userDto.ReporteDto;
import co.edu.uniquindio.ingsoftwareproject.model.enums.TipoAlerta;
import co.edu.uniquindio.ingsoftwareproject.services.implement.AdminServiceImpl;
import co.edu.uniquindio.ingsoftwareproject.services.implement.UserServiceImpl;
import co.edu.uniquindio.ingsoftwareproject.services.interfaces.AdminService;
import co.edu.uniquindio.ingsoftwareproject.services.interfaces.UserService;
import co.edu.uniquindio.ingsoftwareproject.user.Alertas;
import co.edu.uniquindio.ingsoftwareproject.user.UsuarioLoggeado;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class AdminController implements Initializable {

    @FXML
    private TableColumn<ControlTimexDTO, String> callDiaValidacionIO;
    @FXML
    private TableColumn<ControlTimexDTO, String> callHoraEntradaValidacionIO;
    @FXML
    private TableColumn<ControlTimexDTO, String> callHoraSalidaValidacionIO;
    @FXML
    private TableColumn<NotificacionDTO, String> callJustificacionNotificacion;
    @FXML
    private TableColumn<NotificacionDTO, String> callNombreNotificacion;
    @FXML
    private TableColumn<ControlTimexDTO, String> callNombreValidacionIO;
    @FXML
    private TableColumn<NotificacionDTO, String> callRutNotificacion;
    @FXML
    private TableColumn<ControlTimexDTO, String> callRutValidacionIO;
    @FXML
    private TableView<ControlTimexDTO> tblValidacionIO;
    @FXML
    private TableView<NotificacionDTO> tblValidacionIO1;
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
    private ComboBox<String> cmbTipoEmpleado;
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
    private Button btnActualizar;
    @FXML
    private Button btnGenerarReporte;
    @FXML
    private ImageView imgHuella;

    ObservableList<String> tipoUsuarios;
    ObservableList<ControlTimexDTO> registrosTimex;
    String tipoSeleccionado;
    AdminService adminService = new AdminServiceImpl();
    UserService userService = new UserServiceImpl();
    ControlTimexDTO controlRegistroSeleccionado;

    @FXML
    void seleccionarRegistro(MouseEvent event) {

        this.btnActualizar.setDisable(false);
        this.btnGenerarReporte.setDisable(false);

        controlRegistroSeleccionado = this.tblValidacionIO.getSelectionModel().getSelectedItem();
        this.txtRutUnEditable.setText(controlRegistroSeleccionado.rut());
        this.txtActualizarHoraEntrada.setText(controlRegistroSeleccionado.horaEntrada());
        this.txtActualizarHoraSalida.setText(controlRegistroSeleccionado.horaSalida());

    }

    @FXML
    void btnActualizarValidacionControlTimeX(ActionEvent event) {


        try{
            adminService.actualizarEmpleado(new ActualizarEmpleadoDTO(
                    this.controlRegistroSeleccionado.dia(),this.controlRegistroSeleccionado.horaEntrada(),
                    this.controlRegistroSeleccionado.horaSalida(),this.controlRegistroSeleccionado.rut()
            ));
            actualizarTablaTimeX("");
        }catch (Exception e){

            e.printStackTrace();
        }


    }

    @FXML
    void btnConsultarValidacion(ActionEvent event) {

        if(this.txtValidacionRUTControlTimeX.getText().isEmpty() || this.txtValidacionRUTControlTimeX == null){
            Alertas.alertar(TipoAlerta.ERROR,"Rut","Debe ingresar un rut primero");
        }else{

            try{
                actualizarTablaTimeX(this.txtValidacionRUTControlTimeX.getText());
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }

    @FXML
    void btnGenerarReporteValidacionControlTimeX(ActionEvent event) {

        String fechaStr = controlRegistroSeleccionado.dia();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate fecha = LocalDate.parse(fechaStr, formatter);
        int mes = fecha.getMonthValue();

        try{
            ReporteDto reporteDto = userService.generarReporte(
                    this.controlRegistroSeleccionado.rut(),
                    mes
            );
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/edu/uniquindio/ingsoftwareproject/view/reporte-view.fxml"));

            Scene scene = new Scene(loader.load());
            Stage stage = new Stage();
            stage.setTitle("Reporte");
            stage.setScene(scene);
            stage.show();
            ReporteController controller = loader.getController();
            controller.settearReporte(reporteDto);

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @FXML
    void registrarEmpleado(ActionEvent event) {

    }

    @FXML
    void registrarHuellaEmpleado(ActionEvent event) {

        //TODO
        String imagePath = "/co/edu/uniquindio/ingsoftwareproject/assets/Huellas/ImagenHuella4.jpg";
        Image image = new Image(getClass().getResourceAsStream(imagePath));

        this.imgHuella.setImage(image);

    }

    @FXML
    void seleccionarTipoEmpleado(ActionEvent event) {

        tipoSeleccionado = cmbTipoEmpleado.getSelectionModel().getSelectedItem();


    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        this.tipoUsuarios = FXCollections.observableArrayList("CLIENTE", "ADMINISTRADOR");


        try{
            actualizarTablaTimeX("");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void actualizarTablaTimeX(String rut) throws  Exception{

        this.registrosTimex = FXCollections.observableArrayList();

        this.callRutValidacionIO.setCellValueFactory(
                (TableColumn.CellDataFeatures<ControlTimexDTO,String> p) ->
                        new SimpleStringProperty(p.getValue().rut().toString())
        );
        this.callNombreValidacionIO.setCellValueFactory(
                (TableColumn.CellDataFeatures<ControlTimexDTO,String> p) ->
                        new SimpleStringProperty(p.getValue().nombre().toString())
        );
        this.callDiaValidacionIO.setCellValueFactory(
                (TableColumn.CellDataFeatures<ControlTimexDTO,String> p) ->
                        new SimpleStringProperty(p.getValue().dia().toString())
        );
        this.callHoraEntradaValidacionIO.setCellValueFactory(
                (TableColumn.CellDataFeatures<ControlTimexDTO,String> p) ->
                        new SimpleStringProperty(p.getValue().horaEntrada().toString())
        );
        this.callHoraSalidaValidacionIO.setCellValueFactory(
                (TableColumn.CellDataFeatures<ControlTimexDTO,String> p) ->
                        new SimpleStringProperty(p.getValue().horaSalida().toString())
        );

        if(rut.isBlank()){

            this.registrosTimex.addAll(this.adminService.listarEmpleados());

        }else{

            this.registrosTimex.clear();
            this.registrosTimex.addAll(this.adminService.listarEmpleadosPorRut(rut));

        }

        this.tblValidacionIO.setItems(this.registrosTimex);

    }

    public void actualizarTablaNotificaciones() throws Exception{



    }

    @FXML
    void reset(ActionEvent event) {

        this.txtValidacionRUTControlTimeX.setText("");
        try{
            this.registrosTimex.clear();
            actualizarTablaTimeX("");
        }catch (Exception e){
            e.printStackTrace();
        }

    }


    public void SeleccionarRegistro(KeyEvent keyEvent) {
    }
}
