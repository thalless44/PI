package principal;

import controller.TelaLoginPIController;
import java.io.File;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class App extends Application {

    

    @Override
    public void start(Stage stage) throws IOException {
        stage.setTitle("Enter your Trip");
        URL url = new File("src/main/java/view/TelaLoginPI.fxml").toURI().toURL();
        FXMLLoader loader = new FXMLLoader(url);
        Parent root = loader.load();
        Stage telaLogin = new Stage();
        TelaLoginPIController lc = loader.getController();
        lc.setStage(telaLogin);
        telaLogin.setOnShown(event -> {
            lc.abrirJanela();
        });
        Scene scene = new Scene (root);
        telaLogin.setScene(scene);
        telaLogin.show();
    }

    public static void main(String[] args) {
        launch();
    }

}