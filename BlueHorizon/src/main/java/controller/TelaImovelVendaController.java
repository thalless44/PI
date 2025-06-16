package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import model.Propriedades;
import util.AlertaUtil;

public class TelaImovelVendaController {

    private Stage stage;
    private Propriedades imovel; // [MODIFICAÇÃO 1] - Atributo para armazenar o imóvel

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
        
    }

    @FXML
    void ActionEfetuarVenda(ActionEvent event) {
        /* 
        if (!lblDisponibilidade.getText().equalsIgnoreCase("Vendido")) {
            AlertaUtil.mostrarInformacao("Venda Efetuada", "O imóvel foi vendido com sucesso!");
        } else {
            AlertaUtil.mostrarAviso("Venda não permitida", "Este imóvel já está marcado como vendido.");
        }
        */
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

    // [MODIFICAÇÃO 2] - Método para preencher os campos com os dados do imóvel
    public void preencherCampos(Propriedades imovel) {
        this.imovel = imovel;
        lblNumeracaoImovel.setText(String.valueOf(imovel.getNumeroCasa()));
        lblImovelVenda.setText(imovel.getTipoPropriedade());
        lblRua.setText(imovel.getRua());
        lblCidade.setText(imovel.getEndereco());
        lblQuartos.setText(String.valueOf(imovel.getQuartos()));
        lblBanheiros.setText(String.valueOf(imovel.getBanheiros()));
        lblVagasGaragem.setText(String.valueOf(imovel.getVagasGaragem()));
        lblValor.setText(String.format("R$ %.2f", imovel.getPreco()));
        lblArea.setText(imovel.getArea());
        lblDataCadastro.setText(imovel.getDataCadastro().toString());
        
        
        lblDisponibilidade.setText(imovel.isDisponibilidade() ? "Disponível" : "Vendido");

    // Convertendo booleanos para texto legível
        lblJardim.setText(imovel.isJardim() ? "Sim" : "Não");
        lblMobiliada.setText(imovel.isMobilia() ? "Sim" : "Não");
        lblPiscina.setText(imovel.isPiscina() ? "Sim" : "Não");
        lblSistemaSegurança.setText(imovel.isSistemaSeguranca() ? "Sim" : "Não");

    // Como os campos de proprietário não existem na classe, deixe-os vazios (ou implemente depois)
        lblNome.setText("Não informado");
        lblEmail.setText("Não informado");
        lblTelefone.setText("Não informado");
        
    
    }
}
