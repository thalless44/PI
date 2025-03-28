package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class TelaCadastroClienteController {

    @FXML
    private Button btnCancelarcadastro;

    @FXML
    private Button btnEfetuarCadastro;

    @FXML
    private TextField txtEmailCliente;

    @FXML
    private TextField txtNomeCliente;

    @FXML
    private TextField txtTelefoneCliente;

    @FXML
    void ActionCancelarCadastro(ActionEvent event) {
        Alert confirmar = new Alert(Alert.AlertType.CONFIRMATION);
        confirmar.setTitle("Confirmação");
        confirmar.setHeaderText("Cancelar Cadastro");
        confirmar.setContentText("Tem certeza que deseja cancelar o cadastro?");
        
        if (confirmar.showAndWait().filter(response -> response == ButtonType.OK).isPresent()) {
            Stage stage = (Stage) btnCancelarcadastro.getScene().getWindow();
            stage.close();
        } else {
            event.consume();
        }
    }

    @FXML
    void ActionEfetuarCadastro(ActionEvent event) {
        
        String nome = txtNomeCliente.getText();
        String email = txtEmailCliente.getText();
        String telefone = txtTelefoneCliente.getText();

        if (nome.isEmpty() || email.isEmpty() || telefone.isEmpty()) {
            Alert erro = new Alert(Alert.AlertType.ERROR);
            erro.setTitle("Erro");
            erro.setHeaderText("Campos obrigatórios");
            erro.setContentText("Todos os campos devem ser preenchidos!");
            erro.show();
        } else {
            Alert sucesso = new Alert(Alert.AlertType.INFORMATION);
            sucesso.setTitle("Sucesso");
            sucesso.setHeaderText("Cadastro Efetuado");
            sucesso.setContentText("Cliente cadastrado com sucesso!\n\nNome: " + nome + "\nEmail: " + email + "\nTelefone: " + telefone);
            sucesso.showAndWait();
        }
    }
}
