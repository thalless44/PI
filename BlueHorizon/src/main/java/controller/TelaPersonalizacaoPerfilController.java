package controller;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.collections.FXCollections;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import model.Funcionario;
import model.FuncionarioDAO;
import util.AlertaUtil;
import util.LimitarCaracter;

public class TelaPersonalizacaoPerfilController {
    private Stage stage; 

    @FXML
    private Button BtnPP; 
    
    @FXML
    private Button BtnPP1;

    @FXML
    private ComboBox<String> Cargocb; // Definido como ComboBox<String>

    @FXML
    private TextField Cpftxt;

    @FXML
    private TextField DatadeContratacaotxt;

    @FXML
    private TextField DatadeNascimentotxt;

    @FXML
    private TextField Emailtxt;

    @FXML
    private TextField Enderecotxt;

    @FXML
    private TextField Nometxt;

    @FXML
    private TextField TelefoneTxt;
    
    private Funcionario funcionario;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    public void initialize() {
        
        new LimitarCaracter (50, LimitarCaracter.TipoEntrada.NOME).applyToTextInputControl(Nometxt);
        new LimitarCaracter (100, LimitarCaracter.TipoEntrada.EMAIL).applyToTextInputControl(Emailtxt);
        new LimitarCaracter (10, LimitarCaracter.TipoEntrada.DATA).applyToTextInputControl(DatadeContratacaotxt);
        new LimitarCaracter (10, LimitarCaracter.TipoEntrada.DATA).applyToTextInputControl(DatadeNascimentotxt);
        new LimitarCaracter (14, LimitarCaracter.TipoEntrada.CPF).applyToTextInputControl(Cpftxt);
        new LimitarCaracter (15, LimitarCaracter.TipoEntrada.FONE).applyToTextInputControl(TelefoneTxt);
        
        // Inicializa o ComboBox com as opções
        // Adicionando as opções "Gerente" e "Corretor" no ComboBox
        Cargocb.setItems(FXCollections.observableArrayList("Gerente", "Corretor"));
    }
    
    @FXML
    void OnClickAlterarPerfil(ActionEvent event) throws SQLException {
        
        boolean dataValidaNascimento = LimitarCaracter.VerificarData.validarData(DatadeNascimentotxt.getText());
        boolean dataValidaContratacao = LimitarCaracter.VerificarData.validarData(DatadeContratacaotxt.getText());
         
          if (Nometxt.getText().isEmpty()||Cpftxt.getText().isEmpty()||Enderecotxt.getText().isEmpty()
                  || TelefoneTxt.getText().isEmpty() || Emailtxt.getText().isEmpty() || DatadeContratacaotxt.getText().isEmpty()
                  ||DatadeNascimentotxt.getText().isEmpty()){
            AlertaUtil.mostrarErro("Erro", "Erro ao cadastrar", "Todos os campos devem ser preenchidos!");
            
        }else if (TelefoneTxt.getText().length()!=13){
            AlertaUtil.mostrarErro("Erro", "Erro ao cadastrar", "Verifique o campo Telefone.");
            
        }else if (Cpftxt.getText().length()!=14){
            AlertaUtil.mostrarErro("Erro", "Erro ao cadastrar", "Verifique o campo CPF.");
        
        }else if(Emailtxt.getText().length()<10){
            AlertaUtil.mostrarErro("Erro", "Erro ao cadastrar", "Verifique o campo Email.");
            
        }else if(DatadeContratacaotxt.getText().length()!=10 || !dataValidaContratacao ){
            AlertaUtil.mostrarErro("Erro", "Erro ao cadastrar", "Verifique o campo Data de Contratação.");
      
        }else if(DatadeNascimentotxt.getText().length()!=10 || !dataValidaNascimento){
            AlertaUtil.mostrarErro("Erro", "Erro ao cadastrar", "Verifique o campo Data de Nascimento.");
            
        }else{
       // Ajuste nas variáveis
        funcionario.setNome(Nometxt.getText());
        funcionario.setCpf(Cpftxt.getText());
        funcionario.setTelefone(TelefoneTxt.getText());
        funcionario.setEndereco(Enderecotxt.getText());
        funcionario.setEmail(Emailtxt.getText());
    
    // Valida e seta o cargo se o valor não for nulo
    if (Cargocb.getValue() != null) {
        funcionario.setCargo(Cargocb.getValue());
    }
    // Validação da Data de Nascimento e data de contrato

        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate dataNascimentoLD = LocalDate.parse(DatadeNascimentotxt.getText(), formato);
        LocalDate dataContratacaoLD = LocalDate.parse(DatadeContratacaotxt.getText(), formato);

        Date dataNascimento = Date.valueOf(dataNascimentoLD);
        Date dataContratacao = Date.valueOf(dataContratacaoLD);
    
    }

    // Atualizar no banco
    FuncionarioDAO.atualizarFuncionario(funcionario);

    // Fechar a tela
    ((Stage) ((Button) event.getSource()).getScene().getWindow()).close(); 
       
    
        
        // Usando AlertaUtil para mostrar um alerta de sucesso
    
    
             AlertaUtil.mostrarInformacao("Sucesso",
            "Alterações feitas com sucesso!", 
            "As alterações no seu perfil foram salvas com sucesso.");
       
}

    @FXML
    void OnClickSair(ActionEvent event) {
        
        if (FecharTelaPersonalizacaoPerfil()) {
            Stage stage = (Stage) BtnPP1.getScene().getWindow();
            stage.close();
        } else {
            event.consume();
        }
    }
    
     void setFuncionario(Funcionario funcionario) {
        System.out.println("Funcionário recebido: " + funcionario);
        this.funcionario = funcionario;

        // Preencher os campos com os dados do funcionário
        Nometxt.setText(funcionario.getNome());
        Cpftxt.setText(funcionario.getCpf());
        TelefoneTxt.setText(funcionario.getTelefone());
        Enderecotxt.setText(funcionario.getEndereco());
        Emailtxt.setText(funcionario.getEmail());
        Cargocb.setValue(funcionario.getCargo());
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        DatadeNascimentotxt.setText(funcionario.getDataNascimento().format(formatter));
        DatadeContratacaotxt.setText(funcionario.getDataContratacao().format(formatter));
             
    }

    private boolean FecharTelaPersonalizacaoPerfil() {
        // Usando AlertaUtil para confirmar se o usuário realmente quer fechar a tela
        return AlertaUtil.mostrarConfirmacao(
            "Aviso", 
            "Tem certeza que deseja fechar a tela de personalização de perfil?",
            "Todas as alterações não salvas serão perdidas e a tela atual será fechada!"
        ).filter(response -> response == ButtonType.OK).isPresent();
    }
}



   
