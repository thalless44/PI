package controller;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import model.ImovelDAO;
import model.PropriedadesDAO;
import model.ProprietarioDAO;
import util.AlertaUtil;

public class TelaCadastroImovelController {

    private Stage stage;

    @FXML
    private Button btnAdicionarImagem;

    @FXML
    private Button btnCadastro;

    @FXML
    private Button btnCancelarCadastro;

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
    private TextField txtDisponibilidade;

    @FXML
    private TextField txtEmailProprietario;

    @FXML
    private TextField txtIDimovel;

    @FXML
    private TextField txtNumeracaoImovel;

    @FXML
    private TextField txtTipoPropriedade;

    @FXML
    private TextField txtProprietario;

    @FXML
    private TextField txtQuartos;

    @FXML
    private TextField txtRua;

    @FXML
    private TextField txtTelefoneProprietario;

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
    }

    @FXML
    void OnClickAdicionarImagem(ActionEvent event) {
        // Implementar lógica para adicionar imagem (se necessário)
    }

    @FXML
    void onClickCadastro(ActionEvent event) {
        try {
            String telefoneProprietario = txtTelefoneProprietario.getText();
            String nomeProprietario = txtProprietario.getText();
            String emailProprietario = txtEmailProprietario.getText();

            String tipoPropriedade = txtTipoPropriedade.getText();
            String cidade = txtCidades.getText();
            double preco = Double.parseDouble(txtValor.getText());
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

            boolean sucessoProprietario = ProprietarioDAO.Proprietarios(telefoneProprietario, nomeProprietario, emailProprietario);
            boolean sucessoPropriedade = PropriedadesDAO.Propriedades(tipoPropriedade, endereco, preco, disponibilidade, dataCadastro, rua);
            boolean sucessoImovel = ImovelDAO.InformacoesImovel(quartos, banheiros, vagasGaragem, mobiliada, jardim, sistemaSeguranca, piscina, numeroCasa, area);

            if (sucessoProprietario && sucessoPropriedade && sucessoImovel) {
                AlertaUtil.mostrarInformacao("Cadastro realizado", 
                    "O imóvel foi cadastrado corretamente no banco de dados.");
            } else {
                AlertaUtil.mostrarErro("Erro no Cadastro", 
                    "Houve um erro ao tentar cadastrar o imóvel. Verifique os dados e tente novamente.");
            }

        } catch (Exception e) {
            AlertaUtil.mostrarErro("Erro", "Ocorreu um erro inesperado. Verifique os dados e tente novamente.");
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
            "Tem certeza que deseja fechar a tela de cadastro de imóvel?\nTodas as alterações não salvas serão perdidas!"
        ).filter(response -> response == ButtonType.OK).isPresent();
    }
}