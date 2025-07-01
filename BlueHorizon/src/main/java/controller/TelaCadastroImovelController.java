package controller;

import java.io.File;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Side;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import static model.CidadeDAO.buscarCidades;
import static model.CidadeDAO.buscarIdCidadePorNome;
import static model.ImagemDAO.salvarImagemNoBanco;
import model.PropriedadesDAO;
import model.Proprietario;
import model.ProprietarioDAO;
import util.AlertaUtil;
import util.LimitarCaracter;

public class TelaCadastroImovelController {

    private Stage stage;
    
    @FXML
    private ImageView imageViewImovel;


     @FXML
    private Button btnAdicionarImagem;

    @FXML
    private Button btnCadastro;

    @FXML
    private Button btnCancelarCadastro;

    @FXML
    private ComboBox<Proprietario> cmbxProprietario;

    @FXML
    private RadioButton rbDisponivel;

    @FXML
    private RadioButton rbNaoDisponivel;

    @FXML
    private RadioButton rbNaoJardim;

    @FXML
    private RadioButton rbNaoMobiliada;

    @FXML
    private RadioButton rbNaoPiscina;

    @FXML
    private RadioButton rbNaoSG;

    @FXML
    private RadioButton rbSimJardim;

    @FXML
    private RadioButton rbSimMobiliada;

    @FXML
    private RadioButton rbSimPiscina;

    @FXML
    private RadioButton rbSimSG;

    @FXML
    private TextField txtArea;

    @FXML
    private TextField txtBanheiros;

    @FXML
    private TextField txtCidades;

    @FXML
    private TextField txtDatacadastro;

    @FXML
    private TextField txtNumeracaoImovel;

    @FXML
    private TextField txtQuartos;

    @FXML
    private TextField txtRua;
    
    @FXML
    private ContextMenu cTextMenuCidade;
    
    private File arquivoSelecionado;

    @FXML
    private TextField txtTipoPropriedade;

    @FXML
    private TextField txtVagaGaragem;

    @FXML
    private TextField txtValor;

    private ToggleGroup grupoJardim;
    private ToggleGroup grupoMobiliada;
    private ToggleGroup grupoPiscina;
    private ToggleGroup grupoSG;
    private ToggleGroup grupoDisponibilidade;
    
    @FXML
    void initialize() {
        grupoJardim = new ToggleGroup();
        grupoPiscina = new ToggleGroup();
        grupoMobiliada = new ToggleGroup();
        grupoSG = new ToggleGroup();
        grupoDisponibilidade = new ToggleGroup();

        rbSimJardim.setToggleGroup(grupoJardim);
        rbNaoJardim.setToggleGroup(grupoJardim);
        rbSimPiscina.setToggleGroup(grupoPiscina);
        rbNaoPiscina.setToggleGroup(grupoPiscina);
        rbSimMobiliada.setToggleGroup(grupoMobiliada);
        rbNaoMobiliada.setToggleGroup(grupoMobiliada);
        rbSimSG.setToggleGroup(grupoSG);
        rbNaoSG.setToggleGroup(grupoSG);
        rbDisponivel.setToggleGroup(grupoDisponibilidade);
        rbNaoDisponivel.setToggleGroup(grupoDisponibilidade);

        new LimitarCaracter(10, LimitarCaracter.TipoEntrada.DATA).applyToTextInputControl(txtDatacadastro);
        new LimitarCaracter(20, LimitarCaracter.TipoEntrada.VALOR).applyToTextInputControl(txtValor);
        carregarProprietarios();

        txtCidades.textProperty().addListener((obs, oldText, newText) -> {
            if (newText.length() >= 2) {
                List<String> sugestoes = buscarCidades(newText);
                mostrarSugestoesCidade(sugestoes);
            } else {
                cTextMenuCidade.hide();
            }
        });
    }

    private void carregarProprietarios() {
        List<Proprietario> proprietarios = ProprietarioDAO.listarTodosProprietarios();
        cmbxProprietario.getItems().clear();
        cmbxProprietario.getItems().addAll(proprietarios);
        
    }
    

    @FXML
    void OnClickAdicionarImagem(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Selecionar Imagem");
        fileChooser.getExtensionFilters().add(
            new FileChooser.ExtensionFilter("Imagens", "*.png", "*.jpg", "*.jpeg", "*.gif")
        );

        arquivoSelecionado = fileChooser.showOpenDialog(null);
        if (arquivoSelecionado != null) {
            Image image = new Image(arquivoSelecionado.toURI().toString(), false);
            imageViewImovel.setImage(image);
        }
    }

    @FXML
    void onClickCadastro(ActionEvent event) {
        try {
            Proprietario proprietario = cmbxProprietario.getValue();
            
            if (proprietario == null) {
                AlertaUtil.mostrarErro("Erro", "Proprietário do imóvel não selecionado", "Selecione um proprietário!");
                return;
            }else {
                System.out.println("Selecionado: " + proprietario.getNome() + " | ID: " + proprietario.getId());
            }

            int idImagem = -1;
            if (arquivoSelecionado != null) {
                idImagem = salvarImagemNoBanco(arquivoSelecionado);
                if (idImagem == -1) {
                    AlertaUtil.mostrarErro("Erro", "Imagem", "Erro ao salvar imagem no banco de dados.");
                    return;
                }
            }

            String tipoPropriedade = txtTipoPropriedade.getText();
            String nomeCidade = txtCidades.getText();
            Integer idCidade = buscarIdCidadePorNome(nomeCidade);
            System.out.println("id " + idCidade);

            if (idCidade == null) {
                AlertaUtil.mostrarErro("Erro", "Cidade não encontrada", "A cidade digitada não existe no banco de dados.");
                return;
            }
            
            String valorFormatado = txtValor.getText().replaceAll("[^\\d]","");
            double preco = Double.parseDouble(valorFormatado)/100.0;
            
            boolean disponibilidade = rbDisponivel.isSelected();

            DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate dataCadastroLD = LocalDate.parse(txtDatacadastro.getText(), formato);
            Date dataCadastro = Date.valueOf(dataCadastroLD);

            String rua = txtRua.getText();
            String endereco = txtRua.getText();
            int quartos = Integer.parseInt(txtQuartos.getText());
            int banheiros = Integer.parseInt(txtBanheiros.getText());
            int vagasGaragem = Integer.parseInt(txtVagaGaragem.getText());
            boolean mobiliada = rbSimMobiliada.isSelected();
            boolean jardim = rbSimJardim.isSelected();
            boolean sistemaSeguranca = rbSimSG.isSelected();
            boolean piscina = rbSimPiscina.isSelected();
            int numeroCasa = Integer.parseInt(txtNumeracaoImovel.getText());
            String area = txtArea.getText();
            int idProprietario = proprietario.getId();
            

            boolean sucessoPropriedade = PropriedadesDAO.Propriedades(
                tipoPropriedade, endereco, preco, disponibilidade, dataCadastro, rua,
                quartos, banheiros, vagasGaragem, mobiliada, jardim, sistemaSeguranca,
                piscina, numeroCasa, area, idProprietario, idImagem, idCidade
            );

            if (sucessoPropriedade) {
                AlertaUtil.mostrarInformacao("Sucesso", "Cadastro de imóvel", "O imóvel foi cadastrado com sucesso!");
                stage.close();
            } else {
                AlertaUtil.mostrarErro("Erro", "Erro no Cadastro", "Houve um erro ao tentar cadastrar o imóvel. Verifique os dados e tente novamente.");
            }

        } catch (Exception e) {
            e.printStackTrace();
            AlertaUtil.mostrarErro("Erro", "Ocorreu um erro inesperado.", "Verifique os dados e tente novamente.");
        }
    }

    

    @FXML
    void onClickCancelarCadastro(ActionEvent event) {
        if (CancelarCadastroImovel()) {
            Stage stage = (Stage) btnCancelarCadastro.getScene().getWindow();
            stage.close();
        } else {
            event.consume();
        }
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    private boolean CancelarCadastroImovel() {
        return AlertaUtil.mostrarConfirmacao(
            "Confirmação",
            "Tem certeza que deseja fechar a tela de cadastro de imóvel?",
            "Todas as alterações não salvas serão perdidas!"
        ).filter(response -> response == ButtonType.OK).isPresent();
    }

   

    public void mostrarSugestoesCidade(List<String> sugestoes) {
        cTextMenuCidade.getItems().clear();

        for (String sugestao : sugestoes) {
            MenuItem item = new MenuItem(sugestao);
            item.setOnAction(e -> {
                txtCidades.setText(sugestao);
                cTextMenuCidade.hide();
            });
            cTextMenuCidade.getItems().add(item);
        }

        if (!sugestoes.isEmpty()) {
            cTextMenuCidade.show(txtCidades, Side.BOTTOM, 0, 0);
        } else {
            cTextMenuCidade.hide();
        }
    }
}