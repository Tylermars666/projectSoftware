package co.edu.uniquindio.ingsoftwareproject.controller;

import co.edu.uniquindio.ingsoftwareproject.dto.adminDto.ActualizarEmpleadoDTO;
import co.edu.uniquindio.ingsoftwareproject.dto.adminDto.ControlTimexDTO;
import co.edu.uniquindio.ingsoftwareproject.dto.adminDto.NotificacionDTO;
import co.edu.uniquindio.ingsoftwareproject.dto.userDto.CrearEmpleadoDTO;
import co.edu.uniquindio.ingsoftwareproject.dto.userDto.ReporteDto;
import co.edu.uniquindio.ingsoftwareproject.model.enums.TipoAlerta;
import co.edu.uniquindio.ingsoftwareproject.model.enums.TipoUsuario;
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
    ObservableList<NotificacionDTO> notificaciones;
    String tipoSeleccionado;
    AdminService adminService = new AdminServiceImpl();
    UserService userService = new UserServiceImpl();
    ControlTimexDTO controlRegistroSeleccionado;

    @FXML
    void seleccionarRegistro(MouseEvent event) {

        this.btnActualizar.setDisable(false);
        this.btnGenerarReporte.setDisable(false);

        controlRegistroSeleccionado = this.tblValidacionIO.getSelectionModel().getSelectedItem();
        Alertas.alertar(TipoAlerta.INFORMATION,"Seleccion","Se ha seleccionado el registro con rut: "+controlRegistroSeleccionado.rut());
        this.txtRutUnEditable.setText(controlRegistroSeleccionado.rut());
        this.txtActualizarHoraEntrada.setText(controlRegistroSeleccionado.horaEntrada());
        this.txtActualizarHoraSalida.setText(controlRegistroSeleccionado.horaSalida());

    }

    @FXML
    void btnActualizarValidacionControlTimeX(ActionEvent event) {


        try{
            String response = adminService.actualizarEmpleado(new ActualizarEmpleadoDTO(
                    this.controlRegistroSeleccionado.dia(),this.txtActualizarHoraEntrada.getText(),
                    this.txtActualizarHoraSalida.getText(),this.controlRegistroSeleccionado.rut()
            ));
            Alertas.alertar(TipoAlerta.INFORMATION,"Actualizacion",response);
            this.registrosTimex.clear();
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


        String username = this.txtUserNameEmpleado.getText();
        String password = this.txtPasswordEmpleado.getText();
        String rut = this.txtRegistroUnicoTributarioEmpleado.getText();
        String nombre = this.txtNombreCompletoEmpleado.getText();
        String direccion = this.txtDireccionEmpleado.getText();
        String telefono = this.txtTelefonoEmpleado.getText();
        TipoUsuario tipoUsuario = TipoUsuario.valueOf(tipoSeleccionado);
        String huella = "/co/edu/uniquindio/ingsoftwareproject/assets/Huellas/ImagenHuella4.jpg";

        if(username==null || username.isBlank() || password==null || password.isBlank() ||
                rut==null || rut.isBlank() || nombre==null || nombre.isBlank() ||
                direccion==null || direccion.isBlank() || telefono==null || telefono.isBlank() ||
        tipoSeleccionado==null || tipoSeleccionado.isBlank()){

            Alertas.alertar(TipoAlerta.ERROR,"Campos en blanco","Asegurese de llenar todos los campos");

        }else{

            try{

                String response = adminService.registrarEmpleado(new CrearEmpleadoDTO(username,
                        password,rut,nombre,direccion,telefono,tipoUsuario,huella));
                Alertas.alertar(TipoAlerta.INFORMATION,"Creacion", response);

                this.txtUserNameEmpleado.setText("");
                this.txtPasswordEmpleado.setText("");
                this.txtRegistroUnicoTributarioEmpleado.setText("");
                this.txtNombreCompletoEmpleado.setText("");
                this.txtDireccionEmpleado.setText("");
                this.txtTelefonoEmpleado.setText("");

            } catch (Exception e) {
                e.printStackTrace();
            }

        }


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
        this.cmbTipoEmpleado.setItems(tipoUsuarios);


        try{
            actualizarTablaTimeX("");
            actualizarTablaNotificaciones();
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

        notificaciones = FXCollections.observableArrayList();

        this.callRutNotificacion.setCellValueFactory(
                (TableColumn.CellDataFeatures<NotificacionDTO,String> p) ->
        new SimpleStringProperty(p.getValue().rut().toString())
        );

        this.callNombreNotificacion.setCellValueFactory(
                (TableColumn.CellDataFeatures<NotificacionDTO,String> p) ->
                        new SimpleStringProperty(p.getValue().nombre().toString())
        );
        this.callJustificacionNotificacion.setCellValueFactory(
                (TableColumn.CellDataFeatures<NotificacionDTO,String> p) ->
                        new SimpleStringProperty(p.getValue().justificacion().toString())
        );

        this.notificaciones.addAll(adminService.listarNotificaciones());
        this.tblValidacionIO1.setItems(notificaciones);


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
