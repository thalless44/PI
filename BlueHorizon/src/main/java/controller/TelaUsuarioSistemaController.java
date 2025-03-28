package controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.stage.Stage;

public class TelaUsuarioSistemaController {
    
    private Stage stage;
    
    
        @FXML
    private Button btnAlterarDados;

    @FXML
    private Button btnExcluir;

    @FXML
    private Button btnSair;

    @FXML
    private TableColumn<?, ?> tbcargo;

    @FXML
    private TableColumn<?, ?> tbdtnascimento;

    @FXML
    private TableColumn<?, ?> tbemail;

    @FXML
    private TableColumn<?, ?> tbnome;

    @FXML
    private TableColumn<?, ?> tbtelefone;
    
    public void setStage(Stage stage) {
        this.stage = stage;
    }
    

    @FXML
    void OnClickSair(ActionEvent event) {

        try {
            URL url = new File("src/main/java/view/TelaTelaInicial.fxml").toURI().toURL();
            FXMLLoader loader = new FXMLLoader(url);
            Parent root = loader.load();
            Stage telaInicial = new Stage();
            TelaInicialController ti = loader.getController(); 
            ti.setStage(telaInicial);
            Scene scene = new Scene(root);
            telaInicial.setScene(scene);
            telaInicial.setTitle("BlueHorizon - Sistema de gerenciamento de propriedades beira-mar");

            telaInicial.show();
            } catch (IOException e) {
            e.printStackTrace();
            }
    
    }
    
    

    @FXML
    void OnclickAlterarDados(ActionEvent event) {
        
        

    }

    @FXML
    void OnclickExcluir(ActionEvent event) {

    }

}



