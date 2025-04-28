package controller;

import java.sql.SQLException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Funcionario;
import model.FuncionarioDAO;
import util.AlertaUtil;

public class TelaAlterarDadosFuncionariosController {

      @FXML
    private Button btnAlterarDadosFuncionario;

    @FXML
    private Button btnCancelarFuncionario;

    @FXML
    private ComboBox<String> cmbxAlterarCargoFuncionario;

    @FXML
    private TextField txtfdAlterarCPFFuncionario;

    @FXML
    private TextField txtfdAlterarDataContratoFuncionario;

    @FXML
    private TextField txtfdAlterarDataNascimentoFuncionario;

    @FXML
    private TextField txtfdAlterarEmailFuncionario;

    @FXML
    private TextField txtfdAlterarEnderecoFuncionario;

    @FXML
    private TextField txtfdAlterarSalarioFuncionario;

    @FXML
    private TextField txtfdAlterarSenhaFuncionario;

    @FXML
    private TextField txtfdAlterarTelefoneFuncionario;

    @FXML
    private TextField txtfdNomeFuncionarioAlterar;

    
    private Funcionario funcionario;

    @FXML
    void ActionAlterarDadosFuncionario(ActionEvent event) throws SQLException {
        
        //AJUSTAR
        
      /*  funcionario.setNome(.getText());
        funcionario.setCpf(txtCPF.getText());
        funcionario.setTelefone(txtTelefone.getText());
        funcionario.setEndereco(txtEndereco.getText());
        funcionario.setEmail(txtEmail.getText());
        funcionario.setSenha(txtSenha.getText());
        funcionario.setCargo(txtCargo.getText());
        funcionario.setSalario(Double.parseDouble(txtSalario.getText()));
        funcionario.setDataNascimento(dpNascimento.getValue());
        funcionario.setDataContratacao(dpContratacao.getValue());

        FuncionarioDAO.atualizarFuncionario(funcionario);

        ((Stage) ((Button) event.getSource()).getScene().getWindow()).close();
        
        if (txtfdAlterarDataContratoFuncionario.getText().isEmpty() && 
            txtfdAlterarDataNascimentoFuncionario.getText().isEmpty() && 
            txtfdAlterarCPFFuncionario.getText().isEmpty() &&
            txtfdAlterarEmailFuncionario.getText().isEmpty() &&
            txtfdAlterarSenhaFuncionario.getText().isEmpty() &&
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

    void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;

        // Preencher os campos com os dados do funcionário
        txtfdNomeFuncionarioAlterar.setText(funcionario.getNome());
        txtfdAlterarCPFFuncionario.setText(funcionario.getCpf());
        txtfdAlterarTelefoneFuncionario.setText(funcionario.getTelefone());
        txtfdAlterarEnderecoFuncionario.setText(funcionario.getEndereco());
        txtfdAlterarEmailFuncionario.setText(funcionario.getEmail());
        txtfdAlterarSenhaFuncionario.setText(funcionario.getSenha());
        cmbxAlterarCargoFuncionario.setValue(funcionario.getCargo());
        txtfdAlterarSalarioFuncionario.setText(funcionario.getSalario());
        txtfdAlterarDataNascimentoFuncionario.setText(funcionario.getDataNascimento());
        txtfdAlterarDataContratoFuncionario.setText(funcionario.getDataContratacao());
        
        
    }*/
    
}
}
