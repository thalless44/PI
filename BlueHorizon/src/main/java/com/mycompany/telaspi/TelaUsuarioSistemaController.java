package com.mycompany.telaspi;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.stage.Stage;

public class TelaUsuarioSistemaController {
    
    
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

    @FXML
    void OnClickSair(ActionEvent event) {

                try {
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/TelaInicial.fxml"));
        Parent root = loader.load();
        
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Tela Home");
        stage.show();
        
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



