package co.edu.uniquindio.ingsoftwareproject.controller;

import co.edu.uniquindio.ingsoftwareproject.services.implement.LoginServiceImpl;
import co.edu.uniquindio.ingsoftwareproject.services.interfaces.LoginService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import lombok.Setter;

public class LoginController {

    LoginService loginService = new LoginServiceImpl();
    @Setter
    Stage loginStage;
    private final String RUTA_ADMIN= "/co/edu/uniquindio/ingsoftwareproject/view/administrador-view.fxml";
    private final String RUTA_CLIENTE = "/co/edu/uniquindio/ingsoftwareproject/view/user-view.fxml";

    @FXML
    private TextField txtPasswordLogin;

    @FXML
    private TextField txtUserNameLogin;

    @FXML
    void btnIngresarUsuarioLogin(ActionEvent event) throws Exception{

        String username = txtUserNameLogin.getText();
        String password = txtPasswordLogin.getText();
        String response = loginService.login(username,password);

        nuevaVentana(response);

    }

    private void nuevaVentana(String response) throws Exception{

        String ruta = "";

        if(response.equals("CLIENTE")){
            ruta = RUTA_CLIENTE;
        }else if(response.equals("ADMINISTRADOR")){
            ruta = RUTA_ADMIN;
        }

        FXMLLoader loader = new FXMLLoader(getClass().getResource(ruta));

        Scene scene = new Scene(loader.load());
        Stage stage = new Stage();
        stage.setTitle(response);
        stage.setScene(scene);
        stage.show();
        loginStage.close();

    }

}

