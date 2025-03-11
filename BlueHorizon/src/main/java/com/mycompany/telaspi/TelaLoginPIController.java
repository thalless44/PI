package com.mycompany.telaspi;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Button;

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
            
        Alert loginSucesso = new Alert(Alert.AlertType.INFORMATION);
        loginSucesso.setTitle("BlueHorizon - Login");
        loginSucesso.setHeaderText("Login efetuado com sucesso!");
        loginSucesso.setContentText("Seja bem-vindo(a), " + email + ".");
        loginSucesso.showAndWait();
            
        } else {
            
        Alert loginErro = new Alert(Alert.AlertType.ERROR);
        loginErro.setTitle("BlueHorizon - Login");
        loginErro.setHeaderText("Erro ao efetuar o login!");
        loginErro.setContentText("Senha ou email incorretos");
        loginErro.showAndWait();
        
        }
        
    }

    @FXML
    private void ActionHyperlinkRecSenha(ActionEvent event) {
        
        //Arrumar
        System.out.println("Link de recuperação de senha clicado!");
     
    }
}
