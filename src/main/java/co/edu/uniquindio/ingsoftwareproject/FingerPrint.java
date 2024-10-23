package co.edu.uniquindio.ingsoftwareproject;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class FingerPrint extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(SalaryApp.class.getResource("view/registro-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("FingerPrint");
        stage.setScene(scene);
        stage.show();

    }

    public static void main(String[] args) {
        launch();
    }
}