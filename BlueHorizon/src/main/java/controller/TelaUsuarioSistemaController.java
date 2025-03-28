package controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
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

        if(FecharTelaUsuariosSistema()){              
            //faz com que feche apenas a tela de rec senha, ao inves da aplicação toda
            Stage stage = (Stage) btnSair.getScene().getWindow();
            stage.close();          
        }else{
            event.consume();
        }
    
    }
    
    

    @FXML
    void OnclickAlterarDados(ActionEvent event) {
        
        

    }

    @FXML
    void OnclickExcluir(ActionEvent event) {

    }

    private boolean FecharTelaUsuariosSistema() {
        
        Alert confirmar = new Alert(Alert.AlertType.WARNING);
        confirmar.setTitle("Confirmação");
        confirmar.setHeaderText("Tem certeza que deseja fechar a tela atual?");
        confirmar.setContentText("Todas as alterações não salvas serão perdidas!");
        return confirmar.showAndWait().filter(response -> response == ButtonType.OK).isPresent();
    }

}



