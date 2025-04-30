package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.ClienteDAO;
import util.AlertaUtil;

public class TelaCadastroClienteController {
    
    private Stage stage;

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
    
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    void ActionCancelarCadastro(ActionEvent event) {
        
        if(CancelarCadastroCliente()){
            
            Stage stage = (Stage) btnCancelarcadastro.getScene().getWindow();
            stage.close();
            
        }else{
            event.consume();
        }
    }

    @FXML
    void ActionEfetuarCadastro(ActionEvent event) {
        
        cadastrarCliente();
    }

private boolean CancelarCadastroCliente() {
    return AlertaUtil.mostrarConfirmacao(
        "Aviso", 
        "Tem certeza que deseja fechar a tela de cadastro de cliente?",
        "Todas as alterações não salvas serão perdidas e a tela atual será fechada!"
    ).filter(response -> response == ButtonType.OK).isPresent();
}

    private void cadastrarCliente() {
    String nome = txtNomeCliente.getText();
    String email = txtEmailCliente.getText();
    String telefone = txtTelefoneCliente.getText();

    if (nome.isEmpty() || email.isEmpty() || telefone.isEmpty()) {
        AlertaUtil.mostrarErro("Erro","Campos obrigatórios", 
            "Todos os campos devem ser preenchidos!");
    } else {
        boolean sucesso = ClienteDAO.cadastrarCliente(nome, email, telefone);

        if (sucesso) {
            AlertaUtil.mostrarInformacao("Cadastro de cliente", "Cadastro realizado", 
                "O cliente foi cadastrado com sucesso.");

            // Fecha a janela
            Stage stage = (Stage) btnCancelarcadastro.getScene().getWindow();
            stage.close();
        } else {
            AlertaUtil.mostrarErro("Erro", "Erro ao cadastrar", 
                "Ocorreu um erro ao tentar cadastrar o cliente.");
        }
    }
}
}
