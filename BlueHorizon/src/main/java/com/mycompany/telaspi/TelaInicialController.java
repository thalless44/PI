package com.mycompany.telaspi;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;

public class TelaInicialController {
    
    @FXML
    private MenuItem menuAcessarPerfil;

    @FXML
    private MenuItem menuCadastroCliente;

    @FXML
    private MenuItem menuCadastroFuncionario;

    @FXML
    private MenuItem menuCadastroImovol;

    @FXML
    void onClickCliente(ActionEvent event) {
        try{
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/TelaCadastroCliente.fxml"));
            Parent root = loader.load();
            
            Stage stage = new Stage();
            
            stage.setScene(new Scene (root));
            stage.setTitle("Personalização de perfil");
            stage.show();
            
        }catch(IOException e){
            e.printStackTrace();
        }

    }

    @FXML
    void onClickFuncionario(ActionEvent event) {

    }

    @FXML
    void onClickImovel(ActionEvent event) {

    }

    @FXML
    void onClickPerfil(ActionEvent event) {
        
        try{
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/TelaInformacoesPerfil.fxml"));
            Parent root = loader.load();
            
            Stage stage = new Stage();
            
            stage.setScene(new Scene (root));
            stage.setTitle("Personalização de perfil");
            stage.show();
            
        }catch(IOException e){
            e.printStackTrace();
        }

    }
    
    
    
}
