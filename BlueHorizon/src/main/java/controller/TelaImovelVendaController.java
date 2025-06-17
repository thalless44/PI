package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import model.Propriedades;
import model.PropriedadesDAO;
import model.Proprietario;
import util.AlertaUtil;

public class TelaImovelVendaController {

    private Stage stage;
    private Propriedades propriedadeSelecionada;

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
    
    private Proprietario prop;

    @FXML
    public void carregarDadosImovel(Propriedades propriedade) {
        this.propriedadeSelecionada = propriedade;

        lblImovelVenda.setText(propriedade.getTipoPropriedade());
        lblRua.setText(propriedade.getRua());
        lblCidade.setText(propriedade.getCidade() != null ? propriedade.getCidade() : "Não informado");
        lblValor.setText("R$ " + String.format("%.2f", propriedade.getPreco()));
        lblDisponibilidade.setText(propriedade.isDisponibilidade() ? "Disponível" : "Vendido");
        lblDataCadastro.setText(propriedade.getDataCadastro() != null ? propriedade.getDataCadastro().toString() : "N/D");
        lblQuartos.setText(String.valueOf(propriedade.getQuartos()));
        lblBanheiros.setText(String.valueOf(propriedade.getBanheiros()));
        lblVagasGaragem.setText(String.valueOf(propriedade.getVagasGaragem()));
        lblMobiliada.setText(propriedade.isMobilia() ? "Sim" : "Não");
        lblJardim.setText(propriedade.isJardim() ? "Sim" : "Não");
        lblSistemaSegurança.setText(propriedade.isSistemaSeguranca() ? "Sim" : "Não");
        lblPiscina.setText(propriedade.isPiscina() ? "Sim" : "Não");
        lblNumeracaoImovel.setText(String.valueOf(propriedade.getNumeroCasa()));
        lblArea.setText(propriedade.getArea());

        //Falta adicionar esse método de Proprietário, o mesmo motivo de erro na TelaAlterarDadosImovelController. Proprietario prop = 
        
        if (prop != null) {
            lblNome.setText(prop.getNome());
            lblTelefone.setText(prop.getTelefone());
            lblEmail.setText(prop.getEmail());
        } else {
            lblNome.setText("Não informado");
            lblTelefone.setText("Não informado");
            lblEmail.setText("Não informado");
        }
    }

    @FXML
    public void ActionEditarImovel(ActionEvent event) {
        if (propriedadeSelecionada != null) {
            AlertaUtil.mostrarInformacao("Editar Imóvel",
                    "O imóvel nº " + propriedadeSelecionada.getNumeroCasa() + " será enviado para edição.");
            // TODO: abrir tela de edição
        } else {
            AlertaUtil.mostrarAviso("Imóvel não selecionado", "Nenhum imóvel foi selecionado.");
        }
    }

    @FXML
    public void ActionEfetuarVenda(ActionEvent event) {
        if (propriedadeSelecionada != null && propriedadeSelecionada.isDisponibilidade()) {
            propriedadeSelecionada.setDisponibilidade(false);
            boolean atualizado = PropriedadesDAO.atualizarPropriedade(propriedadeSelecionada);

            if (atualizado) {
                lblDisponibilidade.setText("Vendido");
                AlertaUtil.mostrarInformacao("Venda Efetuada", "O imóvel foi vendido com sucesso!");
            } else {
                AlertaUtil.mostrarErro("Erro", "Falha ao atualizar", "Erro ao marcar o imóvel como vendido.");
            }
        } else {
            AlertaUtil.mostrarAviso("Venda não permitida", "Este imóvel já está marcado como vendido.");
        }
    }

    @FXML
    public void ActionSair(ActionEvent event) {
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

