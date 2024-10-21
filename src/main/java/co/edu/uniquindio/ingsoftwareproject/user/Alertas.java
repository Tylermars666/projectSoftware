package co.edu.uniquindio.ingsoftwareproject.user;

import co.edu.uniquindio.ingsoftwareproject.model.enums.TipoAlerta;
import javafx.scene.control.Alert;

public class Alertas {


    public static void alertar(TipoAlerta tipo, String titulo, String mensaje){

        String alerta = String.valueOf(tipo);
        Alert alert = new Alert(Alert.AlertType.valueOf(alerta));
        alert.setHeaderText(null);
        alert.setTitle(titulo);
        alert.setContentText(mensaje);
        alert.showAndWait();

    }

}
