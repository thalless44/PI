package controller;

import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Cliente;
import model.ClienteDAO;
import util.AlertaUtil;

public class TelaAlterarDadosClientesController  {

    @FXML
    private Button btnAlterarDados;

    @FXML
    private Button btnCancelar;

    @FXML
    private Label lblAlterarDadosClientes;

    @FXML
    private Label lblEmail;

    @FXML
    private Label lblNome;

    @FXML
    private Label lblTelefone;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtNome;

    @FXML
    private TextField txtTelefone;
    
    private Cliente cliente;
    
    private Stage stage;

    @FXML
    void ActionAlterarDados(ActionEvent event) throws SQLException {
        if (txtNome.getText().isEmpty() || txtEmail.getText().isEmpty() || txtTelefone.getText().isEmpty()) {
            AlertaUtil.mostrarAviso("Aviso", "Campos obrigatórios", 
                "Todos os campos devem ser preenchidos!");
        }else {
            cliente.setNome(txtNome.getText());
            cliente.setTelefone(txtTelefone.getText());
            cliente.setEmail(txtEmail.getText());
            ClienteDAO.atualizarCliente(cliente);
            Optional<ButtonType> resultado = AlertaUtil.mostrarConfirmacao("Confimação", "Alterar Dados", "deseja alterar os dados?");
            if (resultado.isPresent()){
            ButtonType botaoPressionado = resultado.get();
            if(botaoPressionado == ButtonType.OK){
                 stage.close();
                 
            }
            }
        } 
    }

    @FXML
    void ActionCancelar(ActionEvent event) {
        if (FecharTelaAlterarDadosCliente()) {              
            Stage stage = (Stage) btnCancelar.getScene().getWindow();
            stage.close();          
        } else {
            event.consume();
        }
    }

    private boolean FecharTelaAlterarDadosCliente() {
        return AlertaUtil.mostrarConfirmacao(
            "Confirmação", 
            "Tem certeza que deseja fechar a tela de alterar dados de clientes?",
            "Todas as alterações não salvas serão perdidas e a tela atual será fechada!"
        ).filter(response -> response == ButtonType.OK).isPresent();
    }

    void setStage(Stage telaADC) {
        this.stage = telaADC;
    }

    void setCliente(Cliente clienteSelecionado) {
        
        System.out.println("Cliente recebido: " + cliente);
        this.cliente = clienteSelecionado;

        // Preencher os campos com os dados do funcionário
        txtNome.setText(cliente.getNome());
        txtEmail.setText(cliente.getEmail());
        txtTelefone.setText(cliente.getTelefone());
            }

}