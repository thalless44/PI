module principal {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires jakarta.mail; // This is the key line!

    opens controller to javafx.fxml;
    exports principal;
}