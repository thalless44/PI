package com.mycompany.telaspi;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

import static javafx.application.Application.launch;

public class App extends Application {

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/TelaLoginPI.fxml"));
        Parent root = loader.load();

        Scene scene = new Scene(root);

        stage.setTitle("BlueHorizon - Sistema de gerenciamento de propriedades beira-mar");
        stage.setScene(scene);
        
        stage.setMaximized(true);
        stage.show();

    }

}