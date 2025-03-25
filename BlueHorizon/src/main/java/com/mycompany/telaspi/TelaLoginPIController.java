package com.mycompany.telaspi;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class TelaLoginPIController {

    @FXML
    private TextField txtfdEmail;

    @FXML
    private PasswordField txtfdSenha;

    @FXML
    private Button btnEfetuarLogin;

    @FXML
    private Hyperlink hlRecuperarSenha;

    @FXML
    private void ActionEfetuarLogin(ActionEvent event) {
        
        String email = txtfdEmail.getText();
        String senha = txtfdSenha.getText();
        
        if(email.isEmpty()){
            
        Alert loginPreencher = new Alert(Alert.AlertType.ERROR);
        loginPreencher.setTitle("BlueHorizon - Login");
        loginPreencher.setHeaderText("Erro ao efetuar o login!");
        loginPreencher.setContentText("Campo de email vazio. Preencha");
        loginPreencher.showAndWait();
        
        }else if(senha.isEmpty()){
            
        Alert loginPreencher = new Alert(Alert.AlertType.ERROR);
        loginPreencher.setTitle("BlueHorizon - Login");
        loginPreencher.setHeaderText("Erro ao efetuar o login!");
        loginPreencher.setContentText("Campo de senha vazio. Preencha");
        loginPreencher.showAndWait();
        
        }else if(email.equals("usuario@gmail.com") && senha.equals("12345")){
            
        try {
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/TelaInicial.fxml"));
        Parent root = loader.load();
        
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("BlueHorizon - Sistema de gerenciamento de propriedades beira-mar");
        
        stage.setMaximized(true);
        stage.show();
        
    } catch (IOException e) {
        e.printStackTrace();
    }
            
        } else {
            
        Alert loginErro = new Alert(Alert.AlertType.ERROR);
        loginErro.setTitle("BlueHorizon - Login");
        loginErro.setHeaderText("Erro ao efetuar o login!");
        loginErro.setContentText("Senha ou email incorretos");
        loginErro.showAndWait();
        
        }       
    }

    @FXML
    private void ActionHyperlinkRecSenha(ActionEvent event) throws IOException {
        
    try {
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/TelaRecSenha.fxml"));
        Parent root = loader.load();
        
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Recuperação de Senha");
        stage.setMaximized(true);
        stage.show();
        
    } catch (IOException e) {
        e.printStackTrace();
    }
    
    }
}
