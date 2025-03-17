package com.mycompany.telaspi;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.collections.FXCollections;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

public class TelaPersonalizacaoPerfil {

    @FXML
    private Button BtnPP; 
    
    @FXML
    private Button BtnPP1;

    @FXML
    private ComboBox<String> Cargocb; // Definido como ComboBox<String>

    @FXML
    private TextField Cpftxt;

    @FXML
    private TextField DatadeContratacaotxt;

    @FXML
    private TextField DatadeNascimentotxt;

    @FXML
    private TextField Emailtxt;

    @FXML
    private TextField Enderecotxt;

    @FXML
    private TextField Nometxt;

    @FXML
    private TextField TelefoneTxt;

    @FXML
    void OnClickAlterarPerfil(ActionEvent event) {
        // Lógica para alterar o perfil
    }

    @FXML
    void OnClickSair(ActionEvent event) {
     
        if(FecharTelaPersonalizacaoPerfil()){
            
            Stage stage = (Stage) BtnPP1.getScene().getWindow();
            stage.close();
            
        }else{
            event.consume();
        }
    }

    // Método para inicializar o ComboBox com as opções
    @FXML
    public void initialize() {
        // Adicionando as opções "Gerente" e "Corretor" no ComboBox
        Cargocb.setItems(FXCollections.observableArrayList("Gerente", "Corretor"));
    }

    private boolean FecharTelaPersonalizacaoPerfil() {
        
        Alert confirmar = new Alert(Alert.AlertType.WARNING);
        confirmar.setTitle("Confirmação");
        confirmar.setHeaderText("Tem certeza que deseja fechar a tela atual?");
        confirmar.setContentText("Todas as alterações não salvas serão perdidas!");
        return confirmar.showAndWait().filter(response -> response == ButtonType.OK).isPresent();
        
    }
}
