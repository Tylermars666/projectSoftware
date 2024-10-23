package co.edu.uniquindio.ingsoftwareproject.controller;

import co.edu.uniquindio.ingsoftwareproject.model.enums.TipoAlerta;
import co.edu.uniquindio.ingsoftwareproject.user.Alertas;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class RegistroController {

    @FXML
    private ImageView imgHuella;


    @FXML
    void marcarEntrada(ActionEvent event) {

        setHuella();
        Alertas.alertar(TipoAlerta.CONFIRMATION,"Huella Entrada","Entrada registrada con éxito");
        this.imgHuella.setImage(null);


    }

    @FXML
    void marcarSalida(ActionEvent event) {

        setHuella();
        Alertas.alertar(TipoAlerta.CONFIRMATION,"Huella Salida","Salida registrada con éxito");
        this.imgHuella.setImage(null);

    }

    //--------------------METODO AUX------------------------


    public void setHuella(){

        String imagePath = "/co/edu/uniquindio/ingsoftwareproject/assets/Huellas/ImagenHuella4.jpg";
        Image image = new Image(getClass().getResourceAsStream(imagePath));
        this.imgHuella.setImage(image);

    }

}
