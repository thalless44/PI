package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class TelaImovelVendaController {

    @FXML
    private Button btnEditarImovel;

    @FXML
    private Button btnEfetuarVenda;

    @FXML
    private Button btnSair;

    @FXML
    private Label lblArea;

    @FXML
    private Label lblBanheiros;

    @FXML
    private Label lblCidade;

    @FXML
    private Label lblDataCadastro;

    @FXML
    private Label lblDisponibilidade;

    @FXML
    private Label lblEmail;

    @FXML
    private Label lblImovelVenda;

    @FXML
    private Label lblJardim;

    @FXML
    private Label lblMobiliada;

    @FXML
    private Label lblNome;

    @FXML
    private Label lblNumeracaoImovel;

    @FXML
    private Label lblPiscina;

    @FXML
    private Label lblQuartos;

    @FXML
    private Label lblRua;

    @FXML
    private Label lblSistemaSegurança;

    @FXML
    private Label lblTelefone;

    @FXML
    private Label lblVagasGaragem;

    @FXML
    private Label lblValor;

    @FXML
    void ActionEditarImovel(ActionEvent event) {
        if (!lblNumeracaoImovel.getText().isEmpty()) {
            Alert editar = new Alert(Alert.AlertType.INFORMATION);
            editar.setTitle("Editar Imóvel");
            editar.setHeaderText("Ação de Edição");
            editar.setContentText("O imóvel nº " + lblNumeracaoImovel.getText() + " será enviado para edição.");
            editar.showAndWait();
        } else {
            Alert aviso = new Alert(Alert.AlertType.WARNING);
            aviso.setTitle("Imóvel não selecionado");
            aviso.setHeaderText("Nenhum imóvel carregado");
            aviso.setContentText("Não é possível editar. Nenhum imóvel foi selecionado.");
            aviso.showAndWait();
        }
    }

    @FXML
    void ActionEfetuarVenda(ActionEvent event) {
        if (!lblDisponibilidade.getText().equalsIgnoreCase("Vendido")) {
            Alert venda = new Alert(Alert.AlertType.CONFIRMATION);
            venda.setTitle("Venda Efetuada");
            venda.setHeaderText("Confirmação de Venda");
            venda.setContentText("O imóvel foi vendido com sucesso!");
            venda.showAndWait();
        } else {
            Alert aviso = new Alert(Alert.AlertType.WARNING);
            aviso.setTitle("Venda não permitida");
            aviso.setHeaderText("Imóvel já vendido");
            aviso.setContentText("Este imóvel já está marcado como vendido.");
            aviso.showAndWait();
        }
    }

    @FXML
    void ActionSair(ActionEvent event) {
        if (FecharTelaImovelVenda()) {
            Stage stage = (Stage) btnSair.getScene().getWindow();
            stage.close();
        } else {
            event.consume();
        }
    }

    private boolean FecharTelaImovelVenda() {
        return true;
    }
}