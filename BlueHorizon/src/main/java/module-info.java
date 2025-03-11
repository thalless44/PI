module com.mycompany.telaspi {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;

    opens com.mycompany.telaspi to javafx.fxml;
    exports com.mycompany.telaspi;
}
