package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import util.AlertaUtil;

public class TelaImovelVendaController {
    
    private Stage stage;

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
        
        /*if (!lblNumeracaoImovel.getText().isEmpty()) {
            AlertaUtil.mostrarInformacao(
                "Editar Imóvel",
                "O imóvel nº " + lblNumeracaoImovel.getText() + " será enviado para edição."
            );
        } else {
            AlertaUtil.mostrarAviso(
                "Imóvel não selecionado",
                "Não é possível editar. Nenhum imóvel foi selecionado."
            );
        }*/
    }

    @FXML
    void ActionEfetuarVenda(ActionEvent event) {
        
       /*if (!lblDisponibilidade.getText().equalsIgnoreCase("Vendido")) {
            AlertaUtil.mostrarInformacao(
                "Venda Efetuada",
                "O imóvel foi vendido com sucesso!"
            );
        } else {
            AlertaUtil.mostrarAviso(
                "Venda não permitida",
                "Este imóvel já está marcado como vendido."
            );
        }*/
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
        return AlertaUtil.mostrarConfirmacao(
            "Confirmação",
            "Tem certeza que deseja sair da tela do imóvel?",
            "Todas as alterações não salvas serão perdidas!"
        ).filter(response -> response == ButtonType.OK).isPresent();
    }
    public void setStage(Stage stage) {
        this.stage = stage;
    }
}