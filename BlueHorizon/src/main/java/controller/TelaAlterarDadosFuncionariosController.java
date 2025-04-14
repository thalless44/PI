package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import util.AlertaUtil;

public class TelaAlterarDadosFuncionariosController {

    @FXML
    private Button btnAlterarDadosFuncionario;

    @FXML
    private Button btnCancelarFuncionario;

    @FXML
    private ComboBox<?> cmbxAlterarCargoFuncionario;

    @FXML
    private Label lblAlterarDadosClientes;

    @FXML
    private Label lblEmail;

    @FXML
    private Label lblNome;

    @FXML
    private Label lblTelefone;

    @FXML
    private TextField txtfdAlterarDataFuncionario;

    @FXML
    private TextField txtfdAlterarEmailFuncionario;

    @FXML
    private TextField txtfdAlterarEnderecoFuncionario;

    @FXML
    private TextField txtfdAlterarSalarioFuncionario;

    @FXML
    private TextField txtfdAlterarTelefoneFuncionario;

    @FXML
    private TextField txtfdNomeFuncionarioAlterar;

    @FXML
    void ActionAlterarDadosFuncionario(ActionEvent event) {
        if (txtfdAlterarDataFuncionario.getText().isEmpty() && 
            txtfdAlterarEnderecoFuncionario.getText().isEmpty() && 
            txtfdAlterarSalarioFuncionario.getText().isEmpty() && 
            txtfdAlterarTelefoneFuncionario.getText().isEmpty() && 
            txtfdNomeFuncionarioAlterar.getText().isEmpty()) {
            
            AlertaUtil.mostrarAviso("Preencha um dos campos", 
                "Pelo menos um dos campos deve ser preenchido!");
        }
    }

    @FXML
    void ActionCancelar(ActionEvent event) {
        if (FecharTelaAlterarDadosFuncionario()) {    
            Stage stage = (Stage) btnCancelarFuncionario.getScene().getWindow();
            stage.close();          
        } else {
            event.consume();
        }
    }

    private boolean FecharTelaAlterarDadosFuncionario() {
        return AlertaUtil.mostrarConfirmacao(
            "Confirmação", 
            "Tem certeza que deseja fechar a tela de alteração de dados de funcionário?\nTodas as alterações não salvas serão perdidas!"
        ).filter(response -> response == ButtonType.OK).isPresent();
    }
}