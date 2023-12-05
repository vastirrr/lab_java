module com.example.curs10gui {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;

    opens com.example.curs10gui to javafx.fxml;
    exports com.example.curs10gui;
}