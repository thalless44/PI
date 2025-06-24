package controller;

import java.io.ByteArrayInputStream;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
    private ImageView Image;

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
    public void carregarDadosImovel(Propriedades imovel, Proprietario proprietario) {
        this.propriedadeSelecionada = imovel;

        lblImovelVenda.setText(imovel.getTipoPropriedade());
        lblRua.setText(imovel.getRua());
        lblCidade.setText(imovel.getCidade() != null ? imovel.getCidade() : "Não informado");
        lblValor.setText("R$ " + String.format("%.2f", imovel.getPreco()));
        lblDisponibilidade.setText(imovel.isDisponibilidade() ? "Disponível" : "Vendido");
        lblDataCadastro.setText(imovel.getDataCadastro() != null ? imovel.getDataCadastro().toString() : "N/D");
        lblQuartos.setText(String.valueOf(imovel.getQuartos()));
        lblBanheiros.setText(String.valueOf(imovel.getBanheiros()));
        lblVagasGaragem.setText(String.valueOf(imovel.getVagasGaragem()));
        lblMobiliada.setText(imovel.isMobilia() ? "Sim" : "Não");
        lblJardim.setText(imovel.isJardim() ? "Sim" : "Não");
        lblSistemaSegurança.setText(imovel.isSistemaSeguranca() ? "Sim" : "Não");
        lblPiscina.setText(imovel.isPiscina() ? "Sim" : "Não");
        lblNumeracaoImovel.setText(String.valueOf(imovel.getNumeroCasa()));
        lblArea.setText(imovel.getArea());
        
        if (imovel.getImagem() != null) {
            ByteArrayInputStream bis = new ByteArrayInputStream(imovel.getImagem());
            Image.setImage(new Image(bis));
        }

         
        Proprietario prop = imovel.getProprietario();
        
        
            lblNome.setText("22 " + proprietario.getNome());
            lblTelefone.setText("12  " +proprietario.getTelefone());
            lblEmail.setText("22 " + proprietario.getEmail());
        

        
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

