package controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Locale;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import model.Cliente;
import model.ClienteDAO;
import model.Contrato;
import model.ContratoDAO;
import model.Propriedades;
import model.PropriedadesDAO;
import model.TransacoesDAO;
import util.AlertaUtil;
import util.LimitarCaracter;

public class TelaPagamentoController {

    @FXML
    private Button btnCancelarpagamento;

    @FXML
    private Button btnEfetuarPagamento;

    @FXML
    private ComboBox<Cliente> cmbxClienteSelecionado;

    @FXML
    private Label labelIDImovel;

    @FXML
    private Label labelTipoPropriedade;

    @FXML
    private Label labelValor;

    @FXML
    private TextField txtFormapagamento;

    @FXML
    private TextField txtValorPagamento;

    @FXML
    private TextField txtdatapagamento;

    private Stage stage;

    private Propriedades propriedadeAtual;

    @FXML
    void initialize() {
        new LimitarCaracter(10, LimitarCaracter.TipoEntrada.DATA).applyToTextInputControl(txtdatapagamento);
        new LimitarCaracter(20, LimitarCaracter.TipoEntrada.VALOR).applyToTextInputControl(txtValorPagamento);
        carregarClientes();
    }

    private void carregarClientes() {
        List<Cliente> clientes = ClienteDAO.listarTodos();
        cmbxClienteSelecionado.getItems().clear();
        cmbxClienteSelecionado.getItems().addAll(clientes);
    }

    @FXML
    void ActionCancelarPagamento(ActionEvent event) {
        if (stage != null) {
            stage.close();
        }
    }

    @FXML
void ActionEfetuarPagamento(ActionEvent event) {
    Cliente clienteSelecionado = cmbxClienteSelecionado.getValue();
    String formaPagamento = txtFormapagamento.getText().trim();
    String valorTexto = txtValorPagamento.getText().replaceAll("[^\\d]", "");

    if (clienteSelecionado == null || formaPagamento.isEmpty() || valorTexto.isEmpty() || txtdatapagamento.getText().isEmpty()) {
        AlertaUtil.mostrarErro("Erro", "Campos obrigatórios", "Todos os campos devem ser preenchidos!");
        return;
    }

    LocalDate dataTransacaoLD;
    try {
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        dataTransacaoLD = LocalDate.parse(txtdatapagamento.getText(), formato);
    } catch (DateTimeParseException e) {
        AlertaUtil.mostrarErro("Erro", "Data inválida", "Digite a data no formato dd/MM/yyyy.");
        return;
    }

    if (propriedadeAtual == null) {
        AlertaUtil.mostrarErro("Erro", "Propriedade não selecionada", "Selecione uma propriedade antes de continuar.");
        return;
    }

    double valorPagamento = Double.parseDouble(valorTexto) / 100.0;
    Date dataTransacao = Date.valueOf(dataTransacaoLD);

    int idCliente = clienteSelecionado.getId();
    int idPropriedade = propriedadeAtual.getId();

    propriedadeAtual.setDisponibilidade(false);  
    boolean sucessoAtualizacao = PropriedadesDAO.atualizarPropriedade(propriedadeAtual);
    if (!sucessoAtualizacao) {
        AlertaUtil.mostrarErro("Erro", "Falha na atualização", "Não foi possível marcar o imóvel como vendido.");
        return; 
    }

    boolean sucesso = TransacoesDAO.cadastrarTransacao(formaPagamento, dataTransacao, valorPagamento, idCliente, idPropriedade);

    if (sucesso) {
        AlertaUtil.mostrarInformacao("Sucesso", "Pagamento", "Pagamento efetuado com sucesso!");
        if (stage != null) stage.close();

        abrirTelaContrato(propriedadeAtual, clienteSelecionado);

    } else {
        AlertaUtil.mostrarErro("Erro", "Falha no pagamento", "Erro ao tentar salvar o pagamento. Tente novamente.");
    }
}

    public void preencherInformacoesImovel(Propriedades propriedade) {
        if (propriedade != null) {
            this.propriedadeAtual = propriedade;
            labelIDImovel.setText(String.valueOf(propriedade.getId()));

            NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
            labelValor.setText(currencyFormat.format(propriedade.getPreco()));

            labelTipoPropriedade.setText(propriedade.getTipoPropriedade());
        }
    }

    void setPropriedade(Propriedades propriedadeSelecionada) {
        preencherInformacoesImovel(propriedadeSelecionada);
    }

    void setStage(Stage telaPG) {
        this.stage = telaPG;
    }

    private void abrirTelaContrato(Propriedades propriedadeAtual, Cliente clienteSelecionado) {
        try {
            Contrato contrato = new Contrato();
            contrato.setDataContrato(LocalDate.now()); // LocalDate mesmo no modelo
            contrato.setValorCompra(propriedadeAtual.getPreco());
            contrato.setIdImovel(propriedadeAtual.getId());
            contrato.setIdCliente(clienteSelecionado.getId());

            Date dataContratoSql = Date.valueOf(contrato.getDataContrato());

            int idGerado = ContratoDAO.cadastrarContrato(contrato, dataContratoSql, contrato.getValorCompra(),
                    propriedadeAtual.getId(), clienteSelecionado.getId());

            if (idGerado > 0) {
                URL url = new File("src/main/java/view/TelaContrato.fxml").toURI().toURL();
                FXMLLoader loader = new FXMLLoader(url);
                Parent root = loader.load();

                TelaContratoController controller = loader.getController();
                controller.setDados(contrato, propriedadeAtual, clienteSelecionado);

                Stage telaC = new Stage();
                controller.setStage(telaC);

                Scene scene = new Scene(root);
                Image icone = new Image(getClass().getResourceAsStream("/icons/Bh.png"));
                telaC.getIcons().add(icone);
                telaC.setScene(scene);
                telaC.setTitle("BlueHorizon - Contrato de Venda");
                telaC.setMaximized(false);
                telaC.show();

            } else {
                AlertaUtil.mostrarErro("Erro", "Falha ao salvar o contrato", "Verifique os dados e tente novamente.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
