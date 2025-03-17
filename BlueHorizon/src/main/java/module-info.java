module com.mycompany.telaspi {
    requires javafx.controls;
    requires javafx.fxml;
    
    exports com.mycompany.telaspi;
    opens com.mycompany.telaspi to javafx.fxml;
}
