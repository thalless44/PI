package controller;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.text.NumberFormat;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import model.Cliente;
import model.Contrato;
import model.Propriedades;
import util.AlertaUtil;

public class TelaContratoController {
    
    private Stage stage;
    
    @FXML
    private Button btnSair;

    @FXML
    private Label lblContrato, lblDataCadastro, lblDataContrato, lblEmailCliente, lblIdContrato,
                  lblIdImovel, lblNomeCliente, lblTelefoneCliente, lblValorImovel, lblValorTotal;

    private Contrato contratoAtual;
    private Propriedades propriedadeAtual;
    private Cliente clienteAtual;
    
    
    public void setDados(Contrato contrato, Propriedades propriedade, Cliente cliente) {

        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));

        if (contrato == null || propriedade == null || cliente == null) {
            AlertaUtil.mostrarErro("Erro", "Dados incompletos", "Contrato, propriedade ou cliente faltando.");
            return;
        }

        lblIdContrato.setText(String.valueOf(contrato.getIdContrato()));
        lblDataContrato.setText(formatDate(contrato.getDataContrato()));  // LocalDate do contrato
        lblValorTotal.setText(currencyFormat.format(contrato.getValorCompra()));

        lblIdImovel.setText(String.valueOf(propriedade.getId()));
        lblDataCadastro.setText(formatDate(propriedade.getDataCadastro())); // java.sql.Date da propriedade
        lblValorImovel.setText(currencyFormat.format(propriedade.getPreco()));

        lblNomeCliente.setText(cliente.getNome());
        lblEmailCliente.setText(cliente.getEmail());
        lblTelefoneCliente.setText(cliente.getTelefone());
    }

    private String formatDate(LocalDate data) {
        if (data == null) return "";
        return data.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    private String formatDate(Date data) {
        if (data == null) return "";
        return data.toLocalDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }
   
    @FXML
    void ActionSair(ActionEvent event) {
        if (FecharTelaContrato()) {
            Stage stage = (Stage) btnSair.getScene().getWindow();
            stage.close();
        } else {
            event.consume();
        }
    }

    private boolean FecharTelaContrato() {
        return AlertaUtil.mostrarConfirmacao(
            "Aviso",
            "Tem certeza que deseja fechar a tela de contrato?",
            "Todas as alterações não salvas serão perdidas!"
        ).filter(response -> response == ButtonType.OK).isPresent();
    }

    void setStage(Stage telaC) {
        this.stage = telaC;
    }
}
