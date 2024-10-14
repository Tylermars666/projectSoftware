module co.edu.uniquindio.ingsoftwareproject {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;
    requires static lombok;

    opens co.edu.uniquindio.ingsoftwareproject to javafx.fxml;
    exports co.edu.uniquindio.ingsoftwareproject;
}