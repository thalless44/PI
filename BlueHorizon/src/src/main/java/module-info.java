module principal {
    requires javafx.controls;
    requires javafx.fxml;

    opens controller to javafx.fxml;
    exports principal;
}
