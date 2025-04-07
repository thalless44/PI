package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.ClienteDAO;
import model.FuncionarioDAO;

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
        
      /*  String nome = txtNomeCliente.getText();
        String email = txtEmailCliente.getText();
        String telefone = txtTelefoneCliente.getText();

       if (nome.isEmpty() || email.isEmpty() || telefone.isEmpty()) {
            Alert erro = new Alert(Alert.AlertType.ERROR);
            erro.setTitle("Erro");
            erro.setHeaderText("Campos obrigatórios");
            erro.setContentText("Todos os campos devem ser preenchidos!");
            erro.show();
        } else {
            Alert sucesso = new Alert(Alert.AlertType.INFORMATION);
            sucesso.setTitle("Cadastro de cliente");
            sucesso.setHeaderText("Cadastro efetuado com sucesso!");
            sucesso.setContentText("Nome: " + nome + "\nEmail: " + email + "\nTelefone: " + telefone);
            sucesso.showAndWait();
        }*/
    }

    private boolean CancelarCadastroCliente() {
        
        Alert confirmar = new Alert(Alert.AlertType.WARNING);
        confirmar.setTitle("Aviso");
        confirmar.setHeaderText("Tem certeza que deseja fechar a tela de cadastro de cliente?");
        confirmar.setContentText("Todas as alterações não salvas serão perdidas e a tela atual será fechada!");
        
        return confirmar.showAndWait().filter(response -> response == ButtonType.OK).isPresent();        
    }

    private void cadastrarCliente() {
        
        String nome = txtNomeCliente.getText();
        String email = txtEmailCliente.getText();
        String telefone = txtTelefoneCliente.getText();
        
         if(nome.isEmpty() || email.isEmpty() || telefone.isEmpty()){
            
            Alert erro = new Alert(Alert.AlertType.ERROR);
            erro.setTitle("Erro");
            erro.setHeaderText("Campos obrigatórios");
            erro.setContentText("Todos os campos devem ser preenchidos!");
            erro.show();
         
        }else{
         
        boolean sucesso = ClienteDAO.cadastrarCliente(nome, email, telefone);
            
            Alert CadastrarCliente = new Alert(Alert.AlertType.INFORMATION);
            CadastrarCliente.setTitle("Sucesso");
            CadastrarCliente.setHeaderText("Cadastro realizado!");
            CadastrarCliente.setContentText("O cliente foi cadastrado com sucesso.");
            CadastrarCliente.showAndWait();
            
            Stage stage = (Stage) btnCancelarcadastro.getScene().getWindow();
            stage.close();
            
         }
    }
}
