package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import util.AlertaUtil;

public class TelaContratoController {

    @FXML
    private Button btnSair;

    @FXML
    private Label lblContrato;

    @FXML
    private Label lblDataCadastro;

    @FXML
    private Label lblDataContrato;

    @FXML
    private Label lblEmailCliente;

    @FXML
    private Label lblIdContrato;

    @FXML
    private Label lblIdImovel;

    @FXML
    private Label lblNomeCliente;

    @FXML
    private Label lblTelefoneCliente;

    @FXML
    private Label lblValorImovel;

    @FXML
    private Label lblValorTotal;

    @FXML
    void ActionSair(ActionEvent event) {
        if (FecharTelaContrato()) {
            Stage stage = (Stage) btnSair.getScene().getWindow();
            stage.close();
        } else {
            event.consume();
        }
    }

    private boolean FecharTelaContrato() {
        return AlertaUtil.mostrarConfirmacao(
            "Aviso", 
            "Tem certeza que deseja fechar a tela de contrato?\nTodas as alterações não salvas serão perdidas!"
        ).filter(response -> response == ButtonType.OK).isPresent();
    }
}
