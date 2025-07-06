package controller;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

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
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Propriedades;
import model.Proprietario;
import util.AlertaUtil;

public class TelaImovelVendaController {

    private Stage stage;
    private Propriedades propriedadeSelecionada;
    private Proprietario prop;

    @FXML private Button btnEditarImovel;
    @FXML private Button btnEfetuarVenda;
    @FXML private Button btnSair;

    @FXML private Label lblArea;
    @FXML private Label lblBanheiros;
    @FXML private Label lblCidade;
    @FXML private Label lblDataCadastro;
    @FXML private Label lblDisponibilidade;
    @FXML private Label lblEmail;
    @FXML private Label lblImovelVenda;
    @FXML private Label lblJardim;
    @FXML private Label lblMobiliada;
    @FXML private Label lblNome;
    @FXML private Label lblNumeracaoImovel;
    @FXML private Label lblPiscina;
    @FXML private Label lblQuartos;
    @FXML private Label lblRua;
    @FXML private Label lblSistemaSegurança;
    @FXML private Label lblTelefone;
    @FXML private Label lblVagasGaragem;
    @FXML private Label lblValor;

    @FXML private ImageView Image;
    
    private TelaInicialController telaInicialController;

    public void setTelaInicialController(TelaInicialController controller) {
        this.telaInicialController = controller;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

     @FXML
    public void carregarDadosImovel(Propriedades imovel, Proprietario proprietario) {
        this.propriedadeSelecionada = imovel;
        this.prop = imovel.getProprietario();

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));

        
        lblRua.setText(imovel.getRua());
        lblCidade.setText(imovel.getCidade() != null ? imovel.getCidade() : "Não informado");
        lblDisponibilidade.setText(imovel.isDisponibilidade() ? " Disponível" : " Vendido");
        lblDataCadastro.setText(dateFormat.format(imovel.getDataCadastro()));
        lblQuartos.setText(String.valueOf(imovel.getQuartos()));
        lblBanheiros.setText(String.valueOf(imovel.getBanheiros()));
        lblVagasGaragem.setText(String.valueOf(imovel.getVagasGaragem()));
        lblMobiliada.setText(imovel.isMobilia() ? "Sim" : "Não");
        lblJardim.setText(imovel.isJardim() ? "Sim" : "Não");
        lblSistemaSegurança.setText(imovel.isSistemaSeguranca() ? "Sim" : "Não");
        lblPiscina.setText(imovel.isPiscina() ? "Sim" : "Não");
        lblNumeracaoImovel.setText(String.valueOf(imovel.getNumeroCasa()));
        lblArea.setText(imovel.getArea());
        lblValor.setText(currencyFormat.format(imovel.getPreco()));

        if (imovel.getImagem() != null) {
            ByteArrayInputStream bis = new ByteArrayInputStream(imovel.getImagem());
            Image imagem = new Image(bis);
            Image.setImage(imagem);

            // Ajuste pra forçar o ImageView a ser quadrado e a imagem preencher:
            Image.setFitWidth(367);
            Image.setFitHeight(203);
            Image.setPreserveRatio(false); // NÃO mantém proporção, pra preencher o quadrado e cortar o que não cabe
            Image.setSmooth(true);
            Image.setCache(true);
        }

        lblNome.setText(" " + prop.getNome());
        lblTelefone.setText(prop.getTelefone());
        lblEmail.setText(prop.getEmail());
        
        this.prop = propriedadeSelecionada.getProprietario();
    }

    @FXML
    public void ActionEditarImovel(ActionEvent event) {
         try {
        URL url = new File("src/main/java/view/TelaAlterarDadosImovel.fxml").toURI().toURL();
        FXMLLoader loader = new FXMLLoader(url);
        Parent root = loader.load();

        TelaAlterarDadosImovelController tEI = loader.getController();

        // Passa a propriedade selecionada e a stage
        tEI.carregarDadosImovel(propriedadeSelecionada);

        Stage telaEdImovel = new Stage();
        tEI.setStage(telaEdImovel);

        Scene scene = new Scene(root);
        Image icone = new Image(getClass().getResourceAsStream("/icons/Bh.png"));
        telaEdImovel.getIcons().add(icone);

        telaEdImovel.setScene(scene);
        telaEdImovel.setTitle("BlueHorizon - Alterar dados do imóvel");
        telaEdImovel.setMaximized(false);
        telaEdImovel.show();

        // Quando fechar a tela de edição, recarrega os dados
        telaEdImovel.setOnHiding(e -> {
            if (telaInicialController != null) {
                telaInicialController.carregarPropriedades(); // chama da tela inicial, que realmente tem esse método
            }
        });

    } catch (IOException e) {
        e.printStackTrace();
    }
}

    @FXML
    public void ActionEfetuarVenda(ActionEvent event) {
        try {
            URL url = new File("src/main/java/view/TelaPagamento.fxml").toURI().toURL();
            FXMLLoader loader = new FXMLLoader(url);
            Parent root = loader.load();

            TelaPagamentoController tp = loader.getController();
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
}
