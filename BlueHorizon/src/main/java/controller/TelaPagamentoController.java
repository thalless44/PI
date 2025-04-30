package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import util.AlertaUtil;

public class TelaPagamentoController {

    @FXML
    private Button btnCancelarpagamento;

    @FXML
    private Button btnEfetuarPagamento;

    @FXML
    private TextField txtEmailCliente;

    @FXML
    private TextField txtFormapagamento;

    @FXML
    private TextField txtIDpagamento;

    @FXML
    private TextField txtNomeCliente;

    @FXML
    private TextField txtTelefoneCliente;

    @FXML
    private TextField txtValorPagamento;

    @FXML
    private TextField txtdatapagamento;

    @FXML
    void ActionCancelarPagamento(ActionEvent event) {
        if (cancelarPagamento()) {
            Stage stage = (Stage) btnCancelarpagamento.getScene().getWindow();
            stage.close();
        } else {
            event.consume();
        }
    }

    @FXML
    void ActionEfetuarPagamento(ActionEvent event) {
        
        String idPagamento = txtIDpagamento.getText();
        String nome = txtNomeCliente.getText();
        String email = txtEmailCliente.getText();
        String telefone = txtTelefoneCliente.getText();
        String valor = txtValorPagamento.getText();
        String data = txtdatapagamento.getText();
        String formaPagamento = txtFormapagamento.getText();

        if (idPagamento.isEmpty() || nome.isEmpty() || email.isEmpty() || telefone.isEmpty()
                || valor.isEmpty() || data.isEmpty() || formaPagamento.isEmpty()) {
            AlertaUtil.mostrarAviso("Aviso", "Campos obrigatórios",
                    "Todos os campos devem ser preenchidos para efetuar o pagamento.");
        } else {
            // Aqui você pode adicionar lógica de persistência no banco, se necessário
            AlertaUtil.mostrarInformacao("Sucesso", "Pagamento efetuado",
                    "O pagamento foi realizado com sucesso para o cliente: " + nome);

            Stage stage = (Stage) btnEfetuarPagamento.getScene().getWindow();
            stage.close();
        }
    }

    private boolean cancelarPagamento() {
        return AlertaUtil.mostrarConfirmacao(
                "Confirmação de cancelamento",
                "Tem certeza que deseja cancelar o pagamento?",
                "Todas as informações não salvas serão perdidas."
        ).filter(response -> response == ButtonType.OK).isPresent();
    }
}