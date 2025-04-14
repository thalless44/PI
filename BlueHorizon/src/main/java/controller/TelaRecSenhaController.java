package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import util.AlertaUtil;

public class TelaRecSenhaController {
    private Stage stage;

    @FXML
    private Button btnRecuperarSenha;

    @FXML
    private Button btnSair;

    @FXML
    private TextField txtfdEmailRecSenha;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    void ActionRecuperarSenha(ActionEvent event) {
        String email = txtfdEmailRecSenha.getText();

        if (email.equals("usuario@gmail.com")) {
            AlertaUtil.mostrarInformacao(
                "BlueHorizon - Recuperação de senha",
                "Verifique o email " + email
            );
        } else if (!email.equals("usuario@gmail.com") || email.isEmpty()) {
            AlertaUtil.mostrarErro(
                "BlueHorizon - Recuperação de senha",
                "Email não cadastrado no sistema ou não preenchido."
            );
        }
    }

    @FXML
    void ActionSair(ActionEvent event) {
        if (FecharTelaRecSenha()) {
            Stage stage = (Stage) btnSair.getScene().getWindow();
            stage.close();
        } else {
            event.consume();
        }
    }

    private boolean FecharTelaRecSenha() {
        return AlertaUtil.mostrarConfirmacao(
            "Confirmação",
            "Tem certeza que deseja fechar a tela atual?\nTodas as alterações não salvas serão perdidas!"
        ).filter(response -> response == ButtonType.OK).isPresent();
    }
}

