package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class TelaRecSenhaController {

    @FXML
    private Button btnRecuperarSenha;

    @FXML
    private Button btnSair;

    @FXML
    private TextField txtfdEmailRecSenha;

    @FXML
    void ActionRecuperarSenha(ActionEvent event) {
        
        //provisório, apenas para teste
        
        String email = txtfdEmailRecSenha.getText();
        
        if(email.equals("usuario@gmail.com")){
            
        Alert EmailCompativelRecSenha = new Alert(Alert.AlertType.INFORMATION);
        EmailCompativelRecSenha.setTitle("BlueHorizon - Recuperação de senha");
        EmailCompativelRecSenha.setHeaderText("Verifique seu email");
        EmailCompativelRecSenha.setContentText("Verifique o email " + email);
        EmailCompativelRecSenha.showAndWait();
                       
        }else if(!email.equals("usuario@gmail.com") || email.isEmpty()){
            
        Alert EmailCompativelRecSenha = new Alert(Alert.AlertType.ERROR);
        EmailCompativelRecSenha.setTitle("BlueHorizon - Recuperação de senha");
        EmailCompativelRecSenha.setHeaderText("ERRO!");
        EmailCompativelRecSenha.setContentText("Email não cadastrado no sistema ou não preenchido.");
        EmailCompativelRecSenha.showAndWait();
        
        }
    }

    @FXML
    void ActionSair(ActionEvent event) {
        
        if(FecharTelaRecSenha()){              
            //faz com que feche apenas a tela de rec senha, ao inves da aplicação toda
            Stage stage = (Stage) btnSair.getScene().getWindow();
            stage.close();          
        }else{
            event.consume();
        }
    }

    private boolean FecharTelaRecSenha() {
        
        Alert confirmar = new Alert(Alert.AlertType.WARNING);
        confirmar.setTitle("Confirmação");
        confirmar.setHeaderText("Tem certeza que deseja fechar a tela atual?");
        confirmar.setContentText("Todas as alterações não salvas serão perdidas!");
        return confirmar.showAndWait().filter(response -> response == ButtonType.OK).isPresent();
        
    }

}
