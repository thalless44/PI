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
import javafx.scene.image.Image;
import model.FuncionarioDAO;

public class App extends Application {
    
    //Login: user@gmail.com
    //Senha: 1
    

    @Override
    public void start(Stage stage) throws IOException {
       
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
        
        Image icone = new Image(getClass().getResourceAsStream("/icons/Bh.png"));
        telaLogin.getIcons().add(icone);
        
        telaLogin.setScene(scene);
        telaLogin.setTitle("BlueHorizon - Sistema de gerenciamento de propriedades beira-mar | Login");
        telaLogin.setMaximized(true);
        telaLogin.show();
    }

    public static void main(String[] args) {
        launch();
    }
    //int idParaDeletar = 2;  // Coloque o ID de um funcionário válido

         //Chama o método deletarFuncionario e obtém o retorno (true ou false)
        // boolean sucesso = FuncionarioDAO.deletarFuncionario(idParaDeletar);
}