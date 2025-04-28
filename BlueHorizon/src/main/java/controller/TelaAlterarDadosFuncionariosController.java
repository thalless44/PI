package controller;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
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
        
        //AJUSTAR ESSE MÉTODO
        
        // Validação antes de tentar atualizar os dados
    if (txtfdNomeFuncionarioAlterar.getText().isEmpty() && 
        txtfdAlterarCPFFuncionario.getText().isEmpty() && 
        txtfdAlterarTelefoneFuncionario.getText().isEmpty() && 
        txtfdAlterarEnderecoFuncionario.getText().isEmpty() && 
        txtfdAlterarEmailFuncionario.getText().isEmpty() && 
        txtfdAlterarSenhaFuncionario.getText().isEmpty() && 
        txtfdAlterarSalarioFuncionario.getText().isEmpty() && 
        txtfdAlterarDataNascimentoFuncionario.getText().isEmpty() && 
        txtfdAlterarDataContratoFuncionario.getText().isEmpty()) {

        AlertaUtil.mostrarAviso("Preencha um dos campos", "Pelo menos um dos campos deve ser preenchido!");
        return;  // Se nada for preenchido, não prossegue
    }

    // Ajuste nas variáveis
    funcionario.setNome(txtfdNomeFuncionarioAlterar.getText());
    funcionario.setCpf(txtfdAlterarCPFFuncionario.getText());
    funcionario.setTelefone(txtfdAlterarTelefoneFuncionario.getText());
    funcionario.setEndereco(txtfdAlterarEnderecoFuncionario.getText());
    funcionario.setEmail(txtfdAlterarEmailFuncionario.getText());
    funcionario.setSenha(txtfdAlterarSenhaFuncionario.getText());
    
    // Valida e seta o cargo se o valor não for nulo
    if (cmbxAlterarCargoFuncionario.getValue() != null) {
        funcionario.setCargo(cmbxAlterarCargoFuncionario.getValue());
    }

        funcionario.setSalario(txtfdAlterarSalarioFuncionario.getText());

    // Validação da Data de Nascimento
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    if (!txtfdAlterarDataNascimentoFuncionario.getText().isEmpty()) {
        try {
            funcionario.setDataNascimento(LocalDate.parse(txtfdAlterarDataNascimentoFuncionario.getText(), formatter));
        } catch (DateTimeParseException e) {
            AlertaUtil.mostrarErro("Data de Nascimento inválida", "Formato correto: yyyy-MM-dd");
            return;
        }
    }

    // Validação da Data de Contratação
    if (!txtfdAlterarDataContratoFuncionario.getText().isEmpty()) {
        try {
            funcionario.setDataContratacao(LocalDate.parse(txtfdAlterarDataContratoFuncionario.getText(), formatter));
        } catch (DateTimeParseException e) {
            AlertaUtil.mostrarErro("Data de Contratação inválida", "Formato correto: yyyy-MM-dd");
            return;
        }
    }

    // Atualizar no banco
    FuncionarioDAO.atualizarFuncionario(funcionario);

    // Fechar a tela
    ((Stage) ((Button) event.getSource()).getScene().getWindow()).close();
        
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
        txtfdAlterarSalarioFuncionario.setText(String.valueOf(funcionario.getSalario()));
        txtfdAlterarDataNascimentoFuncionario.setText(funcionario.getDataNascimento().toString());
        txtfdAlterarDataContratoFuncionario.setText(funcionario.getDataContratacao().toString());
        
        
    }
    
}

