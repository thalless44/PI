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
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class TelaLoginPIController {
    private Stage stageLogin;

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
        
        }else if(email.equals("usuario") && senha.equals("12345")){
            
            try {
            URL url = new File("src/main/java/view/TelaInicial.fxml").toURI().toURL();
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
            URL url = new File("src/main/java/view/TelaRecSenha.fxml").toURI().toURL();
            FXMLLoader loader = new FXMLLoader(url);
            Parent root = loader.load();
            Stage telaRecSenha = new Stage();
            TelaRecSenhaController recSenhaController = loader.getController(); 
            recSenhaController.setStage(telaRecSenha);
            Scene scene = new Scene(root);
            telaRecSenha.setScene(scene);
            telaRecSenha.setTitle("Recuperação de Senha");

            // Exibir a nova tela
            telaRecSenha.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    
    
    }public void setStage(Stage stage){
        this.stageLogin = stage;
    }
    
    public void abrirJanela (){
        System.out.println("Janela exibida");
    }
}
