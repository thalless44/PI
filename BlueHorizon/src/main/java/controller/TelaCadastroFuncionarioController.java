package controller;

import static java.lang.Integer.parseInt;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.FuncionarioDAO;
import model.LimitarCaracter;

public class TelaCadastroFuncionarioController {
    private Stage stage;

    @FXML
    private Button btnEfetuarCadastroFuncionario;

    @FXML
    private Button btnSair;
    
    @FXML
    private TextField txtFConfirmacaoSenha;

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
    
    
    @FXML
    public void initialize() { 
        
    new LimitarCaracter(50, LimitarCaracter.TipoEntrada.NOME).applyToTextInputControl(txtFNome);
    new LimitarCaracter(100, LimitarCaracter.TipoEntrada.NOME).applyToTextInputControl(txtFEndereco);
    new LimitarCaracter(12, LimitarCaracter.TipoEntrada.NUMEROINTEIRO).applyToTextInputControl(txtFTelefone);
    new LimitarCaracter(100, LimitarCaracter.TipoEntrada.EMAIL).applyToTextInputControl(txtFEmail);
    new LimitarCaracter(100, LimitarCaracter.TipoEntrada.NUMERODECIMAL).applyToTextInputControl(txtFSalario);
    new LimitarCaracter(10, LimitarCaracter.TipoEntrada.DATA).applyToTextInputControl(txtFDataContratacao);
    new LimitarCaracter(10, LimitarCaracter.TipoEntrada.DATA).applyToTextInputControl(txtFDataNascimento);
    new LimitarCaracter(14, LimitarCaracter.TipoEntrada.CPF).applyToTextInputControl(txtFCPF);
    new LimitarCaracter(15, LimitarCaracter.TipoEntrada.FONE).applyToTextInputControl(txtFTelefone);

        cmbxCargo.setItems(FXCollections.observableArrayList("Gerente", "Corretor"));
    }

    @FXML
    void OnActionBtnEfetuarCadastroFuncionario(ActionEvent event) {
        
        if (txtFNome.getText().isEmpty()||txtFCPF.getText().isEmpty()||txtFEndereco.getText().isEmpty()||txtFTelefone.getText().isEmpty()
                ||txtFEmail.getText().isEmpty()||txtFSenha.getText().isEmpty()|| txtFSalario.getText().isEmpty()
                || txtFDataContratacao.getText().isEmpty()||txtFDataNascimento.getText().isEmpty()){
            Alert erroDados = new Alert(Alert.AlertType.WARNING);
            erroDados.setTitle("Erro");
            erroDados.setHeaderText("Campos obrigatórios");
            erroDados.setContentText("Todos os campos devem ser preenchidos!");
            erroDados.showAndWait();
        }else if (!txtFSenha.getText().equals(txtFConfirmacaoSenha.getText())) {
            Alert erro = new Alert(Alert.AlertType.WARNING);
            erro.setTitle("Erro");
            erro.setHeaderText("Senha se diferem");
            erro.showAndWait();
        }else if (txtFTelefone.getText().length()!=15){
            Alert erro = new Alert(Alert.AlertType.WARNING);
            erro.setTitle("Eroo");
            erro.setHeaderText("Falta numeros no telefone");
            erro.showAndWait();
        }else if (txtFCPF.getText().length()!=14){
        Alert erro = new Alert(Alert.AlertType.WARNING);
            erro.setTitle("Eroo");
            erro.setHeaderText("Falta numeros no CPF");
            erro.showAndWait();
        }else if(txtFSalario.getText().length()<4){
            Alert erro = new Alert(Alert.AlertType.WARNING);
            erro.setTitle("Eroo");
            erro.setHeaderText("Falta numeros no Salarios");
            erro.showAndWait();
        
        }else if(txtFEmail.getText().length()<10){
            Alert erro = new Alert(Alert.AlertType.WARNING);
            erro.setTitle("Eroo");
            erro.setHeaderText("Falta numeros no Salarios");
            erro.showAndWait();
        }else if(txtFDataContratacao.getText().length()!=10){
            Alert erro = new Alert(Alert.AlertType.WARNING);
            erro.setTitle("Eroo");
            erro.setHeaderText("Falta numeros Data de Contratação");
            erro.showAndWait();
        }else if(txtFDataNascimento.getText().length()!=10){
            Alert erro = new Alert(Alert.AlertType.WARNING);
            erro.setTitle("Eroo");
            erro.setHeaderText("Falta numeros na Data de Nascimento");
            erro.showAndWait();
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
        
        // Converter a data para o formato correto
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate dataNascimentoLD = LocalDate.parse(txtFDataNascimento.getText(), formato);
        LocalDate dataContratacaoLD = LocalDate.parse(txtFDataContratacao.getText(), formato);

        // Converter LocalDate para Date (formato aceito pelo MySQL)
        Date dataNascimento = Date.valueOf(dataNascimentoLD);
        Date dataContratacao = Date.valueOf(dataContratacaoLD);

        boolean sucesso = FuncionarioDAO.cadastrarFuncionario(nome, cpf, dataNascimento, dataContratacao,
                endereco, telefone, email, senha, cargo, salario);

        Alert alert = new Alert(sucesso ? Alert.AlertType.INFORMATION : Alert.AlertType.ERROR);
        alert.setTitle(sucesso ? "Sucesso" : "Erro");
        alert.setHeaderText(sucesso ? "Cadastro realizado!" : "Erro ao cadastrar!");
        alert.setContentText(sucesso ? "O funcionário foi cadastrado com sucesso." : "Verifique os dados e tente novamente.");
        alert.showAndWait();
    }
        
        

    @FXML
    void OnActionBtnSair(ActionEvent event) {
        
        if(FecharTelaCadastroFuncionario()){    
            
            Stage stage = (Stage) btnSair.getScene().getWindow();
            stage.close();          
        }else{
            event.consume();
        }
        
        

    }

    private boolean FecharTelaCadastroFuncionario() {
        
        Alert confirmar = new Alert(Alert.AlertType.WARNING);
        confirmar.setTitle("Confirmação");
        confirmar.setHeaderText("Tem certeza que deseja fechar a tela de cadastro de funcionarios?");
        confirmar.setContentText("Todas as alterações não salvas serão perdidas!");
        return confirmar.showAndWait().filter(response -> response == ButtonType.OK).isPresent();
    }
    
    public void setStage(Stage stage) {
        
        
        this.stage = stage;
    }
     
}
