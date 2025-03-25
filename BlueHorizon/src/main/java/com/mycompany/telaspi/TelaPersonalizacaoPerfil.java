package com.mycompany.telaspi;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.collections.FXCollections;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
        
        Alert confirmar = new Alert(Alert.AlertType.WARNING);
        confirmar.setTitle("Alterações fei com sucesso");
        
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
    
    @FXML
    void OnClickSair(ActionEvent event) {
     
        try {
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/TelaInicial.fxml"));
        Parent root = loader.load();
        
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Tela Home");
        stage.setMaximized(true);

        stage.show();
        
    } catch (IOException e) {
        e.printStackTrace();
    }
    
    }}
   
