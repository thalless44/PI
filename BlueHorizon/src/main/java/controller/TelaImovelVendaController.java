package controller;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
    private Proprietario prop;

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

    @FXML
    public void carregarDadosImovel(Propriedades imovel, Proprietario proprietario) {
        this.propriedadeSelecionada = imovel;
        this.prop = imovel.getProprietario();

        lblImovelVenda.setText(imovel.getTipoPropriedade());
        lblRua.setText(imovel.getRua());
        lblCidade.setText(imovel.getCidade() != null ? imovel.getCidade() : "Não informado");
        lblValor.setText(" R$ " + String.format("%.2f", imovel.getPreco()));
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

        if (this.prop != null) {
            lblNome.setText(prop.getNome());
            lblTelefone.setText(prop.getTelefone());
            lblEmail.setText(prop.getEmail());
        } else {
            lblNome.setText("N/D");
            lblTelefone.setText("N/D");
            lblEmail.setText("N/D");
        }
    }

    @FXML
    public void ActionEditarImovel(ActionEvent event) {
        
    }

    @FXML
    public void ActionEfetuarVenda(ActionEvent event) {
        try {
            URL url = new File("src/main/java/view/TelaPagamento.fxml").toURI().toURL();
            FXMLLoader loader = new FXMLLoader(url);
            Parent root = loader.load();

            // Pega o controller da tela de pagamento
            TelaPagamentoController tp = loader.getController();

            // Passa o imóvel selecionado para a tela de pagamento
            tp.setPropriedade(propriedadeSelecionada);

            Stage telaPG = new Stage();
            tp.setStage(telaPG);

            Scene scene = new Scene(root);

            Image icone = new Image(getClass().getResourceAsStream("/icons/Bh.png"));
            telaPG.getIcons().add(icone);

            telaPG.setScene(scene);
            telaPG.setTitle("BlueHorizon - Pagamento");
            telaPG.setMaximized(false);
            telaPG.show();

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Erro ao abrir a tela de pagamento.");
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
