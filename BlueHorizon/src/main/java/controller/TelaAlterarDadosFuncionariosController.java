package controller;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import javafx.collections.FXCollections;
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
import util.LimitarCaracter;

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
    private Stage stage;
    
    @FXML
    public void initialize() {
        
        new LimitarCaracter (50, LimitarCaracter.TipoEntrada.NOME).applyToTextInputControl(txtfdNomeFuncionarioAlterar);
        new LimitarCaracter (100, LimitarCaracter.TipoEntrada.EMAIL).applyToTextInputControl(txtfdAlterarEmailFuncionario);
        new LimitarCaracter (100, LimitarCaracter.TipoEntrada.NUMERODECIMAL).applyToTextInputControl(txtfdAlterarSalarioFuncionario);
        new LimitarCaracter (10, LimitarCaracter.TipoEntrada.DATA).applyToTextInputControl(txtfdAlterarDataContratoFuncionario);
        new LimitarCaracter (10, LimitarCaracter.TipoEntrada.DATA).applyToTextInputControl(txtfdAlterarDataNascimentoFuncionario);
        new LimitarCaracter (14, LimitarCaracter.TipoEntrada.CPF).applyToTextInputControl(txtfdAlterarCPFFuncionario);
        new LimitarCaracter (15, LimitarCaracter.TipoEntrada.FONE).applyToTextInputControl(txtfdAlterarTelefoneFuncionario);
        
        
        
        
        cmbxAlterarCargoFuncionario.setItems(FXCollections.observableArrayList("Gerente", "Corretor"));
    }

    @FXML
    void ActionAlterarDadosFuncionario(ActionEvent event) throws SQLException {
        
         boolean dataValidaNascimento = LimitarCaracter.VerificarData.validarData(txtfdAlterarDataNascimentoFuncionario.getText());
         boolean dataValidaContratacao = LimitarCaracter.VerificarData.validarData(txtfdAlterarDataContratoFuncionario.getText());
         
          if (txtfdNomeFuncionarioAlterar.getText().isEmpty()||txtfdAlterarCPFFuncionario.getText().isEmpty()||txtfdAlterarEnderecoFuncionario.getText().isEmpty()||txtfdAlterarTelefoneFuncionario.getText().isEmpty()
                ||txtfdAlterarEmailFuncionario.getText().isEmpty()||txtfdAlterarSenhaFuncionario.getText().isEmpty()|| txtfdAlterarSalarioFuncionario.getText().isEmpty()
                || txtfdAlterarDataContratoFuncionario.getText().isEmpty()||txtfdAlterarDataNascimentoFuncionario.getText().isEmpty()){
            AlertaUtil.mostrarErro("Erro ao cadastrar", "Todos os campos devem ser preenchidos!");
            
        }else if (txtfdAlterarTelefoneFuncionario.getText().length()!=15){
            AlertaUtil.mostrarErro("Erro ao cadastrar", "Verifique o campo Telefone.");
            
        }else if (txtfdAlterarCPFFuncionario.getText().length()!=14){
            AlertaUtil.mostrarErro("Erro ao cadastrar", "Verifique o campo CPF.");
        
        }else if(txtfdAlterarSalarioFuncionario.getText().length()<4){
            AlertaUtil.mostrarErro("Erro ao cadastrar", "Verifique o campo Salario.");
        
        }else if(txtfdAlterarEmailFuncionario.getText().length()<10){
            AlertaUtil.mostrarErro("Erro ao cadastrar", "Verifique o campo Email.");
            
        }else if(txtfdAlterarDataContratoFuncionario.getText().length()!=10 || !dataValidaContratacao ){
            AlertaUtil.mostrarErro("Erro ao cadastrar", "Verifique o campo Data de Contratação.");
      
        }else if(txtfdAlterarDataNascimentoFuncionario.getText().length()!=10 || !dataValidaNascimento){
            AlertaUtil.mostrarErro("Erro ao cadastrar", "Verifique o campo Data de Nascimento.");
            
        }else{
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

        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate dataNascimentoLD = LocalDate.parse(txtfdAlterarDataNascimentoFuncionario.getText(), formato);
        LocalDate dataContratacaoLD = LocalDate.parse(txtfdAlterarDataContratoFuncionario.getText(), formato);

        Date dataNascimento = Date.valueOf(dataNascimentoLD);
        Date dataContratacao = Date.valueOf(dataContratacaoLD);
    /*DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    if (!txtfdAlterarDataNascimentoFuncionario.getText().isEmpty()) {
        try {
            funcionario.setDataNascimento(LocalDate.parse(txtfdAlterarDataNascimentoFuncionario.getText(), formatter));
        } catch (DateTimeParseException e) {
            AlertaUtil.mostrarErro("Data de Nascimento inválida", "Formato correto: dd-MM-yyyy");
            return;
        }*/
    // Validação da Data de Contratação
    /*if (!txtfdAlterarDataContratoFuncionario.getText().isEmpty()) {
        try {
            funcionario.setDataContratacao(LocalDate.parse(txtfdAlterarDataContratoFuncionario.getText(), formatter));
        } catch (DateTimeParseException e) {
            AlertaUtil.mostrarErro("Data de Contratação inválida", "Formato correto: dd-MM-yyyy");
            return;
        }*/
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
        System.out.println("Funcionário recebido: " + funcionario);
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
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        txtfdAlterarDataNascimentoFuncionario.setText(funcionario.getDataNascimento().format(formatter));
        txtfdAlterarDataContratoFuncionario.setText(funcionario.getDataContratacao().format(formatter));
        
        
    }

    void setStage(Stage telaADF) {
        this.stage = telaADF;
    }
    
}

