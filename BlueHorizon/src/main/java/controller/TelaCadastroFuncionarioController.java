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
    private TextField txtFTelefone;
    
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    void OnActionBtnEfetuarCadastroFuncionario(ActionEvent event) {
        
        if (txtFNome.getText().isEmpty() || txtFCPF.getText().isEmpty() || txtFEndereco.getText().isEmpty() || txtFTelefone.getText().isEmpty()
                || txtFEmail.getText().isEmpty() || txtFSenha.getText().isEmpty() || txtFSalario.getText().isEmpty()
                || txtFDataContratacao.getText().isEmpty() || txtFDataNascimento.getText().isEmpty()) {
            
            AlertaUtil.mostrarAviso("Campos obrigatórios", "Todos os campos devem ser preenchidos!");
        
        } else if (!txtFCPF.getText().matches("[0-9]+")) {
            
            AlertaUtil.mostrarAviso("Campos CPF", "Não deve conter letras");
        
        } else {
            cadastrarFuncionario();
        }
    }
    
    @FXML
    public void initialize() {
        
        
        
        cmbxCargo.setItems(FXCollections.observableArrayList("Gerente", "Corretor"));
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
            AlertaUtil.mostrarInformacao("Cadastro realizado", "O funcionário foi cadastrado com sucesso.");
        } else {
            AlertaUtil.mostrarErro("Erro ao cadastrar", "Verifique os dados e tente novamente.");
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
        "Tem certeza que deseja fechar a tela de cadastro de funcionários?\nTodas as alterações não salvas serão perdidas!"
    ).filter(response -> response == ButtonType.OK).isPresent();
 }
}
