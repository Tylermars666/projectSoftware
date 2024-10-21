package co.edu.uniquindio.ingsoftwareproject;


import co.edu.uniquindio.ingsoftwareproject.controller.LoginController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SalaryApp extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(SalaryApp.class.getResource("view/login-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("SalaryApp");
        stage.setScene(scene);
        stage.show();
        LoginController controller = fxmlLoader.getController();
        controller.setLoginStage(stage);


    }

    public static void main(String[] args) {
        launch();
    }
}