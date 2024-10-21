package co.edu.uniquindio.ingsoftwareproject.controller;

import co.edu.uniquindio.ingsoftwareproject.dto.registroDto.RegistroClienteDTO;
import co.edu.uniquindio.ingsoftwareproject.dto.userDto.ReporteDto;
import co.edu.uniquindio.ingsoftwareproject.model.enums.TipoAlerta;
import co.edu.uniquindio.ingsoftwareproject.services.implement.UserServiceImpl;
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
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import lombok.Setter;

import java.net.URL;
import java.util.ResourceBundle;

@Setter
public class UserController implements Initializable {

    private UserService userService = new UserServiceImpl();
    @FXML
    private TableColumn<RegistroClienteDTO, String> callFechaLaborada;
    @FXML
    private TableColumn<RegistroClienteDTO, String> callHoraEntradaLaborada;
    @FXML
    private TableColumn<RegistroClienteDTO, String> callHoraSalidaLaborada;
    @FXML
    private TableColumn<RegistroClienteDTO, String> callTotalHorasLaboradas;
    @FXML
    private ComboBox<String> cmbMesLaborado;
    @FXML
    private TableView<RegistroClienteDTO> horasTable;
    @FXML
    private TextField txtJustificacionNotificacion;

    private ObservableList<RegistroClienteDTO> registros;

    private ObservableList<String> opcionesMeses;

    private String mesSeleccionado;

    @FXML
    void btnEnviarNotificacionEmpleado(ActionEvent event) {

        String justificacion = txtJustificacionNotificacion.getText();

        if(justificacion==null || justificacion.isBlank()){

            Alertas.alertar(TipoAlerta.ERROR,"Campo vacío","Asegurese de escribir un mensaje");

        }else{
            try{
                String response = userService.enviarNotificacion(UsuarioLoggeado.instance.getRut(), justificacion);
                Alertas.alertar(TipoAlerta.INFORMATION,"Notificación",response);
                this.txtJustificacionNotificacion.setText("");
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    @FXML
    void btnGenerarReporteHorasLaboradas(ActionEvent event) {

        if(mesSeleccionado==null){

            Alertas.alertar(TipoAlerta.ERROR,"Mes seleccionado", "Primero debe seleccionar un mes");

        }else{
            try{

                ReporteDto reporteDto = userService.generarReporte(UsuarioLoggeado.instance.getRut(),Integer.parseInt(mesSeleccionado));
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


    }

    @FXML
    void filtroSeleccionMesLaborados(ActionEvent event) {
        mesSeleccionado = cmbMesLaborado.getSelectionModel().getSelectedItem();
    }

    @FXML
    void consultarRegistrosPorMes(ActionEvent event) {

        if(mesSeleccionado==null){
            Alertas.alertar(TipoAlerta.ERROR,"Error mes", "Primero debe seleccionar un mes");
        }else{
            try{
                actualizarTabla(mesSeleccionado);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    @FXML
    void reset(ActionEvent event) {

        mesSeleccionado=null;

        try{
            actualizarTabla("");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)  {

        opcionesMeses = FXCollections.observableArrayList("1","2","3","4","5","6","7","8","9","10","11","12");
        cmbMesLaborado.setItems(opcionesMeses);

        try{
            actualizarTabla("");
        }catch (Exception e){
            e.printStackTrace();
        }




    }

    public void actualizarTabla(String mes) throws Exception {

        this.registros = FXCollections.observableArrayList();

        this.callFechaLaborada.setCellValueFactory(
                (TableColumn.CellDataFeatures<RegistroClienteDTO, String> p) ->
                        new SimpleStringProperty(p.getValue().fecha().toString())
        );
        this.callHoraEntradaLaborada.setCellValueFactory(
                (TableColumn.CellDataFeatures<RegistroClienteDTO, String> p) ->
                        new SimpleStringProperty(p.getValue().horaEntrada().toString())
        );
        this.callHoraSalidaLaborada.setCellValueFactory(
                (TableColumn.CellDataFeatures<RegistroClienteDTO, String> p) ->
                        new SimpleStringProperty(p.getValue().horaSalida().toString())
        );
        this.callTotalHorasLaboradas.setCellValueFactory(
                (TableColumn.CellDataFeatures<RegistroClienteDTO, String> p) ->
                        new SimpleStringProperty(p.getValue().totalHoras().toString())
        );

        if (mes.isBlank()) {
            this.registros.addAll(this.userService.listarRegistros(UsuarioLoggeado.getInstance().getRut()));
        } else {
            this.registros.clear();
            this.registros.addAll(this.userService.listarRegistrosPorMes(Integer.parseInt(mes), UsuarioLoggeado.getInstance().getRut()));
        }

        this.horasTable.setItems(this.registros);
    }

}
