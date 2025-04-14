package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.stage.Stage;
import util.AlertaUtil;

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
        if (FecharTelaUsuariosSistema()) {
            Stage stage = (Stage) btnSair.getScene().getWindow();
            stage.close();
        } else {
            event.consume();
        }
    }

    @FXML
    void OnclickAlterarDados(ActionEvent event) {
        AlertaUtil.mostrarInformacao("Alterar Dados",
                "Funcionalidade de alteração de dados ainda não implementada.");
    }

    @FXML
    void OnclickExcluir(ActionEvent event) {
        boolean confirmado = AlertaUtil.mostrarConfirmacao(
                "Confirmação de exclusão",
                "Tem certeza que deseja excluir este usuário? Esta ação não poderá ser desfeita."
        ).filter(response -> response == ButtonType.OK).isPresent();

        if (confirmado) {
            // Aqui você pode implementar a lógica de exclusão no banco de dados
            AlertaUtil.mostrarInformacao("Usuário excluído",
                    "O usuário foi removido com sucesso.");
        } else {
            event.consume();
        }
    }

    private boolean FecharTelaUsuariosSistema() {
        return AlertaUtil.mostrarConfirmacao(
                "Confirmação de saída",
                "Tem certeza que deseja fechar a tela atual?\nTodas as alterações não salvas serão perdidas!"
        ).filter(response -> response == ButtonType.OK).isPresent();
    }
}
