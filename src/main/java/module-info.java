module co.edu.uniquindio.ingsoftwareproject {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;
    requires static lombok;

    opens co.edu.uniquindio.ingsoftwareproject to javafx.fxml;
    exports co.edu.uniquindio.ingsoftwareproject;
    exports co.edu.uniquindio.ingsoftwareproject.controller to javafx.fxml;
    opens co.edu.uniquindio.ingsoftwareproject.controller to javafx.fxml;
    exports co.edu.uniquindio.ingsoftwareproject.repository;
    exports co.edu.uniquindio.ingsoftwareproject.model.classes;
    exports co.edu.uniquindio.ingsoftwareproject.model.enums;
    exports co.edu.uniquindio.ingsoftwareproject.repository.implement;
    exports co.edu.uniquindio.ingsoftwareproject.repository.interfaces;
    exports co.edu.uniquindio.ingsoftwareproject.services.implement;
}