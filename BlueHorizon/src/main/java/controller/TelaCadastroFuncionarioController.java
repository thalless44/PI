package controller;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.FuncionarioDAO;
import util.AlertaUtil;
import util.LimitarCaracter;
import util.LimitarCaracter.VerificarData;

public class TelaCadastroFuncionarioController {
    private Stage stage;

    @FXML
    private Button btnEfetuarCadastroFuncionario;

    @FXML
    private Button btnSair;

    @FXML
    private ComboBox<String> cmbxCargo;

    @FXML
    private TextField txtFCPF;

    @FXML
    private TextField txtFDataContratacao;

    @FXML
    private TextField txtFDataNascimento;

    @FXML
    private TextField txtFEmail;

    @FXML
    private TextField txtFEndereco;

    @FXML
    private TextField txtFNome;

    @FXML
    private TextField txtFSalario;

    @FXML
    private TextField txtFSenha;
    
    @FXML
    private TextField txtFConfirmacaoSenha;

    @FXML
    private TextField txtFTelefone;
    
    public void setStage(Stage stage) {
        this.stage = stage;
    }
    
    @FXML
    public void initialize() {
        
        new LimitarCaracter (50, LimitarCaracter.TipoEntrada.NOME).applyToTextInputControl(txtFNome);
        new LimitarCaracter (100, LimitarCaracter.TipoEntrada.EMAIL).applyToTextInputControl(txtFEmail);
        new LimitarCaracter (100, LimitarCaracter.TipoEntrada.NUMERODECIMAL).applyToTextInputControl(txtFSalario);
        new LimitarCaracter (10, LimitarCaracter.TipoEntrada.DATA).applyToTextInputControl(txtFDataContratacao);
        new LimitarCaracter (10, LimitarCaracter.TipoEntrada.DATA).applyToTextInputControl(txtFDataNascimento);
        new LimitarCaracter (14, LimitarCaracter.TipoEntrada.CPF).applyToTextInputControl(txtFCPF);
        new LimitarCaracter (15, LimitarCaracter.TipoEntrada.FONE).applyToTextInputControl(txtFTelefone);
         
        cmbxCargo.setItems(FXCollections.observableArrayList("Gerente", "Corretor"));
    }

    @FXML
    void OnActionBtnEfetuarCadastroFuncionario(ActionEvent event) {
         boolean dataValidaNascimento = VerificarData.validarData(txtFDataNascimento.getText());
         boolean dataValidaContratacao = VerificarData.validarData(txtFDataContratacao.getText());
        
        if (txtFNome.getText().isEmpty()||txtFCPF.getText().isEmpty()||txtFEndereco.getText().isEmpty()||txtFTelefone.getText().isEmpty()
                ||txtFEmail.getText().isEmpty()||txtFSenha.getText().isEmpty()|| txtFSalario.getText().isEmpty()
                || txtFDataContratacao.getText().isEmpty()||txtFDataNascimento.getText().isEmpty()){
            AlertaUtil.mostrarErro("Erro", "Erro ao cadastrar", "Todos os campos devem ser preenchidos!");
           
        }else if (!txtFSenha.getText().equals(txtFConfirmacaoSenha.getText())) {
            AlertaUtil.mostrarErro("Erro", "Erro ao cadastrar", "Verifique o campo Senha e Confirmação .");
            
        }else if (txtFTelefone.getText().length()!=15){
            AlertaUtil.mostrarErro("Erro", "Erro ao cadastrar", "Verifique o campo Telefone.");
            
        }else if (txtFCPF.getText().length()!=14){
            AlertaUtil.mostrarErro("Erro", "Erro ao cadastrar", "Verifique o campo CPF.");
        
        }else if(txtFSalario.getText().length()<4){
            AlertaUtil.mostrarErro("Erro", "Erro ao cadastrar", "Verifique o campo Salario.");
        
        }else if(txtFEmail.getText().length()<10){
            AlertaUtil.mostrarErro("Erro", "Erro ao cadastrar", "Verifique o campo Email.");
            
        }else if(txtFDataContratacao.getText().length()!=10 || !dataValidaContratacao ){
            AlertaUtil.mostrarErro("Erro", "Erro ao cadastrar", "Verifique o campo Data de Contratação.");
      
        }else if(txtFDataNascimento.getText().length()!=10 || !dataValidaNascimento){
            AlertaUtil.mostrarErro("Erro", "Erro ao cadastrar", "Verifique o campo Data de Nascimento.");
            
        }else{cadastrarFuncionario();}
          
    }
    
    
    private void cadastrarFuncionario() {
        
        String nome = txtFNome.getText();
        String cpf = txtFCPF.getText();
        String endereco = txtFEndereco.getText();
        String telefone = txtFTelefone.getText();
        String email = txtFEmail.getText();
        String senha = txtFSenha.getText();
        String cargo = cmbxCargo.getValue();
        double salario = Double.parseDouble(txtFSalario.getText());
        
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate dataNascimentoLD = LocalDate.parse(txtFDataNascimento.getText(), formato);
        LocalDate dataContratacaoLD = LocalDate.parse(txtFDataContratacao.getText(), formato);

        Date dataNascimento = Date.valueOf(dataNascimentoLD);
        Date dataContratacao = Date.valueOf(dataContratacaoLD);

        boolean sucesso = FuncionarioDAO.cadastrarFuncionario(nome, cpf, dataNascimento, dataContratacao,
                endereco, telefone, email, senha, cargo, salario);

        if (sucesso) {
            AlertaUtil.mostrarInformacao("Cadastro de funcionario", "Cadastro realizado", "O funcionário foi cadastrado com sucesso.");
        } else {
            AlertaUtil.mostrarErro("Erro", "Erro ao cadastrar", "Verifique os dados e tente novamente.");
        }
    }

    @FXML
    void OnActionBtnSair(ActionEvent event) {
        if (FecharTelaCadastroFuncionario()) {
            Stage stage = (Stage) btnSair.getScene().getWindow();
            stage.close();
        } else {
            event.consume();
        }
    }

 private boolean FecharTelaCadastroFuncionario() {
    return AlertaUtil.mostrarConfirmacao(
        "Confirmação", 
        "Tem certeza que deseja fechar a tela de cadastro de funcionários?",
        "Todas as alterações não salvas serão perdidas!"
    ).filter(response -> response == ButtonType.OK).isPresent();
 }
}
