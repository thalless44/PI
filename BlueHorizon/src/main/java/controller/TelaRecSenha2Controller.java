package controller;

import java.sql.SQLException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;
import model.FuncionarioDAO;
import util.AlertaUtil;

public class TelaRecSenha2Controller {

    @FXML
    private Button btnRecuperarSenha;

    @FXML
    private Button btnSair;

     @FXML
    private PasswordField passwordFieldConfirmarNovaSenha;

    @FXML
    private PasswordField passwordFieldNovaSenha;

    
    private Stage stageRS2;
    
    private String email;

    @FXML
    void ActionRecuperarSenha(ActionEvent event) { //Alterar senha
        String novaSenha = passwordFieldNovaSenha.getText();
        String confirmarNovaSenha = passwordFieldConfirmarNovaSenha.getText();
        
        if(novaSenha.isEmpty() || confirmarNovaSenha.isEmpty()){
            AlertaUtil.mostrarErro("Erro", "Campos obrigatórios não preenchidos",
                    "Por favor, preencha todos os campos obrigatórios.");
            return;
        }
        
        if(!novaSenha.equals(confirmarNovaSenha)){        
            AlertaUtil.mostrarAlerta(Alert.AlertType.WARNING,"Aviso", "Inconsistência de dados",
                    "As senhas informadas não são idênticas.");
            return;
        }
        
        if(FuncionarioDAO.atualizarSenha(email, novaSenha)){
            AlertaUtil.mostrarInformacao("Sucesso", "Recuperação de senha", "Senha alterada com sucesso!");   
            
            //Fazer um metodo para fechar a tela, se possível!
            
        }else{
            AlertaUtil.mostrarErro("Erro", "Recuperação de senha", "Não foi possível alterar a senha!");
        }

    }

    @FXML
    void ActionSair(ActionEvent event) {
        if (FecharTelaRecSenha2()) {
            Stage stage = (Stage) btnSair.getScene().getWindow();
            stage.close();
        } else {
            event.consume();
        }
    }

    void setEmail(String email) {
        this.email = email;
        
    }

    void setStage(Stage telaRS2) {
        this.stageRS2 = telaRS2;
    }

    private boolean FecharTelaRecSenha2() {
         return AlertaUtil.mostrarConfirmacao(
            "Confirmação",
            "Tem certeza que deseja fechar a tela atual?",
            "Todas as alterações não salvas serão perdidas!"
        ).filter(response -> response == ButtonType.OK).isPresent();
    }

}
