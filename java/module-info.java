module frontend {

    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;


    opens frontend to javafx.fxml;
    exports frontend;
}